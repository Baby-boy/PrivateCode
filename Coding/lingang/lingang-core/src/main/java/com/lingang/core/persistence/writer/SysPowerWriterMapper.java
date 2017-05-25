package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysPower;

public interface SysPowerWriterMapper {
    int deleteByPrimaryKey(Integer powerId);

    int insert(SysPower record);

    int insertSelective(SysPower record);

    int updateByPrimaryKeySelective(SysPower record);

    int updateByPrimaryKey(SysPower record);
}