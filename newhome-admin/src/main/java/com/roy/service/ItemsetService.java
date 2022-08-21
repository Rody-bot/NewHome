package com.roy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roy.dto.ItemsetDto;
import com.roy.pojo.Itemset;

public interface ItemsetService extends IService<Itemset> {

    void saveWithItem(ItemsetDto itemSetDto);

    void delete(Long[] ids);


    ItemsetDto getByIdWithItem(Long id);

    void updateWithItem(ItemsetDto itemsetDto);
}
