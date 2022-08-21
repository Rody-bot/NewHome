package com.roy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roy.dao.ItemsetDao;
import com.roy.dto.ItemsetDto;
import com.roy.pojo.Itemset;
import com.roy.pojo.ItemsetItem;
import com.roy.service.ItemsetItemService;
import com.roy.service.ItemsetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ItemsetServiceImpl extends ServiceImpl<ItemsetDao, Itemset> implements ItemsetService {
    /*
    新增套餐 同时保存套餐和服务套餐关系表
     */

    @Autowired
    private ItemsetItemService itemSetItemService;


    @Override
    public void saveWithItem(ItemsetDto itemSetDto) {
        this.save(itemSetDto);

        List<ItemsetItem> itemsetItems = itemSetDto.getItemsetItems().stream().map((itemSetItem) -> {
            itemSetItem.setItemsetId(itemSetDto.getId());
            return itemSetItem;
        }).collect(Collectors.toList());
        itemSetItemService.saveBatch(itemsetItems);
    }

    /*
    删除itemset操作
    需要删除itemset表和itemsetitem对应数据
     */
    @Override
    public void delete(Long[] ids) {
        //删除套餐服务关系表
        LambdaQueryWrapper<ItemsetItem> lqw = new LambdaQueryWrapper<>();
        for (Long id : ids) {
            log.info(id.getClass().toString());
            lqw.eq(ItemsetItem::getItemsetId,id);
            itemSetItemService.remove(lqw);
            this.removeById(id);
        }
        // 删除套餐

    }

    //    数据回显
    
    @Override
    public ItemsetDto getByIdWithItem(Long id) {
        Itemset itemset = this.getById(id);

        ItemsetDto itemsetDto = new ItemsetDto();

        BeanUtils.copyProperties(itemset,itemsetDto);

        LambdaQueryWrapper<ItemsetItem> lqw = new LambdaQueryWrapper<>();

        lqw.eq(ItemsetItem::getItemsetId,id);

        List<ItemsetItem> list = itemSetItemService.list(lqw);

        itemsetDto.setItemsetItems(list);

        return itemsetDto;
    }

    @Override
    public void updateWithItem(ItemsetDto itemsetDto) {
        this.updateById(itemsetDto);

        LambdaQueryWrapper<ItemsetItem> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ItemsetItem::getItemsetId,itemsetDto.getId());
        itemSetItemService.remove(lqw);
        List<ItemsetItem> itemsetItems = itemsetDto.getItemsetItems().stream().map((itemsetItem -> {
            itemsetItem.setItemsetId(itemsetDto.getId());
            return itemsetItem;
        })).collect(Collectors.toList());

        itemSetItemService.saveBatch(itemsetItems);

    }


}
