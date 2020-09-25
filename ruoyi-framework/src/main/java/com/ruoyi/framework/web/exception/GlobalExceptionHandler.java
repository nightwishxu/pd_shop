package com.ruoyi.framework.web.exception;

import com.ruoyi.common.core.domain.Ret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.BaseException;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.exception.DemoModeException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 全局异常处理器
 * 
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public Ret baseException(BaseException e)
    {
        return Ret.error(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public Ret businessException(CustomException e)
    {
        if (StringUtils.isNull(e.getCode()))
        {
            return Ret.error(e.getMessage());
        }
        return Ret.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Ret handlerNoFoundException(Exception e)
    {
        log.error(e.getMessage(), e);
        return Ret.error(HttpStatus.NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Ret handleAuthorizationException(AccessDeniedException e)
    {
        log.error(e.getMessage());
        return Ret.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AccountExpiredException.class)
    public Ret handleAccountExpiredException(AccountExpiredException e)
    {
        log.error(e.getMessage(), e);
        return Ret.error(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Ret handleUsernameNotFoundException(UsernameNotFoundException e)
    {
        log.error(e.getMessage(), e);
        return Ret.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Ret handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return Ret.error(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Ret validatedBindException(BindException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Ret.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Ret.error(message);
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public Ret demoModeException(DemoModeException e)
    {
        return Ret.error("演示模式，不允许操作");
    }
}
