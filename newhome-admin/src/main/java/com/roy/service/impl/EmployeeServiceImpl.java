package com.roy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roy.dao.EmployeeDao;
import com.roy.pojo.Employee;
import com.roy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public Employee selectTest(String username) {
        return employeeDao.selectTest(username);
    }
}
