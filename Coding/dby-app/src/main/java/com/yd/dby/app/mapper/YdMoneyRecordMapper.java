package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdMoneyRecord;

public interface YdMoneyRecordMapper {
    int deleteByPrimaryKey(Integer moneyId);

    int insert(YdMoneyRecord record);

    int insertSelective(YdMoneyRecord record);

    YdMoneyRecord selectByPrimaryKey(Integer moneyId);

    int updateByPrimaryKeySelective(YdMoneyRecord record);

    int updateByPrimaryKey(YdMoneyRecord record);
}