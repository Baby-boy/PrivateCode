package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdOrderRefund;

public interface YdOrderRefundMapper {
    int deleteByPrimaryKey(Integer refundId);

    int insert(YdOrderRefund record);

    int insertSelective(YdOrderRefund record);

    YdOrderRefund selectByPrimaryKey(Integer refundId);

    int updateByPrimaryKeySelective(YdOrderRefund record);

    int updateByPrimaryKey(YdOrderRefund record);
}