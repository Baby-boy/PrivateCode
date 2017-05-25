package com.yd.gcj.mapper;

import com.yd.gcj.entity.YdMangerTPMsg;

public interface YdMangerTPMsgMapper {
    int deleteByPrimaryKey(Integer tpmsgId);

    int insert(YdMangerTPMsg record);

    int insertSelective(YdMangerTPMsg record);

    YdMangerTPMsg selectByPrimaryKey(Integer tpmsgId);

    int updateByPrimaryKeySelective(YdMangerTPMsg record);

    int updateByPrimaryKey(YdMangerTPMsg record);
}