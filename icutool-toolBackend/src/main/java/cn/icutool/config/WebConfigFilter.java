package cn.icutool.config;

import cn.icutool.handler.CollectorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author xietao
 */
@Configuration // 配置
public class WebConfigFilter extends WebMvcConfigurerAdapter{
    // 实例化拦截器
    @Resource
    private CollectorInterceptor collectorInterceptor ;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // super.addInterceptors(registry);
        // 注册自定义的拦截器passwordStateInterceptor
        registry.addInterceptor(collectorInterceptor)
            .addPathPatterns("/**");
    }
}