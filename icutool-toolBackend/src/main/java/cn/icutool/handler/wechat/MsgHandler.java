package cn.icutool.handler.wechat;

import cn.icutool.handler.AbstractHandler;
import cn.icutool.service.WeChatService;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xietao
 */
@Component
public class MsgHandler extends AbstractHandler {
    @Resource
    private WeChatService weChatService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {
        return weChatService.receiveMessagesAndProcess(wxMessage, weixinService);
    }

}
