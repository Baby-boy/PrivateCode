package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdInformation;

public interface YdInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdInformation record);

    int insertSelective(YdInformation record);

    YdInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdInformation record);

    int updateByPrimaryKeyWithBLOBs(YdInformation record);

    int updateByPrimaryKey(YdInformation record);
}