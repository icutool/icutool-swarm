package cn.icutool.handler.wechat;

import cn.hutool.json.JSONUtil;
import cn.icutool.handler.AbstractHandler;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xietao
 */
@Component
@Slf4j
public class LogHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        //如果事件是取消订阅 则删除账户
        String event = wxMessage.getEvent();
        //获取用户id
        String openId = wxMessage.getFromUser();
        if ("unsubscribe".equals(event)) {
           log.info("用户{} 取消订阅 屏蔽账户",openId);
        }
        log.info("\n接收到请求消息，内容：{}", JSONUtil.toJsonStr(wxMessage));
        return null;
    }

}
