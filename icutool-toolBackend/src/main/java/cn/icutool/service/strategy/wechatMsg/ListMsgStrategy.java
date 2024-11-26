package cn.icutool.service.strategy.wechatMsg;

import cn.icutool.domain.enums.WxMsgTypeEnum;
import cn.icutool.service.MessageBrokerService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

/**
 * @author xietao
 */
@Component
public class ListMsgStrategy implements MsgPrefixStrategy {
    private final MessageBrokerService messageBrokerService;

    public ListMsgStrategy(MessageBrokerService messageBrokerService) {
        this.messageBrokerService = messageBrokerService;
    }

    @Override
    public WxMsgTypeEnum getKey() {
        return WxMsgTypeEnum.LIST_MESSAGE;
    }

    @Override
    public WxMpXmlOutMessage msgHandle(WxMpXmlMessage wxMessage, WxMpService weixinService) {
        return messageBrokerService.listMsg(wxMessage, weixinService, wxMessage.getContent());
    }
}
