package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysServiceUmanager;

public interface SysServiceUmanagerWriterMapper {
    int deleteByPrimaryKey(Integer serviceUmanagerId);

    int insert(SysServiceUmanager record);

    int insertSelective(SysServiceUmanager record);

    int updateByPrimaryKeySelective(SysServiceUmanager record);

    int updateByPrimaryKey(SysServiceUmanager record);
}