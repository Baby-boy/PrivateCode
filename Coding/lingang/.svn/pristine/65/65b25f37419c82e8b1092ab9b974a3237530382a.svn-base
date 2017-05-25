package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysStationLabel;

public interface SysStationLabelWriterMapper {
    int deleteByPrimaryKey(Integer stationLabelId);

    int insert(SysStationLabel record);

    int insertSelective(SysStationLabel record);

    int updateByPrimaryKeySelective(SysStationLabel record);

    int updateByPrimaryKey(SysStationLabel record);
    
	int deleteByObjectIdAndLabelId(@Param("objectId") Integer objectId, @Param("labelId") Integer labelId);
}