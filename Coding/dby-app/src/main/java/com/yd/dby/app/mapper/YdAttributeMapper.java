package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdAttribute;

public interface YdAttributeMapper {
    int deleteByPrimaryKey(Integer attrId);

    int insert(YdAttribute record);

    int insertSelective(YdAttribute record);

    YdAttribute selectByPrimaryKey(Integer attrId);

    int updateByPrimaryKeySelective(YdAttribute record);

    int updateByPrimaryKeyWithBLOBs(YdAttribute record);

    int updateByPrimaryKey(YdAttribute record);
}