package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdStoreNav;

public interface YdStoreNavMapper {
    int deleteByPrimaryKey(Integer navId);

    int insert(YdStoreNav record);

    int insertSelective(YdStoreNav record);

    YdStoreNav selectByPrimaryKey(Integer navId);

    int updateByPrimaryKeySelective(YdStoreNav record);

    int updateByPrimaryKey(YdStoreNav record);
}