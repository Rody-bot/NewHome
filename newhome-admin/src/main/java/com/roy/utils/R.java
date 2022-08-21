package com.roy.utils;

import com.roy.pojo.Employee;
import lombok.Data;

@Data
public class R {
    private Boolean flag;
    private Object data;
    private String msg;
    public R(Boolean flag){
        this.flag = flag;
    }

    public R(String msg) {
        this.msg = msg;
    }

    public R(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public R(boolean flag, Object emp, String msg) {
        this.flag = flag;
        this.data = emp;
        this.msg = msg;
    }
}
