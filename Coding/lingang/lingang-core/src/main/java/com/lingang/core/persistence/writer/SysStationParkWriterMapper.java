package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysStationPark;

public interface SysStationParkWriterMapper {
    int deleteByPrimaryKey(Integer stationParkId);

    int insert(SysStationPark record);

    int insertSelective(SysStationPark record);

    int updateByPrimaryKeySelective(SysStationPark record);

    int updateByPrimaryKey(SysStationPark record);
    
    int deleteByObjectIdAndStationId(@Param("objectId") Integer objectId, @Param("stationId") Integer stationId);
}