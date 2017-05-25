package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysPolicy;

public interface SysPolicyWriterMapper {
    int deleteByPrimaryKey(Integer policyId);

    int insert(SysPolicy record);

    int insertSelective(SysPolicy record);

    int updateByPrimaryKeySelective(SysPolicy record);

    int updateByPrimaryKeyWithBLOBs(SysPolicy record);

    int updateByPrimaryKey(SysPolicy record);
}