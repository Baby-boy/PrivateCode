package com.lingang.core.persistence.reader;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysStationUmanager;

public interface SysStationUmanagerReaderMapper {
	
	  SysStationUmanager selectByPrimaryKey(Integer stationUmanagerId);
	  
	  SysStationUmanager selectByStationIdAndUserAccount(@Param("stationId") Integer stationId,
				@Param("userAccount") String userAccount);
	  
}