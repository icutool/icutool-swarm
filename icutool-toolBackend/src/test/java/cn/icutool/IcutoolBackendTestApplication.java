package cn.icutool;

import cn.icutool.service.strategy.wechatMsg.WechatMsgPrefixContext;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class IcutoolBackendTestApplication {
    @Resource
    private WechatMsgPrefixContext wechatMsgPrefixContext;
    @Test
    void contextLoads() {
        WxMpXmlMessage wxMpXmlMessage = new WxMpXmlMessage();
        wxMpXmlMessage.setMsgType("text");
        wxMpXmlMessage.setContent("你好");
        wechatMsgPrefixContext.handlePrefix(wxMpXmlMessage, null);
    }
}
