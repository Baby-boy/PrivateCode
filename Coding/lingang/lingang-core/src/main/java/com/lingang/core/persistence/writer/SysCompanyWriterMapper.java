package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysCompany;

public interface SysCompanyWriterMapper {
    int deleteByPrimaryKey(Integer companyId);

    int insert(SysCompany record);

    int insertSelective(SysCompany record);

    int updateByPrimaryKeySelective(SysCompany record);

    int updateByPrimaryKey(SysCompany record);
}