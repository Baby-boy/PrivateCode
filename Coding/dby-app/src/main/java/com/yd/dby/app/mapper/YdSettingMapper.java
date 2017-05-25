package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdSetting;

public interface YdSettingMapper {
    int deleteByPrimaryKey(String name);

    int insert(YdSetting record);

    int insertSelective(YdSetting record);

    YdSetting selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(YdSetting record);

    int updateByPrimaryKeyWithBLOBs(YdSetting record);
}