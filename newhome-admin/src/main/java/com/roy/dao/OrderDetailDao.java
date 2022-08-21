package com.roy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roy.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailDao extends BaseMapper<OrderDetail> {
}
