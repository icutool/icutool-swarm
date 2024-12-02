package cn.icutool.service;

import cn.icutool.domain.vo.request.WeeklyReportReq;
import cn.icutool.common.domain.vo.response.AjaxResult;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author <a href="https://icutool.cn">旧年</a>
 * @since 2024-05-21
 */
public interface WeeklyReportService {

    WxMpXmlOutMessage wxMsgDayReport(WxMpXmlMessage wxMessage, WxMpService weixinService, String content);

    AjaxResult page(Integer pageNum, Integer pageSize, String keyword);

    AjaxResult edit(WeeklyReportReq weeklyReportReq);

    AjaxResult delete(List<Integer> ids);
    AjaxResult generateWeeklyReport();

    AjaxResult add(WeeklyReportReq weeklyReportReq);
}
