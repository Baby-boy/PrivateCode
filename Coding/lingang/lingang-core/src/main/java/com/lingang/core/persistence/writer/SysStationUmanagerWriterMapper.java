package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysStationUmanager;

public interface SysStationUmanagerWriterMapper {
    int deleteByPrimaryKey(Integer stationUmanagerId);

    int insert(SysStationUmanager record);

    int insertSelective(SysStationUmanager record);

    int updateByPrimaryKeySelective(SysStationUmanager record);

    int updateByPrimaryKey(SysStationUmanager record);
}