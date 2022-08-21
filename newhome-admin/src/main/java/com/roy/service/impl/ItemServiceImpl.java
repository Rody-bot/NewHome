package com.roy.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roy.dao.ItemDao;
import com.roy.dao.ItemNeedDao;
import com.roy.dto.ItemDto;
import com.roy.pojo.Item;
import com.roy.pojo.ItemNeed;
import com.roy.service.ItemNeedService;
import com.roy.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemDao, Item> implements ItemService {


    @Autowired
    private ItemNeedService itemNeedService;

    @Autowired
    private ItemDao itemDao;

    @Override
    public void saveWithNeed(ItemDto itemDto) {

        this.save(itemDto);

        Long id = itemDto.getId();

        List<ItemNeed> needs = itemDto.getNeeds().stream().map((need) -> {
            need.setItemId(id);
            return need;
        }).collect(Collectors.toList());

        itemNeedService.saveBatch(needs);

    }

    @Override
    public IPage<ItemDto> selectAll(IPage<ItemDto> ipage) {
        return itemDao.selectAll(ipage);
    }

    @Override
    public ItemDto selectWithNeed(Long id) {
        return itemDao.selectWithNeed(id);
    }

    @Override
    public ItemDto getByIdtWithNeed(Long id) {
        Item item = this.getById(id);
        ItemDto itemDto = new ItemDto();
        BeanUtils.copyProperties(item,itemDto);

        LambdaQueryWrapper<ItemNeed> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ItemNeed::getItemId,item.getId());
        List<ItemNeed> list = itemNeedService.list(lqw);
        itemDto.setNeeds(list);

        return itemDto;

    }

    @Override
    public void updateWithNeed(ItemDto itemDto) {
        this.updateById(itemDto);

        LambdaQueryWrapper<ItemNeed> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ItemNeed::getItemId,itemDto.getId());
        itemNeedService.remove(lqw);

        //add back
        List<ItemNeed> needs = itemDto.getNeeds().stream().map((need)->{
            need.setItemId(itemDto.getId());
            return need;
        }).collect(Collectors.toList());
        itemNeedService.saveBatch(needs);

    }

    @Override
    public void deleteWithNeed(Long id) {

        LambdaQueryWrapper<ItemNeed> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ItemNeed::getItemId,id);
        itemNeedService.remove(lqw);

        this.removeById(id);

    }


//
//    @Override
//    public IPage<ItemDto> saveAll1(IPage<ItemDto> ipage, Wrapper<ItemDto> wrapper) {
//        return itemDao.selectAll1(ipage,wrapper);
//    }
}
