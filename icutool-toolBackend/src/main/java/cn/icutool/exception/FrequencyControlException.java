package cn.icutool.exception;

import cn.icutool.exception.codeType.FrequencyControlExceptionType;
import lombok.Data;

/**
 * 自定义限流异常
 * @author xietao
 */
@Data
public class FrequencyControlException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    public FrequencyControlException() {
        super();
    }

    public FrequencyControlException(String message) {
        super(message);
        this.message = message;
    }

    public FrequencyControlException(FrequencyControlExceptionType error) {
        super(error.getMessage());
        this.code = error.getCode();
        this.message = error.getMessage();
    }
}
