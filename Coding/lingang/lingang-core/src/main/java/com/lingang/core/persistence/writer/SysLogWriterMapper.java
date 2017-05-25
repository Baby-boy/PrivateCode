package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysLog;

public interface SysLogWriterMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}