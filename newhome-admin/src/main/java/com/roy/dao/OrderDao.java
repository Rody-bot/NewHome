package com.roy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roy.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseMapper<Orders> {
}
