package com.example.demo.security;

import com.example.demo.common.BaseResponse;
import com.example.demo.common.BusinessException;
import com.example.demo.common.ErrorCode;
import com.example.demo.common.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * @author: pjialong
 * @program: demo2
 * @description: 全局异常请求处理
 * @createDate: 2023-11-22 16:37
 **/
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    public BaseResponse exceptionHandler(Exception e) {
        logger.error("系统内部异常, {}", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({RuntimeException.class})
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        logger.error("系统内部异常, {}", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({ServletException.class})
    public BaseResponse ServletExceptionHandle(ServletException e) throws ServletException {
        throw e;
    }

    @ExceptionHandler({BusinessException.class})
    public BaseResponse rooBaseExceptionHandler(BusinessException e) {
        logger.error("系统内部异常, {}", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({SQLException.class})
    public BaseResponse sqlExceptionHandle(SQLException e) {
        logger.error("sql处理异常, {}, {}",e.getMessage());
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({ParseException.class})
    public BaseResponse parseExceptionHandle(ParseException e) {
        logger.error("方法解析异常, {}, {}",  e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({NullPointerException.class})
    public BaseResponse nullPointerExceptionHandler(NullPointerException ex) {
        logger.error("空指针异常, {}", ex);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({ClassCastException.class})
    public BaseResponse classCastExceptionHandler(ClassCastException ex) {
        logger.error("类型转换异常, {}", ex);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({IOException.class})
    public BaseResponse iOExceptionHandler(IOException ex) {
        logger.error("IO异常, {}", ex);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({NoSuchMethodException.class})
    public BaseResponse noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        logger.error("未知方法异常, {}", ex);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({IndexOutOfBoundsException.class})
    public BaseResponse indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        logger.error("数组越界异常, {}", ex);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public BaseResponse methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        logger.error("方法参数类型不匹配异常, 找不到API, {}", ex);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }
}
