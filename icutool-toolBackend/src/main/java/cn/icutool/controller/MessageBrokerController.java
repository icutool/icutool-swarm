package cn.icutool.controller;


import cn.icutool.domain.vo.request.MessageBrokerPicReq;
import cn.icutool.domain.vo.request.MessageBrokerReq;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.service.MessageBrokerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;

/**
 * @author xietao
 * @since 2024-05-22
 */
@RestController
@RequestMapping("/messageBroker")
public class MessageBrokerController {
    private final MessageBrokerService messageBrokerService;

    public MessageBrokerController(MessageBrokerService messageBrokerService) {
        this.messageBrokerService = messageBrokerService;
    }

    /**
     * 分页接口
     */
    @ApiOperation("消息分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "页大小"),
            @ApiImplicitParam(name = "keyword", value = "关键词")
    })
    @GetMapping("/page")
    public AjaxResult page(@RequestParam(defaultValue = "1") Integer pageNum,
                           @Valid @Max(value = 30, message = "页大小最大30") @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(required = false) String keyword){
        return messageBrokerService.page(pageNum, pageSize, keyword);
    }

    @PostMapping("/edit")
    @ApiOperation("消息编辑")
    public AjaxResult edit(@Valid @RequestBody MessageBrokerReq messageBrokerReq){
        return messageBrokerService.edit(messageBrokerReq);
    }

    @PostMapping("/add")
    @ApiOperation("消息添加")
    public AjaxResult add(@Valid @RequestBody MessageBrokerReq messageBrokerReq){
        return messageBrokerService.add(messageBrokerReq);
    }

    @PostMapping("/addPic")
    @ApiOperation("图片消息添加")
    public AjaxResult addPic(@NotNull MessageBrokerPicReq messageBrokerPicReq) throws IOException {
        return messageBrokerService.addPic(messageBrokerPicReq);
    }

    @PostMapping("/delete")
    @ApiOperation("消息删除")
    public AjaxResult delete(@Valid @Size(min = 1) @RequestBody List<Integer> ids){
        return messageBrokerService.delete(ids);
    }

}

