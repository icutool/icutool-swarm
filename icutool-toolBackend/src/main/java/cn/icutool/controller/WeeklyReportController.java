package cn.icutool.controller;

import cn.icutool.domain.vo.request.WeeklyReportReq;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.service.WeeklyReportService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 周报相关
 * @author <a href="https://icutool.cn">旧年</a>
 * @since 2024-05-21
 */
@RestController
@RequestMapping("/weeklyReport")
public class WeeklyReportController {
    private final WeeklyReportService weeklyReportService;

    public WeeklyReportController(WeeklyReportService weeklyReportService) {
        this.weeklyReportService = weeklyReportService;
    }

    /**
     * 分页接口
     */
    @ApiOperation("日报分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "页大小"),
            @ApiImplicitParam(name = "keyword", value = "关键词")
    })
    @GetMapping("/page")
    public AjaxResult page(@RequestParam(defaultValue = "1") Integer pageNum,
                           @Valid @Max (value = 30, message = "页大小最大30") @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) String keyword){
        return weeklyReportService.page(pageNum, pageSize, keyword);
    }

    @PostMapping("/edit")
    @ApiOperation("日报编辑")
    public AjaxResult edit(@Valid @RequestBody WeeklyReportReq weeklyReportReq){
        return weeklyReportService.edit(weeklyReportReq);
    }

    @PostMapping("/add")
    @ApiOperation("日报添加")
    public AjaxResult add(@Valid @RequestBody WeeklyReportReq weeklyReportReq){
        return weeklyReportService.add(weeklyReportReq);
    }

    @PostMapping("/delete")
    @ApiOperation("日报删除")
    public AjaxResult delete(@Valid @Size(min = 1) @RequestBody List<Integer> ids){
        return weeklyReportService.delete(ids);
    }
    @GetMapping("/download")
    @ApiOperation("周报下载")
    public AjaxResult generateWeeklyReport(){
        return weeklyReportService.generateWeeklyReport();
    }

}

