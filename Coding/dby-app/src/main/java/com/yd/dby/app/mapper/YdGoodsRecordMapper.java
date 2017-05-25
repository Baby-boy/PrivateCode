package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdGoodsRecord;

public interface YdGoodsRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdGoodsRecord record);

    int insertSelective(YdGoodsRecord record);

    YdGoodsRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdGoodsRecord record);

    int updateByPrimaryKey(YdGoodsRecord record);
}