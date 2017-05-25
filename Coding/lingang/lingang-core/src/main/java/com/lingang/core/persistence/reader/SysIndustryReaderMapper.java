package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysIndustry;
import com.lingang.api.domain.pfvo.SysIndustryPfVo;
import com.lingang.api.domain.vo.SysIndustryStatisticsVo;
import com.lingang.api.domain.vo.SysIndustryVo;

public interface SysIndustryReaderMapper {

	SysIndustry selectByPrimaryKey(Integer industryId);

	SysIndustryVo selectSysIndustryVoById(@Param("industryId") Integer industryId);

	int selectSysIndustryCount(@Param("map") Map<String, Object> map);

	List<SysIndustryVo> selectSysIndustryVoPageList(@Param("map") Map<String, Object> map);

	int selectCount(@Param("map") Map<String, Object> map);

	List<SysIndustry> selectIndustryList(@Param("map") Map<String, Object> map);

	/** 后台逻辑 */
	int selectIndustryPfVoCount(@Param("map") Map<String, Object> map);

	List<SysIndustryPfVo> selectIndustryPfVoPageList(@Param("map") Map<String, Object> map);

	int selectIndustryStatisticsCountMax(@Param("map") Map<String, Object> map);

	int selectIndustryStatisticsCount(@Param("map") Map<String, Object> map);

	List<SysIndustryStatisticsVo> selectIndustryStatisticsPageList(@Param("map") Map<String, Object> map);

	int searchIndustryStatisticsCount(@Param("map") Map<String, Object> map);

}