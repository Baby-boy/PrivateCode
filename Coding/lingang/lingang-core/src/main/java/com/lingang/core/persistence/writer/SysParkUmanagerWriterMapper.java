package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysParkUmanager;

public interface SysParkUmanagerWriterMapper {
    int deleteByPrimaryKey(Integer parkUmanagerId);

    int insert(SysParkUmanager record);

    int insertSelective(SysParkUmanager record);

    int updateByPrimaryKeySelective(SysParkUmanager record);

    int updateByPrimaryKey(SysParkUmanager record);
}