package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdArticleClass;

public interface YdArticleClassMapper {
    int deleteByPrimaryKey(Integer acId);

    int insert(YdArticleClass record);

    int insertSelective(YdArticleClass record);

    YdArticleClass selectByPrimaryKey(Integer acId);

    int updateByPrimaryKeySelective(YdArticleClass record);

    int updateByPrimaryKey(YdArticleClass record);
}