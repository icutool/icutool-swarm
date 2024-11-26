package cn.icutool.service.strategy.wechatMsg;

import cn.icutool.config.CacheConfig;
import cn.icutool.config.WxMpProperties;
import cn.icutool.domain.enums.WxMsgTypeEnum;
import cn.icutool.utils.TextBuilder;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

/**
 * @author xietao
 */
@Component
public class DefaultPrefixStrategy implements MsgPrefixStrategy {
    private final WxMpProperties wxMpProperties;

    public DefaultPrefixStrategy(WxMpProperties wxMpProperties) {
        this.wxMpProperties = wxMpProperties;
    }

    @Override
    public WxMsgTypeEnum getKey() {
        return WxMsgTypeEnum.OTHER;
    }

    @Override
    public WxMpXmlOutMessage msgHandle(WxMpXmlMessage wxMessage, WxMpService weixinService) {
        String content = wxMessage.getContent();
        if (CacheConfig.HOT_REPLY_MAP.containsKey(content)){
            return new TextBuilder().build(CacheConfig.HOT_REPLY_MAP.get(content), wxMessage, weixinService);
        } else {
            return new TextBuilder().build(wxMpProperties.getAutoResp(), wxMessage, weixinService);
        }
    }
}