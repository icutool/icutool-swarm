package cn.icutool.service.strategy.wechatMsg;

import cn.icutool.domain.enums.WxMsgTypeEnum;
import cn.icutool.service.WeeklyReportService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

/**
 * @author xietao
 */
@Component
public class DayReportStrategy implements MsgPrefixStrategy{
    private final WeeklyReportService weeklyReportService;

    public DayReportStrategy(WeeklyReportService weeklyReportService) {
        this.weeklyReportService = weeklyReportService;
    }

    @Override
    public WxMsgTypeEnum getKey() {
        return WxMsgTypeEnum.DAY_REPORT;
    }

    @Override
    public WxMpXmlOutMessage msgHandle(WxMpXmlMessage wxMessage, WxMpService weixinService) {
        return weeklyReportService.wxMsgDayReport(wxMessage, weixinService, wxMessage.getContent());
    }
}
