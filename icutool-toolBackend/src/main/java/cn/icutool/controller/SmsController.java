package cn.icutool.controller;

import cn.icutool.dao.UserDao;
import cn.icutool.domain.entity.User;
import cn.icutool.domain.vo.response.AjaxResult;
import cn.icutool.utils.RedisUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author xietao
 */
@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsController {

    private final UserDao userDao;

    public SmsController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/getCode")
    @ApiOperation("获取短信登录二维码")
    public AjaxResult getCode(@RequestParam String mobile){
        //查看用户手机号是否已在网站注册
        User byUserName = userDao.selectOneByMobile(mobile);
        if(byUserName == null){
            return AjaxResult.error("您输入的手机号未曾注册");
        }
        //生成六位随机号
        String smsCode = RandomStringUtils.randomNumeric(6);
        //TODO 调用短信服务提供商的接口发送短信
        log.info(smsCode  + "+>" + mobile);
        RedisUtils.set(mobile, smsCode,360L, TimeUnit.SECONDS);
        return AjaxResult.success("短信验证码已经发送");
    }
}
