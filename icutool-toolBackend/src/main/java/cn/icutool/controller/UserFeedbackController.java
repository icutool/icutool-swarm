package cn.icutool.controller;

import cn.icutool.domain.entity.UserFeedback;
import cn.icutool.domain.vo.request.UserFeedbackReq;
import cn.icutool.domain.vo.request.UserRegisterReq;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.service.UserFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xietao
 */
@RestController
@RequestMapping("/feedback")
@Api(tags = "反馈接口")
public class UserFeedbackController {

    private final UserFeedbackService userFeedbackService;

    public UserFeedbackController(UserFeedbackService userFeedbackService) {
        this.userFeedbackService = userFeedbackService;
    }

    @PostMapping("/add")
    @ApiOperation("添加反馈")
    public AjaxResult add(@Valid @RequestBody UserFeedbackReq userFeedbackReq){
        UserFeedback userFeedback = new UserFeedback();
        BeanUtils.copyProperties(userFeedbackReq, userFeedback);
        userFeedbackService.add(userFeedback);
        return AjaxResult.success("添加反馈内容成功,等待管理员查看~");
    }
}
