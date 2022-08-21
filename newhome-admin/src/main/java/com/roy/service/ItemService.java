package com.roy.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roy.dto.ItemDto;
import com.roy.pojo.Item;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ItemService extends IService<Item> {

    public void saveWithNeed(ItemDto itemDto);

    IPage<ItemDto> selectAll(IPage<ItemDto> ipage);

    ItemDto selectWithNeed(Long id);

    ItemDto getByIdtWithNeed(Long id);

    void updateWithNeed(ItemDto itemDto);

    void deleteWithNeed(Long id);


//    IPage<ItemDto> saveAll1(IPage<ItemDto> ipage, Wrapper<ItemDto> wrapper);

}
