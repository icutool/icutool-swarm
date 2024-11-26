package cn.icutool.controller;

import cn.icutool.domain.vo.request.AutoReplyReq;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.service.AutoReplyService;
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
 * @author xietao
 */
@RestController
@RequestMapping("/autoReply")
public class AutoReplyController {
    private final AutoReplyService autoReplyService;

    public AutoReplyController(AutoReplyService autoReplyService) {
        this.autoReplyService = autoReplyService;
    }

    /**
     * 分页接口
     */
    @ApiOperation("自动回复分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "页大小"),
            @ApiImplicitParam(name = "keyword", value = "关键词")
    })
    @GetMapping("/page")
    public AjaxResult page(@RequestParam(defaultValue = "1") Integer pageNum,
                           @Valid @Max(value = 30, message = "页大小最大30") @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) String keyword){
        return autoReplyService.page(pageNum, pageSize, keyword);
    }

    @PostMapping("/edit")
    @ApiOperation("消息编辑")
    public AjaxResult edit(@Valid @RequestBody AutoReplyReq autoReplyReq){
        return autoReplyService.edit(autoReplyReq);
    }

    @PostMapping("/add")
    @ApiOperation("自动回复添加")
    public AjaxResult add(@Valid @RequestBody AutoReplyReq autoReplyReq){
        return autoReplyService.addAutoReply(autoReplyReq);
    }

    @PostMapping("/delete")
    @ApiOperation("自动回复删除")
    public AjaxResult delete(@Valid @Size(min = 1) @RequestBody List<Integer> ids){
        return autoReplyService.delete(ids);
    }
}
