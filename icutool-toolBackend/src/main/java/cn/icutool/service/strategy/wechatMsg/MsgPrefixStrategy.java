package cn.icutool.service.strategy.wechatMsg;

import cn.icutool.domain.enums.WxMsgTypeEnum;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author xietao
 */
public interface MsgPrefixStrategy {
    WxMsgTypeEnum getKey();
    WxMpXmlOutMessage msgHandle(WxMpXmlMessage wxMessage, WxMpService weixinService);
}
