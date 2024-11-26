package cn.icutool.domain.enums;

/**
 * 公共返回对象枚举
 * @author : xietao
 */

public enum RespBeanEnum {

    //通用
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "异常"),

    //登录模块
    LOGIN_ERROR(500, "用户名或者密码不正确"),
    USER_NOT_EXIST(500, "用户不存在"),
    MOBILE_ERROR(500, "手机号码格式不正确"),
    BIND_ERROR(500, "参数校验异常"),
    MOBILE_NOT_EXIST(500, "手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500, "更新密码失败"),
    NOT_ADMIN(500, "无权限"),
    NO_PERMISSION(500, "该用户无此接口无权限"),
    USERNAME_EXIST(500, "用户名已存在"),
    ;

    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    RespBeanEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
