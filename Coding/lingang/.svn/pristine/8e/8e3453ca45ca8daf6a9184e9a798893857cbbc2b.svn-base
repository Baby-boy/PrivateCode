package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysPark;
import com.lingang.api.domain.pfvo.SysParkPfVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysParkRegionVo;
import com.lingang.api.domain.vo.SysParkStatisticsVo;
import com.lingang.api.domain.vo.SysParkVo;

public interface SysParkReaderMapper {

	SysPark selectByPrimaryKey(Integer parkId);

	int selectSysParkVoCountByRegionIdAndIndustryIds(@Param("map") Map<String, Object> map);

	List<SysParkVo> selectSysParkVoPageListByRegionIdAndIndustryIds(@Param("map") Map<String, Object> map);

	int selectSysParkDetailsCountByStationId(@Param("parkId") Integer parkId);

	SysParkVo selectSysParkDetailsByStationId(@Param("parkId") Integer parkId);

	int selectUserCollectCount(@Param("map") Map<String, Object> map);

	List<SysParkVo> selectUserCollectPageList(@Param("map") Map<String, Object> map);

	/** 后台逻辑 */
	int selectSysParkPfVoCount(@Param("map") Map<String, Object> map);

	List<SysParkPfVo> selectSysParkPfVoPageList(@Param("map") Map<String, Object> map);

	int selectParkStatisticsCountMax(@Param("map") Map<String, Object> map);

	int selectParkStationStatisticsCountMax(@Param("map") Map<String, Object> map);

	int selectParkStatisticsCount(@Param("map") Map<String, Object> map);

	int selectParkStationStatisticsCount(@Param("map") Map<String, Object> map);

	List<SysParkStatisticsVo> selectParkStatisticsPageList(@Param("map") Map<String, Object> map);

	List<SysParkStatisticsVo> selectParkStationStatisticsPageList(@Param("map") Map<String, Object> map);

	int selectParkServiceStatisticsCountMax(@Param("map") Map<String, Object> map);

	int selectParkServiceStatisticsCount(@Param("map") Map<String, Object> map);

	List<SysParkStatisticsVo> selectParkServiceStatisticsPageList(@Param("map") Map<String, Object> map);

	int selectNewParkStatisticsCountMax(@Param("map") Map<String, Object> map);

	int selectNewParkStatisticsCount(@Param("map") Map<String, Object> map);

	List<SysNewAddMonthStatisticsVo> selectNewAddMonth();

	List<SysNewAddQuarterStatisticsVo> selectNewAddQuarter();

	int selectNewParkStatisticsCountThanDate(String thanDate);

	int selectParkIndustryStatisticsCount(@Param("map") Map<String, Object> map);

	List<SysParkStatisticsVo> selectParkIndustryStatisticsPageList(@Param("map") Map<String, Object> map);

	int selectParkIndustryStatisticsCountMax(@Param("map") Map<String, Object> map);

	int selectParkPublicStatisticsCount(@Param("map") Map<String, Object> map);

	List<SysParkStatisticsVo> selectParkPublicStatisticsPageList(@Param("map") Map<String, Object> map);

	int selectParkPublicStatisticsCountMax(@Param("map") Map<String, Object> map);

	int selectNewAddMonthMax();

	int selectNewAddQuarterMax();
	
	List<SysParkRegionVo> selectParkAllList();
}