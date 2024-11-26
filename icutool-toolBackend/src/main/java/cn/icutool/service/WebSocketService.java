package cn.icutool.service;

import cn.icutool.domain.entity.User;
import cn.icutool.domain.enums.WSRespTypeEnum;
import cn.icutool.domain.vo.ws.WSBaseReq;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public interface WebSocketService {
    /**
     * 处理用户登录请求，需要返回登录验证码
     */
    void handleLoginReq(Channel channel);

    void sendLoginSuccessMsg(String code, User user);

    void sendMsg(WSRespTypeEnum wsRespTypeEnum, Long uid);

    void removed(Channel channel);

    void add(Channel channel);

    void test(Channel channel, WSBaseReq wsBaseReq);

    /**
     * token登录
     * @param channel
     */
    void handleTokenLogin(Channel channel, WSBaseReq wsBaseReq);
}
