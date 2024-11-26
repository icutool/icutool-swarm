package cn.icutool.handler;

import cn.hutool.extra.servlet.ServletUtil;
import cn.icutool.common.RequestHolder;
import cn.icutool.domain.dto.RequestInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 信息收集的拦截器
 * @author xietao
 */
@Order(1)
@Slf4j
@Component
public class CollectorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        RequestInfo info = new RequestInfo();
        String clientIP = ServletUtil.getClientIP(request);
        log.info("用户请求IP: {}", clientIP);
        info.setIp(clientIP);
        RequestHolder.set(info);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        RequestHolder.remove();
    }

}