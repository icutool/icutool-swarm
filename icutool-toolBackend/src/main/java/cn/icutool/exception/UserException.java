package cn.icutool.exception;

import cn.icutool.exception.codeType.UserExceptionType;

/**
 * @author xietao
 */
public class UserException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private Integer code;

    private final String message;

    public UserException(String message)
    {
        this.message = message;
    }

    public UserException(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }

    public UserException(UserExceptionType userExceptionType, String message)
    {
        this.code = userExceptionType.getCode();
        this.message = message==null?userExceptionType.getDescription():message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }

}
