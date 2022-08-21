package com.roy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roy.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDao extends BaseMapper<Category> {
}
