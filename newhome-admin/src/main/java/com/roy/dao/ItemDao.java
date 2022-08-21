package com.roy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.roy.dto.ItemDto;
import com.roy.pojo.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ItemDao extends BaseMapper<Item> {

    public IPage<ItemDto> selectAll(IPage<ItemDto> pageInfo);

    ItemDto selectWithNeed(Long id);

//    public IPage<ItemDto> selectAll1(IPage<ItemDto> pageInfo, @Param(Constants.WRAPPER) Wrapper<ItemDto> wrapper);

}
