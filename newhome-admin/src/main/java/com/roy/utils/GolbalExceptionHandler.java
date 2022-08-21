package com.roy.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GolbalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R exceptionHandler(SQLIntegrityConstraintViolationException ex){
        if(ex.getMessage().contains("Duplicate entry")){
            return new R(ex.getMessage().split(" ")[2]+"已存在");
        }
        return new R("UNKNOWN ERROR");
    }

    @ExceptionHandler(CustomException.class)
    public R customerExceptionHandler(CustomException ex){

        return new R(ex.getMessage());
    }

}
