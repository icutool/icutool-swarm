package cn.icutool.service.impl;

import cn.icutool.dao.MenuDao;
import cn.icutool.dao.UserDao;
import cn.icutool.domain.LoginUser;
import cn.icutool.domain.entity.User;
import cn.icutool.exception.UserException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
public class SmsCodeUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Resource
    private MenuDao menuDao;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        // 查询用户信息
        User user = userDao.selectOneByMobile(mobile);
        // 如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new UserException("用户名或者密码错误");
        }
        // 如果查询到用户
        List<String> list = menuDao.selectPermsByUserId(user.getId());
        // 把数据封装成UserDetails返回
        return new LoginUser(user,list);
    }
}
