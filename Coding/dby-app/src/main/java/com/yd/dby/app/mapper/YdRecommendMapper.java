package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdRecommend;

public interface YdRecommendMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(YdRecommend record);

    int insertSelective(YdRecommend record);

    YdRecommend selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(YdRecommend record);

    int updateByPrimaryKey(YdRecommend record);
}