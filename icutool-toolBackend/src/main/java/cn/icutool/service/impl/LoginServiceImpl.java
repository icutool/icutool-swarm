package cn.icutool.service.impl;

import cn.hutool.core.lang.UUID;
import cn.icutool.common.RequestHolder;
import cn.icutool.common.annotation.FrequencyControl;
import cn.icutool.constant.RedisConst;
import cn.icutool.dao.UserDao;
import cn.icutool.domain.LoginUser;
import cn.icutool.domain.dto.IpInfoDTO;
import cn.icutool.domain.dto.RequestInfo;
import cn.icutool.domain.entity.User;
import cn.icutool.domain.vo.WxCaptchaVO;
import cn.icutool.domain.vo.request.MobileReq;
import cn.icutool.domain.vo.request.UserRegisterReq;
import cn.icutool.domain.vo.request.UserReq;
import cn.icutool.common.domain.vo.response.AjaxResult;
import cn.icutool.exception.BusinessException;
import cn.icutool.exception.FrequencyControlException;
import cn.icutool.exception.UserException;
import cn.icutool.exception.codeType.BusinessExceptionType;
import cn.icutool.exception.codeType.UserExceptionType;
import cn.icutool.service.CommonUtilsService;
import cn.icutool.service.LoginService;
import cn.icutool.service.WebSocketService;
import cn.icutool.utils.RedisUtils;
import cn.icutool.utils.SecurityUtils;
import cn.icutool.utils.TextBuilder;
import cn.icutool.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private SmsCodeUserDetailsServiceImpl smsCodeUserDetailsService;
    @Resource
    @Lazy
    private WebSocketService webSocketService;
    @Resource
    private UserDao userDao;
    @Resource
    private TokenUtil  tokenUtil;
    @Resource
    private CommonUtilsService commonUtilsService;

    @Override
    public AjaxResult login(UserReq userReq) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userReq.getUsername(),
                userReq.getPassword());
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            return AjaxResult.error("登录失败");
        }
        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)){
            return AjaxResult.error("登录失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        RequestInfo requestInfo = RequestHolder.get();
        UpdateIp(loginUser.getUser().getId(), requestInfo.getIp());
        return tokenUtil.getUserToken(loginUser);
    }

    @Async
    @Override
    public void UpdateIp(Long uid,String ip) {
        String geo;
        IpInfoDTO ipInfoDTO = commonUtilsService.searchIpInfo(ip);
        if (!"0".equals(ipInfoDTO.getCity())){
            geo = ipInfoDTO.getCity();
        } else if (!"0".equals(ipInfoDTO.getProvince())){
            geo = ipInfoDTO.getProvince();
        } else if (!"0".equals(ipInfoDTO.getCountry()) && !"内网IP".equals(ipInfoDTO.getCountry())){
            geo = ipInfoDTO.getCountry();
        } else {
            geo = "未知";
        }
        userDao.updateUserIpInfo(uid, ip, geo);
    }

    @Override
    public AjaxResult smsLogin(MobileReq user) {
        String mobileInRequest = user.getMobile();
        String codeInRequest = user.getSmsCode();
        if(StringUtils.isEmpty(mobileInRequest)){
            throw new BusinessException(BusinessExceptionType.SYSTEM_ERROR,"手机号码不能为空");
        }
        if(StringUtils.isEmpty(codeInRequest)) {
            throw new BusinessException(BusinessExceptionType.SYSTEM_ERROR,"短信验证码不能为空");
        }
        String smsCode = RedisUtils.get(mobileInRequest);
        if (StringUtils.isEmpty(smsCode)){
            throw new BusinessException(BusinessExceptionType.SYSTEM_ERROR,"短信验证码不存在或已过期");
        }
        if(!smsCode.equals(codeInRequest)) {
            throw new BusinessException(BusinessExceptionType.SYSTEM_ERROR,"短信验证码不正确");
        }
        User byMobile = userDao.selectOneByMobile(mobileInRequest);
        if(Objects.isNull(byMobile)){
            throw new BusinessException(BusinessExceptionType.SYSTEM_ERROR,"您输入的手机号不是系统的注册用户");
        }
        RedisUtils.del(mobileInRequest);
        UserDetails userDetails = smsCodeUserDetailsService.loadUserByUsername(mobileInRequest);
        LoginUser loginUser = (LoginUser) userDetails;
        RequestInfo requestInfo = RequestHolder.get();
        UpdateIp(loginUser.getUser().getId(), requestInfo.getIp());
        return tokenUtil.getUserToken(loginUser);
    }

    @Override
    public AjaxResult logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = loginUser.getUser();
        user.setLogoutStatus(Boolean.TRUE);
        //删除redis中的值
        RedisUtils.updateValue(RedisConst.getRedisKeyUserLogin(user.getId()),loginUser);
        return AjaxResult.success("注销成功");
    }

    @Override
    public AjaxResult register(UserRegisterReq userRegisterReq) {
        User selectOneByUsername = userDao.selectOneByUsername(userRegisterReq.getUsername());
        if(Objects.nonNull(selectOneByUsername)){
            throw new UserException(UserExceptionType.USER_EXIST,"用户名已存在");
        }
        User selectOneByMobile = userDao.selectOneByMobile(userRegisterReq.getMobile());
        if(Objects.nonNull(selectOneByMobile)){
            throw new UserException(UserExceptionType.USER_EXIST,"手机号已存在");
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterReq, user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        userDao.save(user);
        return AjaxResult.success("注册成功");
    }

    @Override
    public WxMpXmlOutMessage wxMsgLoginHandler(WxMpXmlMessage wxMessage, WxMpService weixinService, String content) {
        try {
            return validUserLogin(wxMessage, weixinService, content);
        } catch (FrequencyControlException e) {
            return new TextBuilder().build("请勿频繁提交验证码,异常提交会封禁账号喔", wxMessage, weixinService);
        }
    }

    @Override
    public AjaxResult info() {
        User user = userDao.selectInfo(SecurityUtils.getUserId());
        return AjaxResult.success("查询用户信息成功",user);
    }

    // 验证用户登录
    @FrequencyControl(time = 5, count = 3, target = FrequencyControl.Target.OPENID, spEl = "#wxMessage")
    @FrequencyControl(time = 30, count = 5, target = FrequencyControl.Target.OPENID, spEl = "#wxMessage")
    @FrequencyControl(time = 300, count = 10, target = FrequencyControl.Target.OPENID, spEl = "#wxMessage")
    public WxMpXmlOutMessage validUserLogin(WxMpXmlMessage wxMessage, WxMpService weixinService, String content) {
        String captchaKey = RedisConst.getRedisKeyUserWxLoginCaptcha(content);
        // 用户验证码登录
        if (!RedisUtils.hasKey(captchaKey)){
            return new TextBuilder().build("验证码已过期或不存在", wxMessage, weixinService);
        }
        String openId = wxMessage.getFromUser();
        User user = userDao.selectOneByOpenId(openId);
        if (ObjectUtils.isEmpty(user)){
            //执行自动注册逻辑
            User register = new User();
            register.setOpenId(openId);
            register.setUsername("用户" + openId);
            register.setPassword(SecurityUtils.encryptPassword(UUID.fastUUID().toString()));
            userDao.save(register);
            doLogin(captchaKey, register);
            return new TextBuilder().build("该用户未注册 已自动注册\n登陆成功", wxMessage, weixinService);
        } else {
            doLogin(captchaKey, user);
            // 发送登录成功消息给ws客户端
            webSocketService.sendLoginSuccessMsg(content, user);
            return new TextBuilder().build("登陆成功", wxMessage, weixinService);
        }
    }

    private static void doLogin(String captchaKey, User user) {
        WxCaptchaVO wxCaptchaVO = RedisUtils.get(captchaKey,WxCaptchaVO.class);
        wxCaptchaVO.setFlag(Boolean.TRUE);
        wxCaptchaVO.setUserId(user.getId());
        RedisUtils.updateValue(captchaKey, wxCaptchaVO);
    }


}
