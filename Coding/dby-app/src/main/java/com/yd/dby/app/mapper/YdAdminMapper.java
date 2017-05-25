package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdAdmin;

public interface YdAdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(YdAdmin record);

    int insertSelective(YdAdmin record);

    YdAdmin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(YdAdmin record);

    int updateByPrimaryKey(YdAdmin record);
}