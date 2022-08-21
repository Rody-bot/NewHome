package com.roy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roy.pojo.Orders;
import com.roy.service.OrderService;
import com.roy.utils.BaseContext;
import com.roy.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public R submit(@RequestBody Orders orders){
        orderService.submit(orders);

        return new R(true);
    }

    @GetMapping("/userPage")
    public R page(int page, int pageSize){
        Page<Orders> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<Orders>();
        lqw.eq(Orders::getUserId, BaseContext.getCurrentId());
        List<Orders> list = orderService.list(lqw);
        return new R(true,list,"success");
    }


}
