package cn.icutool.filter;

import cn.icutool.domain.LoginUser;
import cn.icutool.domain.entity.User;
import cn.icutool.exception.UserException;
import cn.icutool.service.UserService;
import cn.icutool.utils.JwtUtil;
import cn.icutool.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        String apiKey = request.getHeader("api-key");
        if (!StringUtils.hasText(token) && !StringUtils.hasText(apiKey)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        LoginUser loginUser;
        //解析apikey
        if (StringUtils.hasText(apiKey) && !StringUtils.hasText(token)) {
            //放行
            loginUser = filterByApiKey(apiKey);
        } else {
            Long userId;
            try {
                Claims claims = JwtUtil.parseJWT(token);
                userId = claims.get("id",Long.class);
            } catch (Exception e) {
                throw new RuntimeException("token非法");
            }
            //从redis中获取用户信息
            String value = RedisUtils.get(RedisConst.getRedisKeyUserLogin(userId));
            if(StringUtils.isEmpty(value)){
                throw new InvalidCookieException("用户身份失效，请重新登录");
            }
            loginUser = JSON.parseObject(value, LoginUser.class);
            User user = loginUser.getUser();
            if (user.getLogoutStatus()){
                throw new UserException("用户已注销，请重新登录");
            }
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }

    private LoginUser filterByApiKey(String apiKey){
        LoginUser loginUser = new LoginUser();
        User userInfoByApiKey = userService.getUserInfoByApiKey(apiKey);
        loginUser.setUser(userInfoByApiKey);
        loginUser.setPermissions(Arrays.asList("api:hello"));
        return loginUser;
    }

}
