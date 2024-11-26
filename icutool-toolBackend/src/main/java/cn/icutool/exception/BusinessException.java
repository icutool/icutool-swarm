package cn.icutool.exception;

import cn.icutool.exception.codeType.BusinessExceptionType;

public class BusinessException extends RuntimeException {
    //异常错误编码
    private int code ;
    //异常信息
    private String message;

    private BusinessException(){}
    public BusinessException(String message) {
        this.code = BusinessExceptionType.DEFAULT_ERROR.getCode();
        this.message = message;
    }

    public BusinessException(BusinessExceptionType exceptionTypeEnum) {
        this.code = exceptionTypeEnum.getCode();
        this.message = exceptionTypeEnum.getMessage();
    }

    public BusinessException(BusinessExceptionType exceptionTypeEnum,
                             String message) {
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}