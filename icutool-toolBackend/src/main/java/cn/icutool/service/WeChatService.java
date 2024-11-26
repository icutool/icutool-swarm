package cn.icutool.service;

import cn.icutool.domain.vo.WxCaptchaVO;
import cn.icutool.domain.vo.response.AjaxResult;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 微信服务接口
 * @author xietao
 */
public interface WeChatService {
    WxCaptchaVO loginCaptchaGenerate();
    AjaxResult checkLogin(String code);
    WxMpXmlOutMessage receiveMessagesAndProcess(WxMpXmlMessage wxMessage, WxMpService weixinService);
    WxMpXmlOutMessage scan(WxMpService wxMpService, WxMpXmlMessage wxMpXmlMessage);
    void authorize(WxOAuth2UserInfo userInfo);
}
