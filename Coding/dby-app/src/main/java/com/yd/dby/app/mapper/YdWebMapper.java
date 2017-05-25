package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdWeb;

public interface YdWebMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdWeb record);

    int insertSelective(YdWeb record);

    YdWeb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdWeb record);

    int updateByPrimaryKey(YdWeb record);
}