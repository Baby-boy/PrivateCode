package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdLink;

public interface YdLinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdLink record);

    int insertSelective(YdLink record);

    YdLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YdLink record);

    int updateByPrimaryKey(YdLink record);
}