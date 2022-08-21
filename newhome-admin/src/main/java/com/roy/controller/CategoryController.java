package com.roy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roy.pojo.Category;
import com.roy.service.CategoryService;
import com.roy.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R save(@RequestBody Category category){
        boolean save = categoryService.save(category);
        return new R(save,"新增分类成功");
    }

    @GetMapping("/page")
    public R page(int page, int pageSize){
        Page<Category> pageInfo = new Page<>(page,pageSize);

        return new R(true,categoryService.page(pageInfo),"查询成功");
    }

    @DeleteMapping
    public R remove(Long ids){
        boolean b = categoryService.remove(ids);
        return new R(b,"分类删除成功");
    }

    @PutMapping
    public R update(@RequestBody Category category){
        boolean update = categoryService.updateById(category);
        return new R(update,update?"修改成功":"修改失败");
    }

    @GetMapping("/list")
    public R ategoryList(Category category){
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.eq(category.getType()!=null,Category::getType,category.getType());
        List<Category> list = categoryService.list(lqw);
        return new R(true,list,"查询列表成功");
    }
}
