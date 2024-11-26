package cn.icutool.handler.wechat;

import cn.icutool.config.WxMpProperties;
import cn.icutool.handler.AbstractHandler;
import cn.icutool.service.WeChatService;
import cn.icutool.utils.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@Slf4j
public class SubscribeHandler extends AbstractHandler {
    @Resource
    private WeChatService weChatService;
    @Resource
    private WxMpProperties wxMpProperties;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        log.info("新关注用户 OPENID: " + wxMessage.getFromUser());
        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(weixinService, wxMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (responseResult != null) {
            return responseResult;
        }
        try {
            return new TextBuilder().build(wxMpProperties.getAutoResp(), wxMessage, weixinService);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpService weixinService, WxMpXmlMessage wxMessage)
            throws Exception {
        return weChatService.scan(weixinService, wxMessage);
    }

}
