package cn.icutool.service.impl;

import cn.icutool.config.WxMpProperties;
import cn.icutool.domain.vo.WxCaptchaVO;
import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.service.WeChatService;
import cn.icutool.service.strategy.wechatMsg.WechatMsgPrefixContext;
import cn.icutool.utils.RedisUtils;
import cn.icutool.utils.TokenUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


/**
 * 微信服务实现类
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {
    @Resource
    private WxMpProperties wxMpProperties;
    @Resource
    private TokenUtil tokenUtil;
    @Resource
    private WechatMsgPrefixContext wechatMsgPrefixContext;

    @Override
    public WxCaptchaVO loginCaptchaGenerate() {
        String code = generateRandomNumber();
        WxCaptchaVO wxCaptchaVO = WxCaptchaVO.builder()
                .code(code)
                .expireTime(LocalDateTime.now().plusMinutes(Long.parseLong(wxMpProperties.getExpireTime())))
                .flag(Boolean.FALSE)
                .build();
        RedisUtils.set(RedisConst.getRedisKeyUserWxLoginCaptcha(wxCaptchaVO.getCode()), wxCaptchaVO, Long.parseLong(wxMpProperties.getExpireTime()), TimeUnit.MINUTES);
        return wxCaptchaVO;
    }

    public static synchronized String generateRandomNumber() {
        int randomNumber = ThreadLocalRandom.current().nextInt(1000, 9999);
        return "00" + randomNumber;
    }

    @Override
    public AjaxResult checkLogin(String code) {
        String captchaKey = RedisConst.getRedisKeyUserWxLoginCaptcha(code);
        if (RedisUtils.hasKey(captchaKey)) {
            WxCaptchaVO wxCaptchaVO = JSON.parseObject(RedisUtils.get(captchaKey), WxCaptchaVO.class);
            if (wxCaptchaVO.getFlag()){
                RedisUtils.del(captchaKey);
                // 已登录 返回token
                return tokenUtil.getUserToken(wxCaptchaVO.getUserId());
            } else {
                // 未登录
                return  AjaxResult.error("未进行发送验证码登录");
            }
        }
        return AjaxResult.error("验证码失效");
    }

    @Override
    public WxMpXmlOutMessage receiveMessagesAndProcess(WxMpXmlMessage wxMessage, WxMpService weixinService) {
        return wechatMsgPrefixContext.handlePrefix(wxMessage, weixinService);
    }

    @Override
    public WxMpXmlOutMessage scan(WxMpService wxMpService, WxMpXmlMessage wxMpXmlMessage) {
        String openid = wxMpXmlMessage.getFromUser();
        //判断是登录扫码还是扫码关注公众号
        log.info("用户{} 正在进行扫码事件",openid);
        return null;
    }

    @Override
    public void authorize(WxOAuth2UserInfo userInfo) {
        return;
    }

}
