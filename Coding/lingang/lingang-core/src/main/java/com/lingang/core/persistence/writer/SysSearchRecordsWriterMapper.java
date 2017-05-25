package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysSearchRecords;

public interface SysSearchRecordsWriterMapper {
    int deleteByPrimaryKey(Integer searchId);

    int insert(SysSearchRecords record);

    int insertSelective(SysSearchRecords record);

    int updateByPrimaryKeySelective(SysSearchRecords record);

    int updateByPrimaryKey(SysSearchRecords record);
}