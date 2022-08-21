package com.roy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roy.utils.R;
import com.roy.pojo.Employee;
import com.roy.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R login(HttpServletRequest httpServletRequest, @RequestBody Employee employee){
        System.out.println(employee);
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<Employee>();
        lqw.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(lqw);
        if(emp == null){
            return new R("用户名不存在");
        }
        String s = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());

        if(!emp.getPassword().equals(s)){
            return new R("密码错误");
        }

        if(emp.getStatus()==0){
            return new R("该用户已被禁用");
        }
        httpServletRequest.getSession().setAttribute("employee",emp.getId());
        return new R(true,emp,"登录成功");
    }

    @PostMapping("/logout")
    public R logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("employee");
        return new R(true);
    }

    /*
      新增employee
     */
    @PostMapping
    public R save(HttpServletRequest httpServletRequest,@RequestBody Employee employee){
        String s = DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setPassword(s);
        Long id = (Long) httpServletRequest.getSession().getAttribute("employee");
//        employee.setCreateUser(id);
//        employee.setUpdateUser(id);
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
        employeeService.save(employee);
        return new R(true,"保存成功");
    }

    @GetMapping("/page")
    public R page(int page, int pageSize, String name){
        Page pageInfo = new Page(page,pageSize);

        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper();
        lqw.like(name!=null,Employee::getName,name);
        lqw.orderByDesc(Employee::getUpdateTime);
        Page page1 = employeeService.page(pageInfo, lqw);

        return new R(true,page1,"查询成功");
    }

    @PutMapping
    public R update(HttpServletRequest httpServletRequest, @RequestBody Employee employee){
        log.info(employee.toString());
//        employee.setUpdateUser((Long)(httpServletRequest.getSession().getAttribute("employee")));
//        employee.setUpdateTime(LocalDateTime.now());

//        boolean b = employee.getId()==null?employeeService.update():employeeService.updateById(employee);
        boolean b = employeeService.updateById(employee);
        return new R(b,b?"修改成功":"修改失败");
    }

    @GetMapping("/{id}")
    public R selectById(@PathVariable Long id){
        Employee emp = employeeService.getById(id);
        Boolean b = emp==null?false:true;
        String s = emp==null?"查询失败":"查询成功";
        return new R(b,emp,s);
    }
}
