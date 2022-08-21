package com.roy.controller;


import com.roy.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orderDetailService")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;
}
