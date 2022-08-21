package com.roy.service.impl;

import com.roy.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class MailServiceImpl implements MailService {

//    @Autowired
//    private HttpSession httpSession;

    @Resource
    private JavaMailSender javaMailSender;
    /*
           Info required:
           sender:
           receiver:
           title:
           context:

     */
    @Value("${spring.mail.username}")
    private String sender;
    //private String receiver = "@*.com";
    private String title = "NewHome验证码";
    private String context = "测试邮件";

    @Override
    public void sendMsg(String mailAddress, String msg) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(mailAddress);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(msg);

        javaMailSender.send(simpleMailMessage);
        System.out.println("成功发送邮件");
    }
}
