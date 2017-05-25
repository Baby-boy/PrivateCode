package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdDictType;

public interface YdDictTypeMapper {
    int deleteByPrimaryKey(Integer dtId);

    int insert(YdDictType record);

    int insertSelective(YdDictType record);

    YdDictType selectByPrimaryKey(Integer dtId);

    int updateByPrimaryKeySelective(YdDictType record);

    int updateByPrimaryKey(YdDictType record);
}