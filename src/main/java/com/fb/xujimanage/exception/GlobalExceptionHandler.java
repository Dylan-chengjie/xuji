package com.fb.xujimanage.exception;

import com.fb.xujimanage.util.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: xujimanage
 * @description: 异常统一处理
 * @author: chengjie
 * @date: Created in 2020/8/27 16:34
 **/

@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数解析失败异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public CommonResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        logger.error(request.getRequestURI() + ":参数解析失败", e);
        return CommonResult.build(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * 不支持当前请求方法异常处理
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public CommonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        logger.error(request.getRequestURI() + ":不支持当前请求方法", e);
        return CommonResult.build(HttpStatus.METHOD_NOT_ALLOWED.value(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        StringBuffer errorStr = new StringBuffer();
        errors.forEach(error -> {
            errorStr.append(error.getDefaultMessage() + ";");
        });
        return CommonResult.fail(errorStr);
    }

    /**
     * 项目运行异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult handleException(Exception e, HttpServletRequest request) {
        logger.error(request.getRequestURI() + ":服务运行异常", e);
        return CommonResult.build(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务异常,请稍候再试");
    }

    /**
     * Validated注解校验传参异常
     */
    @ExceptionHandler(BindException.class)
    public CommonResult bindExceptionHandler(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMsg = "";
        if (bindingResult != null && bindingResult.getFieldError() != null) {
            errorMsg = bindingResult.getFieldError().getDefaultMessage();
        }
        return CommonResult.fail(errorMsg);
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(VerifyException.class)
    @ResponseBody
    public CommonResult handleException(VerifyException e, HttpServletRequest request) {
        logger.error(request.getRequestURI() + ":自定义内部异常", e.getMessage());
        return CommonResult.fail(e.getMessage());
    }
}
