package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdAttributeValue;

public interface YdAttributeValueMapper {
    int deleteByPrimaryKey(Integer attrValueId);

    int insert(YdAttributeValue record);

    int insertSelective(YdAttributeValue record);

    YdAttributeValue selectByPrimaryKey(Integer attrValueId);

    int updateByPrimaryKeySelective(YdAttributeValue record);

    int updateByPrimaryKey(YdAttributeValue record);
}