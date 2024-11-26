package cn.icutool.exception.codeType;

public enum UserExceptionType {
    USER_EXIST(5001,"用户已存在"),
    TOKEN_EXPIRE(5002,"token失效"),
    USER_LOGOUT(5003,"用户已注销");

    private final String description;

    private final int code;

    UserExceptionType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
