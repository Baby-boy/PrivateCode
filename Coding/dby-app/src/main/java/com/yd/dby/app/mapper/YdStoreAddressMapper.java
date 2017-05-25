package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdStoreAddress;

public interface YdStoreAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdStoreAddress record);

    int insertSelective(YdStoreAddress record);

    YdStoreAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdStoreAddress record);

    int updateByPrimaryKey(YdStoreAddress record);
}