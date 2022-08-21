package com.roy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roy.pojo.Employee;



public interface EmployeeService extends IService<Employee> {

    public Employee selectTest(String username);
}
