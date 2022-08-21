package com.roy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roy.dao.ItemsetItemDao;
import com.roy.pojo.ItemsetItem;
import com.roy.service.ItemsetItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemsetItemServiceImpl extends ServiceImpl<ItemsetItemDao, ItemsetItem> implements ItemsetItemService {
}
