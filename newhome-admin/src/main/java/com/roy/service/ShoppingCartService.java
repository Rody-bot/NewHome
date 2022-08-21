package com.roy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roy.pojo.ShoppingCart;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ShoppingCartService extends IService<ShoppingCart> {
}
