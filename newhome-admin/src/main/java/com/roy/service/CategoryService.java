package com.roy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roy.pojo.Category;


public interface CategoryService extends IService<Category> {
    boolean remove(Long id);
}
