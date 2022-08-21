package com.roy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roy.dto.ItemDto;
import com.roy.dto.ItemsetDto;
import com.roy.pojo.Itemset;
import com.roy.service.ItemsetService;
import com.roy.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/itemset")
public class ItemsetController {

    @Autowired
    private ItemsetService itemSetService;

    @PostMapping
    public R save(@RequestBody ItemsetDto itemSetDto){
        log.info(itemSetDto.toString());
        itemSetService.saveWithItem(itemSetDto);
        return new R(true,"新增套餐成功");
    }
    @GetMapping("/page")
    public R page(int page, int pageSize){
        Page<Itemset> pageInfo = new Page<>(page,pageSize);
        itemSetService.page(pageInfo);
        return new R(true,pageInfo,"查询套餐完毕");
    }

    /*
    *** 删除itemset
    *
     */
    @DeleteMapping
    public R delete(Long[] ids){
        log.info(ids.toString());
        itemSetService.delete(ids);
        return new R(true,"DELETE SUCCEED");
    }

    /*
    数据回显
     */
    @GetMapping("{id}")
    public R getItemsetInfo(@PathVariable Long id){

        ItemsetDto itemsetDto = itemSetService.getByIdWithItem(id);
        return new R(true,itemsetDto,"查询套餐基本信息成功");
    }

    /*
    itemset数据修改
     */
    @PutMapping
    public R updateWithItem(@RequestBody ItemsetDto itemsetDto){
        log.info(itemsetDto.toString());
        itemSetService.updateWithItem(itemsetDto);
        return new R(true);
    }

    @GetMapping("/list")
    public R list(Itemset itemset){
        LambdaQueryWrapper<Itemset> lqw = new LambdaQueryWrapper<>();
        lqw.eq(itemset.getCategoryId()!=null,Itemset::getCategoryId,itemset.getCategoryId());
        lqw.eq(itemset.getStatus()!=null,Itemset::getStatus,1);
        lqw.orderByDesc(Itemset::getUpdateTime);
        List<Itemset> list = itemSetService.list(lqw);

        return new R(true,list,"success");
    }
}
