package com.roy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roy.pojo.Employee;
import com.roy.pojo.User;
import com.roy.service.MailService;
import com.roy.service.UserService;
import com.roy.utils.R;
import com.roy.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @PostMapping("/sendMsg")
    public R sendMsg(@RequestBody User user, HttpSession httpSession){

        String phone = user.getPhone();
        String code = null;
        if(phone.contains("@")){
             code = ValidateCodeUtils.generateValidateCode(phone, 6);
        }
        log.info(code);
        //mailService.sendMsg(phone,code);
        //存到本地
        //存到session
        httpSession.setAttribute(phone,code);
        return new R(true,"验证码已发送");
    }

    @PostMapping("/login")
    public R login(@RequestBody Map map,HttpSession httpSession){

        String phone =  map.get("phone").toString();

        String code = map.get("code").toString();

        Object codeInSession = httpSession.getAttribute(phone);
        User user = null;
        if (codeInSession!=null&&codeInSession.equals(code)){
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
            lqw.eq(User::getPhone,phone);

             user = userService.getOne(lqw);
            if(user==null){
                user = new User();
                user.setPhone(phone);
                userService.save(user);
            }
            httpSession.setAttribute("user",user.getId());
            return new R(true,user,"登录成功");
        }
        return new R(false,"登录失败");
    }
}
