package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysRegion;
import com.lingang.api.domain.vo.SysRegionStatisticsVo;

public interface SysRegionReaderMapper {

    SysRegion selectByPrimaryKey(Integer regionId);

    List<SysRegion> selectRegionAllList();

	int selectSysRegionCount(@Param("map")Map<String, Object> map);

	List<SysRegion> selectSysRegionPageList(@Param("map") Map<String, Object> map);
	
	int selectRegionParkStatisticsCount(@Param("map") Map<String, Object> map);
	
	List<SysRegionStatisticsVo> selectRegionParkStatisticsPageList(@Param("map") Map<String, Object> map);
	
	int selectRegionParkStatisticsCountMax(@Param("map") Map<String, Object> map);

}