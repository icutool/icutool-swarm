package cn.icutool.constant;

/**
 * @author xietao
 */
public class RedisConst {
    public static final String REDIS_KEY_PREFIX = "icutool:";
    public static final String REDIS_KEY_USER_LOGIN = REDIS_KEY_PREFIX + "user:%d";
    public static String getRedisKeyUserLogin(Long userId){
        return String.format(REDIS_KEY_USER_LOGIN, userId);
    }
    public static final String REDIS_KEY_USER_WX_LOGIN_CAPTCHA = REDIS_KEY_PREFIX + "wx:login:captcha:%s";
    public static String getRedisKeyUserWxLoginCaptcha(String captcha){
        return String.format(REDIS_KEY_USER_WX_LOGIN_CAPTCHA, captcha);
    }
}
