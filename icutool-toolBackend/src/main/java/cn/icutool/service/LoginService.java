package cn.icutool.service;

import cn.icutool.domain.LoginUser;
import cn.icutool.domain.vo.request.MobileReq;
import cn.icutool.domain.vo.request.UserRegisterReq;
import cn.icutool.domain.vo.request.UserReq;
import cn.icutool.domain.vo.response.AjaxResult;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author xietao
 */
public interface LoginService {
    AjaxResult login(UserReq user);

    void UpdateIp(Long uid,String ip);

    AjaxResult smsLogin(MobileReq user);

    AjaxResult logout();

    AjaxResult register(UserRegisterReq user);

    WxMpXmlOutMessage wxMsgLoginHandler(WxMpXmlMessage wxMessage, WxMpService weixinService, String content);

    AjaxResult info();
}
