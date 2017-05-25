package com.yd.dby.app.mapper;

import java.util.List;

import com.yd.dby.app.entity.YdRefundReason;

public interface YdRefundReasonMapper {
    int deleteByPrimaryKey(Integer reasonId);

    int insert(YdRefundReason record);

    int insertSelective(YdRefundReason record);

    YdRefundReason selectByPrimaryKey(Integer reasonId);

    int updateByPrimaryKeySelective(YdRefundReason record);

    int updateByPrimaryKey(YdRefundReason record);
    
    List<YdRefundReason> selectRefundReasonList();
}