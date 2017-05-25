package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdScore;

public interface YdScoreMapper {
    int deleteByPrimaryKey(Integer scoreId);

    int insert(YdScore record);

    int insertSelective(YdScore record);

    YdScore selectByPrimaryKey(Integer scoreId);

    int updateByPrimaryKeySelective(YdScore record);

    int updateByPrimaryKey(YdScore record);
}