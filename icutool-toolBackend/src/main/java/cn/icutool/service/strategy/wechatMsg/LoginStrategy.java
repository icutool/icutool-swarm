package cn.icutool.service.strategy.wechatMsg;

import cn.icutool.domain.enums.WxMsgTypeEnum;
import cn.icutool.service.LoginService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

/**
 * @author xietao
 */
@Component
public class LoginStrategy implements MsgPrefixStrategy {
    private final LoginService loginService;

    public LoginStrategy(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public WxMsgTypeEnum getKey() {
        return WxMsgTypeEnum.LOGIN;
    }

    @Override
    public WxMpXmlOutMessage msgHandle(WxMpXmlMessage wxMessage, WxMpService weixinService) {
        return loginService.wxMsgLoginHandler(wxMessage, weixinService, wxMessage.getContent());
    }
}