package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdNews;

public interface YdNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdNews record);

    int insertSelective(YdNews record);

    YdNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdNews record);

    int updateByPrimaryKeyWithBLOBs(YdNews record);

    int updateByPrimaryKey(YdNews record);
}