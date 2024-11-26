package cn.icutool.controller;

import cn.icutool.common.annotation.FrequencyControl;
import cn.icutool.domain.vo.WxCaptchaVO;
import cn.icutool.domain.vo.request.MobileReq;
import cn.icutool.domain.vo.request.UserRegisterReq;
import cn.icutool.domain.vo.request.UserReq;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.service.LoginService;
import cn.icutool.service.WeChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @author xietao
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Resource
    private LoginService loginService;
    @Resource
    private WeChatService weChatService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @FrequencyControl(time = 5, count = 3, target = FrequencyControl.Target.IP, spEl = "#wxMessage")
    @FrequencyControl(time = 30, count = 5, target = FrequencyControl.Target.IP, spEl = "#wxMessage")
    @FrequencyControl(time = 300, count = 10, target = FrequencyControl.Target.IP, spEl = "#wxMessage")
    public AjaxResult login(@Valid @RequestBody UserReq user){
        return loginService.login(user);
    }

    @GetMapping("/smsLogin")
    @ApiOperation("短信登录")
    @FrequencyControl(time = 5, count = 3, target = FrequencyControl.Target.IP, spEl = "#wxMessage")
    @FrequencyControl(time = 30, count = 5, target = FrequencyControl.Target.IP, spEl = "#wxMessage")
    @FrequencyControl(time = 300, count = 10, target = FrequencyControl.Target.IP, spEl = "#wxMessage")
    public AjaxResult smsLogin(@RequestParam String mobile, @RequestParam String smsCode){
        return loginService.smsLogin(new MobileReq(mobile,smsCode));
    }

    @GetMapping(value = "/wechat/loginCaptcha")
    @ApiOperation("生成登录验证码")
    public AjaxResult loginCaptcha(){
        WxCaptchaVO wxCaptchaVO = weChatService.loginCaptchaGenerate();
        return AjaxResult.success("获取登录码成功", wxCaptchaVO);
    }

    @GetMapping("/wechat/checkLogin")
    @ApiOperation("检测登录")
    public AjaxResult wechatMpCheckLogin(@RequestParam String code) {
        return weChatService.checkLogin(code);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public AjaxResult register(@Valid @RequestBody UserRegisterReq userRegisterReq){
        return loginService.register(userRegisterReq);
    }
    @PostMapping("/update")
    @ApiOperation("用户身份更新")
    public AjaxResult update(@Valid @RequestBody UserRegisterReq userRegisterReq){
        return loginService.register(userRegisterReq);
    }

    @GetMapping("/logout")
    @ApiOperation("用户登出")
    public AjaxResult logout(){
        return loginService.logout();
    }

    @GetMapping("/info")
    @ApiOperation("获取用户身份信息")
    public AjaxResult info(){
        return loginService.info();
    }

    private int a = 100;
    @GetMapping("/hello")
    // @PreAuthorize("hasAuthority('api:hello')")
    public int hello(){
        // Integer.valueOf("aaaa");
        // String currentDir = System.getProperty("user.dir");
        a--;
        return a;
    }

}
