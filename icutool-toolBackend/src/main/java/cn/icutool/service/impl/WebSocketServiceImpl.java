package cn.icutool.service.impl;

import cn.hutool.json.JSONUtil;
import cn.icutool.constant.ApplicationConst;
import cn.icutool.domain.bo.WSUserInfoBO;
import cn.icutool.domain.entity.User;
import cn.icutool.domain.enums.WSRespTypeEnum;
import cn.icutool.domain.vo.WxCaptchaVO;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.domain.vo.response.WSBaseResp;
import cn.icutool.domain.vo.ws.WSAuthorize;
import cn.icutool.domain.vo.ws.WSBaseReq;
import cn.icutool.service.LoginService;
import cn.icutool.service.UserService;
import cn.icutool.service.WeChatService;
import cn.icutool.service.WebSocketService;
import cn.icutool.service.adapter.WSAdapter;
import cn.icutool.utils.JwtUtil;
import cn.icutool.utils.SecurityUtils;
import cn.icutool.websocket.NettyUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.jsonwebtoken.Claims;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {

    private final WeChatService weChatService;
    private final LoginService loginService;

    public WebSocketServiceImpl(WeChatService weChatService, LoginService loginService) {
        this.weChatService = weChatService;
        this.loginService = loginService;
    }

    private static final Duration EXPIRE_TIME = Duration.ofHours(1);
    private static final Long MAX_MUM_SIZE = 10000L;
    /**
     * 所有请求登录的code与channel关系
     */
    public static final Cache<String, Channel> WAIT_LOGIN_MAP = Caffeine.newBuilder()
            .expireAfterWrite(EXPIRE_TIME)
            .maximumSize(MAX_MUM_SIZE)
            .build();
    /**
     * 所有在线的用户和对应的socket 考虑多端同时登陆使用list
     */
    public static final ConcurrentHashMap<Long, CopyOnWriteArrayList<Channel>> ONLINE_UID_MAP = new ConcurrentHashMap<>();

    /**
     * 所有在线的channel和对应的额外信息
     */
    public static final ConcurrentHashMap<Channel, WSUserInfoBO> ONLINE_WS_MAP = new ConcurrentHashMap<>();

    /**
     * 处理用户登录请求，需要返回登录code
     */
    @SneakyThrows
    @Override
    public void handleLoginReq(Channel channel) {
        WxCaptchaVO wxCaptchaVO = weChatService.loginCaptchaGenerate();
        WAIT_LOGIN_MAP.put(wxCaptchaVO.getCode(), channel);
        sendMsg(channel, WSAdapter.buildLoginResp(wxCaptchaVO));
    }

    @Override
    public void sendLoginSuccessMsg(String code, User user) {
        // 发送登陆成功和token给用户
        AjaxResult ajaxResult = weChatService.checkLogin(code);
        JSONObject result = JSON.parseObject(JSON.toJSONString(ajaxResult.get("data")));
        String token = result.getString("token");
        Channel channel = WAIT_LOGIN_MAP.getIfPresent(code);
        if (!Objects.isNull(channel)) {
            WSAuthorize wsAuthorize = new WSAuthorize();
            wsAuthorize.setToken(token);
            sendMsg(channel, WSAdapter.buildTData(WSRespTypeEnum.TOKEN_CODE, wsAuthorize));
            WSUserInfoBO wsUserInfoBO = WSUserInfoBO.builder()
                    .userId(user.getId())
                    .token(token)
                    .expireTime(LocalDateTime.now().plus(ApplicationConst.JWT_EXPIRE, ChronoUnit.MILLIS))
                    .build();
            ONLINE_WS_MAP.put(channel, wsUserInfoBO);
            CopyOnWriteArrayList<Channel> channels = ONLINE_UID_MAP.get(user.getId());
            if (channels == null) {
                channels = new CopyOnWriteArrayList<>();
            }
            channels.add(channel);
            ONLINE_UID_MAP.put(user.getId(), channels);
            WAIT_LOGIN_MAP.invalidate(code);
        }
    }

    @Override
    public void sendMsg(WSRespTypeEnum wsRespTypeEnum, Long uid) {
        CopyOnWriteArrayList<Channel> channels1 = ONLINE_UID_MAP.get(uid);
        if (Objects.isNull(channels1)) {
            log.error("推送ws消息失败");
        }
        Optional.ofNullable(ONLINE_UID_MAP.get(uid)).ifPresent(channels -> channels.forEach(channel ->
            sendMsg(channel, WSAdapter.buildStrResp(wsRespTypeEnum, null))
        ));
    }

    @Override
    public void removed(Channel channel) {
        NettyUtil.setAttr(channel, NettyUtil.UID, null);
        NettyUtil.setAttr(channel, NettyUtil.TOKEN, null);
        WSUserInfoBO wsUserInfoBO = ONLINE_WS_MAP.get(channel);
        if (!Objects.isNull(wsUserInfoBO)) {
            ONLINE_WS_MAP.remove(channel);
            CopyOnWriteArrayList<Channel> channels = ONLINE_UID_MAP.get(wsUserInfoBO.getUserId());
            if (!Objects.isNull(channels)) {
                channels.remove(channel);
                if (channels.size() == 0) {
                    ONLINE_UID_MAP.remove(wsUserInfoBO.getUserId());
                }
            }
        }
    }

    @Override
    public void add(Channel channel) {
        Long uid = NettyUtil.getAttr(channel, NettyUtil.UID);
        String ip = NettyUtil.getAttr(channel, NettyUtil.IP);
        // String token = NettyUtil.getAttr(channel, NettyUtil.TOKEN);
        updateChannelUserInfo(channel, uid, ip);
    }

    private void updateChannelUserInfo(Channel channel, Long uid, String ip) {
        WSUserInfoBO wsUserInfoBO = WSUserInfoBO.builder()
                .userId(uid)
                .expireTime(LocalDateTime.now().plus(ApplicationConst.JWT_EXPIRE, ChronoUnit.MILLIS))
                .build();
        ONLINE_WS_MAP.put(channel, wsUserInfoBO);
        CopyOnWriteArrayList<Channel> channels = ONLINE_UID_MAP.get(uid);
        if (channels == null) {
            channels = new CopyOnWriteArrayList<>();
        }
        channels.add(channel);
        ONLINE_UID_MAP.put(uid, channels);
        //更新用户IP信息
        loginService.UpdateIp(uid, ip);
        NettyUtil.setAttr(channel, NettyUtil.UID, uid);
        sendMsg(channel, WSAdapter.buildTData(WSRespTypeEnum.LOGIN_SUCCESS, wsUserInfoBO));
    }

    @Override
    public void test(Channel channel, WSBaseReq wsBaseReq) {
        HashMap<String, Object> map = new HashMap<>();
        if ("token".equals(wsBaseReq.getData())) {
            WSUserInfoBO wsUserInfoBO = ONLINE_WS_MAP.get(channel);
            System.out.println("ONLINE_WS_MAP.size() = " + ONLINE_WS_MAP.size());
            String attrToken = NettyUtil.getAttr(channel, NettyUtil.TOKEN);
            map.put("attrToken", attrToken);
            map.put("wsUserInfoBO", wsUserInfoBO);
        } else if ("uid".equals(wsBaseReq.getData())) {
            WSUserInfoBO wsUserInfoBO = ONLINE_WS_MAP.get(channel);
            System.out.println("ONLINE_WS_MAP.size() = " + ONLINE_WS_MAP.size());
            Long attrUid = NettyUtil.getAttr(channel, NettyUtil.UID);
            map.put("attrUid", attrUid);
            map.put("wsUserInfoBO", wsUserInfoBO);
        }
        sendMsg(channel, WSAdapter.buildStrResp(WSRespTypeEnum.TEST_CODE, JSON.toJSONString(map)));
    }

    @Override
    public void handleTokenLogin(Channel channel, WSBaseReq wsBaseReq) {
        // 通过token注册这个用户绑定到channel
        String data = wsBaseReq.getData();
        WSAuthorize wsAuthorize = JSON.parseObject(data, WSAuthorize.class);
        String token = wsAuthorize.getToken();
        Long uid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            uid = claims.get("id",Long.class);
        } catch (Exception e) {
            log.error("ws token解析异常：{}", e.getMessage(), e);
            sendMsg(channel, WSAdapter.buildStrResp(WSRespTypeEnum.LOGIN_ERROR, null));
            return;
        }
        String ip = NettyUtil.getAttr(channel, NettyUtil.IP);
        updateChannelUserInfo(channel, uid, ip);
    }


    private void sendMsg(Channel channel, WSBaseResp<?> wsBaseResp) {
        channel.writeAndFlush(new TextWebSocketFrame(JSONUtil.toJsonStr(wsBaseResp)));
    }

}
