package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysUmanager;

public interface SysUmanagerWriterMapper {
    int deleteByPrimaryKey(Integer umanagerId);

    int insert(SysUmanager record);

    int insertSelective(SysUmanager record);

    int updateByPrimaryKeySelective(SysUmanager record);

    int updateByPrimaryKey(SysUmanager record);
}