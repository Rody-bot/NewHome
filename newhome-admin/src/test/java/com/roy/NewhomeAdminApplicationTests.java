package com.roy;

import com.roy.service.MailService;
import com.roy.utils.ValidateCodeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewhomeAdminApplicationTests {

//    @Autowired
//    private MailService mailService;
    @Test
    void sendMailTest() {
        //mailService.sendMsg("**@nyu.edu","");
    }

    @Test
    void generateCodeTest(){
        System.out.println(ValidateCodeUtils.generateValidateCode("1761879230@qq.com",4));
    }

}
