package com.scoks.order.exception;

import org.apache.log4j.Logger;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

/**
 * 统一异常处理
 *
 * @author julius
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ShiroException.class)
    public Result handleShiroException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        logger.error("shiro执行出错：" + eName);
        return Result.error(ResultStatus.ERROR);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public Result page401(UnauthenticatedException e) {
        logger.error("未经授权:" + e.getMessage());
        return Result.error(ResultStatus.NOT_LOGGED_IN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result page403(UnauthorizedException e) {
        logger.error("无权限访问:" + e.getMessage());
        return Result.error(ResultStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResultException.class)
    public Result error(ResultException e) {
        return Result.error(e.getStatus());
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public Result error(DuplicateKeyException e) {
        return Result.error(ResultStatus.DUPLICATE_ERR);
    }

    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        logger.error("系统异常:" + e.getMessage(), e);
        return Result.error(ResultStatus.ERROR);
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result handle(ValidationException exception) {
        Result<Object> error = Result.error(ResultStatus.PARAM_ERR);
        error.setMessage(exception.getMessage());
        return error;
    }

}
