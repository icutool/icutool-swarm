package cn.icutool.exception;

import cn.icutool.domain.enums.RespBeanEnum;
import cn.icutool.domain.vo.response.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xietao
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 未知异常
     * @param  e
     * @return AjaxResult
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult exceptionHandler(Exception e) {
        log.error("Exception:{}",e.getMessage());
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public AjaxResult businessExceptionHandler(Exception e) {
        BusinessException ex = (BusinessException) e;
        return AjaxResult.error(ex.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public AjaxResult userExceptionHandler(Exception e) {
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public AjaxResult BindExceptionHandler(Exception e) {
        BindException ex = (BindException) e;
        return AjaxResult.error(RespBeanEnum.BIND_ERROR.getCode(),"参数校验异常：" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public AjaxResult AccessDeniedExceptionHandler(Exception e) {
        return AjaxResult.error(RespBeanEnum.NO_PERMISSION);
    }
}
