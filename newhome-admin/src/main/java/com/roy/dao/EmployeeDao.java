package com.roy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roy.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {

    public Employee selectTest(String username);
}
