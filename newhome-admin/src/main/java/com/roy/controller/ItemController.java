package com.roy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.roy.dto.ItemDto;
import com.roy.pojo.Category;
import com.roy.pojo.Item;
import com.roy.pojo.ItemNeed;
import com.roy.service.CategoryService;
import com.roy.service.ItemNeedService;
import com.roy.service.ItemService;
import com.roy.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemNeedService itemNeedService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R page(int page, int pageSize, String name){
        Page<Item> pageInfo = new Page<>(page,pageSize);
        Page<ItemDto> pageInfo1 = new Page<>(page,pageSize);
//        LambdaQueryWrapper<Item> lqw = new LambdaQueryWrapper();
//        lqw.like(name!=null, Item::getName, name);
//        lqw.orderByDesc(Item::getUpdateTime);
//        itemService.page(pageInfo,lqw);
//        return new R(true,pageInfo,"查询基本数据成功");
        QueryWrapper<ItemDto> query = Wrappers.query();
        query.eq("i.category_Id","c.id");
        itemService.selectAll(pageInfo1);
//        itemService.saveAll1(pageInfo1,query);
        return new R(true,pageInfo1,"查询基本数据成功");
    }

    @PostMapping
    public R save(@RequestBody ItemDto itemDto){
        itemService.saveWithNeed(itemDto);
        return new R(true,"添加服务成功");
    }

    @GetMapping("/{id}")
    public R getItemInfo(@PathVariable Long id){
//        LambdaQueryWrapper<ItemDto> lqw = new LambdaQueryWrapper<>();
//        lqw.eq(id!=null,ItemDto::getId,id);
//        ItemDto item = itemService.selectWithNeed(id);
        ItemDto item = itemService.getByIdtWithNeed(id);
        return item!=null? new R(true,item,"查询成功"):new R(false,"查询失败");
    }

    @PutMapping
    public R updateWithNeed(@RequestBody ItemDto itemDto){

        itemService.updateWithNeed(itemDto);

        return new R(true,"修改服务成功");
    }

//    @GetMapping("/list")
//    public R list(Item item){
//        LambdaQueryWrapper<Item> lqw = new LambdaQueryWrapper();
//        lqw.eq(item!=null,Item::getCategoryId,item.getCategoryId());
//        lqw.eq(Item::getStatus,1);
//        lqw.orderByDesc(Item::getSort).orderByDesc(Item::getUpdateTime);
//        List<Item> list = itemService.list(lqw);
//        return list!=null?new R(true,list,"查询服务成功"):new R(false,"查询服务失败");
//    }
    @GetMapping("/list")
    public R list(Item item){
        LambdaQueryWrapper<Item> lqw = new LambdaQueryWrapper();
        lqw.eq(item!=null,Item::getCategoryId,item.getCategoryId());
        lqw.eq(Item::getStatus,1);
        lqw.orderByDesc(Item::getSort).orderByDesc(Item::getUpdateTime);
        List<Item> list = itemService.list(lqw);

        List<ItemDto> itemDtoList = list.stream().map((i)->{
            ItemDto itemDto = new ItemDto();
            BeanUtils.copyProperties(i,itemDto);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);

            if(category!=null){
                String categoryName = category.getName();
                itemDto.setCategoryName(categoryName);
            }

            LambdaQueryWrapper<ItemNeed> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(ItemNeed::getItemId,i.getId());
            List<ItemNeed> itemNeedList = itemNeedService.list(lqw1);
            itemDto.setNeeds(itemNeedList);
            return itemDto;

        }).collect(Collectors.toList());

        return itemDtoList!=null?new R(true,itemDtoList,"查询服务成功"):new R(false,"查询服务失败");
    }

    @DeleteMapping
    public R delete(Long[] ids){

        log.info(ids.toString());
        //boolean b = itemService.removeByIds(ids);
        for (Long id : ids) {
            itemService.deleteWithNeed(id);
        }

        return new R(true,"DELETED");
        //return b?new R(true,"DELETED"): new R(false, "DELETE FAILED");

    }
}
