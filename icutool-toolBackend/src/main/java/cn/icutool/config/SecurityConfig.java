package cn.icutool.config;

import cn.icutool.filter.JwtAuthenticationTokenFilter;
import cn.icutool.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


/**
 * @author xietao
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //创建BCryptPasswordEncoder注入容器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    @Resource
    private UserDetailsServiceImpl usernamePasswordUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 放行相关接口
                .antMatchers(
                        "/user/login",
                        "/user/hello",
                        "/user/wechat/**",
                        "/user/smsLogin",
                        "/sms/getCode",
                        "/wx/portal/public/**",
                        "/doc.html",
                        "/user/logout",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/util/**",
                        "/feedback/add",
                        "/swagger-ui/**"
                ).permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //添加过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //添加Provider
        http.authenticationProvider(usernamePasswordAuthenticationProvider());
        //配置异常处理器
        http.exceptionHandling()
                //配置认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        //允许跨域
        http.cors();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 指定用户名密码 使用usernamePasswordUserDetailsService
     */
    @Bean
    public DaoAuthenticationProvider usernamePasswordAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(usernamePasswordUserDetailsService);
        // 密码加密方式
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
