package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdType;

public interface YdTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(YdType record);

    int insertSelective(YdType record);

    YdType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(YdType record);

    int updateByPrimaryKey(YdType record);
}