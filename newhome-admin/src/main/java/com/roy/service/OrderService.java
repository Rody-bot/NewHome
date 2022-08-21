package com.roy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roy.pojo.Orders;

public interface OrderService extends IService<Orders> {
    void submit(Orders order);
}
