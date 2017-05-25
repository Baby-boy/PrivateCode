package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdBill;

public interface YdBillMapper {
    int deleteByPrimaryKey(Integer billId);

    int insert(YdBill record);

    int insertSelective(YdBill record);

    YdBill selectByPrimaryKey(Integer billId);

    int updateByPrimaryKeySelective(YdBill record);

    int updateByPrimaryKey(YdBill record);
}