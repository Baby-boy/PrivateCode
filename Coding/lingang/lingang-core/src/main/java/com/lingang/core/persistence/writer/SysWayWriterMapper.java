package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysWay;

public interface SysWayWriterMapper {
    int deleteByPrimaryKey(Integer wayId);

    int insert(SysWay record);

    int insertSelective(SysWay record);

    int updateByPrimaryKeySelective(SysWay record);

    int updateByPrimaryKey(SysWay record);
}