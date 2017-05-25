package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysStationIndustry;

public interface SysStationIndustryWriterMapper {
    int deleteByPrimaryKey(Integer stationIndustryId);

    int insert(SysStationIndustry record);

    int insertSelective(SysStationIndustry record);

    int updateByPrimaryKeySelective(SysStationIndustry record);

    int updateByPrimaryKey(SysStationIndustry record);
    
    int deleteByObjectIdAndIndustryId(@Param("objectId") Integer objectId, @Param("industryId") Integer industryId);
}