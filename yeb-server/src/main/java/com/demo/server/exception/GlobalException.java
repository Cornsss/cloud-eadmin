package com.demo.server.exception;

import com.demo.server.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public Result customSQLException(SQLException e){
        Result result = Result.error();
        if(e instanceof SQLIntegrityConstraintViolationException){
            result.setMessage("该数据存在关联数据，操作失败");
        }
        result.setMessage("数据库异常，操作失败");
        return result;
    }
}
