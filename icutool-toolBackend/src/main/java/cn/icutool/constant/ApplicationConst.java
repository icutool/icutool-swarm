package cn.icutool.constant;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author xietao
 */
@Configuration
public class ApplicationConst {
    public static final String JWT_KEY = "xtXT1234";
    public static Long JWT_EXPIRE;
    @Value("${icutool.jwt.expire}")
    public void setJwtExpire(Long jwtExpire) {
        ApplicationConst.JWT_EXPIRE = jwtExpire * 60 * 60 * 1000;
    }
}
