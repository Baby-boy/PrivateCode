package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysCollect;

public interface SysCollectWriterMapper {
    int deleteByPrimaryKey(Integer collectId);

    int insert(SysCollect record);

    int insertSelective(SysCollect record);

    int updateByPrimaryKeySelective(SysCollect record);

    int updateByPrimaryKey(SysCollect record);
}