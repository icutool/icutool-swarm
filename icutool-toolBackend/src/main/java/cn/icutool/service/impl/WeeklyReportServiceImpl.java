package cn.icutool.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.icutool.config.MinioConfiguration;
import cn.icutool.dao.UserDao;
import cn.icutool.dao.WeeklyReportDao;
import cn.icutool.domain.bo.WeeklyReportExcelBO;
import cn.icutool.domain.dto.PageDTO;
import cn.icutool.domain.entity.User;
import cn.icutool.domain.entity.WeeklyReport;
import cn.icutool.domain.vo.request.WeeklyReportReq;
import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.domain.vo.response.MinioFileResp;
import cn.icutool.service.WeeklyReportService;
import cn.icutool.utils.*;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static cn.icutool.utils.IFileUtil.judgeCreatePath;

/**
 * 周报服务实现类
 * @author xietao
 */
@Service
@Slf4j
public class WeeklyReportServiceImpl implements WeeklyReportService {
    @Resource
    private MinioConfiguration minioConfiguration;
    @Resource
    private WeeklyReportDao weeklyReportDao;
    @Resource
    private UserDao userDao;
    @Resource
    private MinioUtil minioUtil;
    @Override
    public WxMpXmlOutMessage wxMsgDayReport(WxMpXmlMessage wxMessage, WxMpService weixinService, String content) {
        log.debug("收到日报请求");
        User user = userDao.selectOneByOpenId(wxMessage.getFromUser());
        if (ObjectUtils.isEmpty(user)){
            return new TextBuilder().build("您不是该功能的用户,请访问网站进行注册!", wxMessage, weixinService);
        }
        WeeklyReport weeklyReport = WeeklyReport.builder()
                .subject(content.replace("#",""))
                .userId(user.getId())
                .build();
        weeklyReportDao.save(weeklyReport);
        return new TextBuilder().build("日报提交完成", wxMessage, weixinService);
    }

    @Override
    public AjaxResult page(Integer pageNum, Integer pageSize, String keyword) {
        log.debug("查询周报列表");
        pageNum = (pageNum -1)* pageSize;
        List<WeeklyReport> weeklyReports = weeklyReportDao.queryPage(pageNum, pageSize, keyword, SecurityUtils.getUserId());
        int count = weeklyReportDao.count(keyword, SecurityUtils.getUserId());
        PageDTO<WeeklyReport> resp = new PageDTO<>(weeklyReports, count);
        return AjaxResult.success("分页查询成功",resp);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult edit(WeeklyReportReq weeklyReportReq) {
        if (weeklyReportReq.getId() == null){
            return AjaxResult.error("周报ID不能为空");
        }
        WeeklyReport weeklyReport = new WeeklyReport();
        BeanUtils.copyProperties(weeklyReportReq,weeklyReport);
        weeklyReport.setUserId(SecurityUtils.getUserId());
        weeklyReportDao.update(weeklyReport);
        return AjaxResult.success("修改成功");
    }

    @Override
    public AjaxResult add(WeeklyReportReq weeklyReportReq) {
        WeeklyReport weeklyReport = WeeklyReport.builder()
                .subject(weeklyReportReq.getSubject())
                .content(weeklyReportReq.getContent())
                .userId(SecurityUtils.getUserId())
                .build();
        weeklyReportDao.save(weeklyReport);
        return AjaxResult.success("添加成功");
    }

    @Override
    public AjaxResult delete(List<Integer> ids) {
        weeklyReportDao.removeByIds(ids);
        return AjaxResult.success("删除成功");
    }

    @Override
    public AjaxResult generateWeeklyReport(){
        LocalDate theStartDateOfTheWeek = TimeUtil.getTheStartDateOfTheWeek();
        List<WeeklyReport> weeklyReports = weeklyReportDao.list(theStartDateOfTheWeek, SecurityUtils.getUserId());
        if (CollectionUtil.isEmpty(weeklyReports)){
            log.debug("没有周报数据");
            return AjaxResult.error("没有周报数据");
        }
        AtomicInteger counter = new AtomicInteger(1); // 计数器从1开始
        List<WeeklyReportExcelBO> excelBOList = weeklyReports.stream()
                .map(report -> {
                    WeeklyReportExcelBO excelBO = new WeeklyReportExcelBO();
                    excelBO.setId(counter.getAndIncrement()); // 获取当前值，并自增
                    excelBO.setSubject(report.getSubject());
                    excelBO.setContent(report.getContent());
                    excelBO.setCreateTime(report.getCreateTime().toLocalDate());
                    excelBO.setUpdateTime(report.getUpdateTime().toLocalDate());
                    excelBO.setDoneTime(report.getUpdateTime().toLocalDate()); // doneTime取updateTime一样的值
                    excelBO.setProgressBar("100%"); // progressBar全部默认为100%
                    return excelBO;
                })
                .collect(Collectors.toList());
        String excelFileName = SecurityUtils.getUserId()
                + File.separator
                + TimeUtil.getWeekOfYear()
                + "_"
                + System.currentTimeMillis()
                + ".xlsx";
        String excelFilePath = judgeCreatePath(FilePathConst.UserExcelPath
                + File.separator
                + excelFileName);
        ExcelUtil.generateExcelFromTemplates(FilePathConst.WEEKLY_REPORT_PATH, excelFilePath,"工作周报", excelBOList);
        //储存数据库
        //储存到minio
        String minioFilePath = excelFilePath.replace(FilePathConst.BASE_PATH, "");
        minioUtil.uploadFile(new File(excelFilePath), minioConfiguration.getBucketName(), minioFilePath.replaceAll("\\\\", "/"));
        //获取下载地址
        String url = minioUtil.getUrl(minioFilePath, 1, TimeUnit.HOURS);
        if (StringUtils.isEmpty(url)){
            return AjaxResult.error("获取minio文件失败");
        } else {
            MinioFileResp minioFileResp = MinioFileResp.builder()
                    .fileName(FileUtil.getName(excelFileName))
                    .url(url)
                    .time(1L)
                    .timeUnit(TimeUnit.HOURS)
                    .build();
            return AjaxResult.success("生成周报下载地址成功", minioFileResp);
        }
    }


}
