package com.sky.handler;


import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 用户已存在异常
     * @param ex
     * @return
     */

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
//java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '15715997225' for key 'idx_username'
        if (message.contains("Duplicate entry")) {
            log.info("捕获到数据库唯一索引冲突异常");
            String[] split = message.split(" ");
            String username=split[2];
            String msg=username + MessageConstant.ALREADY_EXISTED;
            return Result.error(msg);
        }else {
            //未知错误
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
