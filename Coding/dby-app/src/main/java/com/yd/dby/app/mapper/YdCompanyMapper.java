package com.yd.dby.app.mapper;

import com.yd.dby.app.entity.YdCompany;

public interface YdCompanyMapper {
    int deleteByPrimaryKey(Integer companyId);

    int insert(YdCompany record);

    int insertSelective(YdCompany record);

    YdCompany selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(YdCompany record);

    int updateByPrimaryKey(YdCompany record);
}