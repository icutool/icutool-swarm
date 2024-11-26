package cn.icutool.handler.wechat;

import cn.icutool.handler.AbstractHandler;
import cn.icutool.service.WeChatService;
import me.chanjar.weixin.common.error.WxErrorException;
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
public class ScanHandler extends AbstractHandler {


    @Resource
    private WeChatService weChatService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 扫码事件处理
        logger.info("正在进行扫码事件处理");
        return weChatService.scan(wxMpService, wxMpXmlMessage);
    }

}
