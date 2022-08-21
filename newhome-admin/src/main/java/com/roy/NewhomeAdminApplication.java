package com.roy;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@MapperScan("com.roy.dao")
@EnableTransactionManagement
public class NewhomeAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewhomeAdminApplication.class, args);
        log.info("项目启动");
    }

}
