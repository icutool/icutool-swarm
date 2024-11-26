package cn.icutool.utils;

import cn.icutool.constant.RedisConst;
import cn.icutool.dao.MenuDao;
import cn.icutool.dao.UserDao;
import cn.icutool.domain.LoginUser;
import cn.icutool.domain.bo.UserJwtBO;
import cn.icutool.domain.entity.User;
import cn.icutool.domain.vo.response.AjaxResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TokenUtil {
    @Resource
    private MenuDao menuDao;
    @Resource
    private UserDao userDao;

    public AjaxResult getUserToken(LoginUser loginUser) {
        User user = loginUser.getUser();
        user.setLogoutStatus(Boolean.FALSE);
        Map<String, String> map = createToken(user);
        //把完整的用户信息存入redis  userid作为key
        RedisUtils.set(RedisConst.getRedisKeyUserLogin(user.getId()), loginUser);
        return AjaxResult.success("登录成功", map);
    }


    public AjaxResult getUserToken(Long userId) {
        LoginUser loginUser = new LoginUser();
        User user = userDao.getById(userId);
        user.setLogoutStatus(Boolean.FALSE);
        return createUserTokenNoLoginUser(loginUser, user);
    }

    private AjaxResult createUserTokenNoLoginUser(LoginUser loginUser, User user) {
        loginUser.setUser(user);
        List<String> perms = menuDao.selectPermsByUserId(user.getId());
        loginUser.setPermissions(perms);
        Map<String, String> map = createToken(user);
        //把完整的用户信息存入redis  userid作为key
        RedisUtils.set(RedisConst.getRedisKeyUserLogin(user.getId()), loginUser);
        return AjaxResult.success("登录成功", map);
    }

    private static Map<String, String> createToken(User user) {
        UserJwtBO userJwtBO = new UserJwtBO(user.getId(), user.getUsername());
        String jwt = JwtUtil.createJWT(userJwtBO);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return map;
    }
}
