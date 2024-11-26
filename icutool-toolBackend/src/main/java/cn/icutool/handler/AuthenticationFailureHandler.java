package cn.icutool.handler;

import cn.icutool.constant.HttpStatus;
import cn.icutool.domain.vo.response.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录系统失败后 需要做的业务操作
 * extends SimpleUrlAuthenticationFailureHandler 好处是登录失败可以重新返回登录页
 * 也可以 implements AuthenticationFailureHandler
 */
@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        String errorMsg = "用户名或者密码输入错误!";
        if(exception instanceof SessionAuthenticationException){
            errorMsg = exception.getMessage();
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(OBJECT_MAPPER.writeValueAsString(
                AjaxResult.error(HttpStatus.USER_INPUT_ERROR,errorMsg)
        ));
    }
}
