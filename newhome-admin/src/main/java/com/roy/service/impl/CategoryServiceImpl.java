package com.roy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roy.dao.CategoryDao;
import com.roy.pojo.Category;
import com.roy.pojo.Item;
import com.roy.pojo.Itemset;
import com.roy.service.CategoryService;
import com.roy.service.ItemService;
import com.roy.service.ItemsetService;
import com.roy.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemsetService itemSetService;
    @Override
    public boolean remove(Long id) {
        LambdaQueryWrapper<Item> lqwItem = new LambdaQueryWrapper<>();
        lqwItem.eq(Item::getCategoryId,id);
        int count1 = itemService.count(lqwItem);
        if(count1 > 0){
            throw new CustomException("当前分类下已关联服务，无法删除");
        }

        LambdaQueryWrapper<Itemset> lqwItemSet = new LambdaQueryWrapper<>();
        lqwItemSet.eq(Itemset::getCategoryId,id);
        int count2 = itemService.count(lqwItem);
        if(count2 > 0){
            throw new CustomException("当前分类下已关联服务套餐，无法删除");
        }

        return super.removeById(id);
    }
}
