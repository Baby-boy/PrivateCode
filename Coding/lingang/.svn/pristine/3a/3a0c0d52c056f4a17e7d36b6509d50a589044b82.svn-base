package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysPublic;
import com.lingang.api.domain.pfvo.SysPublicPfVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysPublicVo;

public interface SysPublicReaderMapper {

	SysPublic selectByPrimaryKey(Integer publicId);

	int selectSysPublicVoCountByRegionIdAndBasicsId(@Param("map") Map<String, Object> map);

	List<SysPublicVo> selectSysPublicVoPageListByRegionIdAndBasicsId(@Param("map") Map<String, Object> map);

	int selectSysPublicDetailsCountByPublicId(@Param("publicId") Integer publicId);

	SysPublicVo selectSysPublicDetailsByPublicId(@Param("publicId") Integer publicId);

	int selectUserCollectCount(@Param("map") Map<String, Object> map);

	List<SysPublicVo> selectUserCollectPageList(@Param("map") Map<String, Object> map);

	/** 后台逻辑 */
	int selectSysPublicPfVoCount(@Param("map") Map<String, Object> map);

	List<SysPublicPfVo> selectSysPublicPfVoPageList(@Param("map") Map<String, Object> map);

	SysPublicVo selectSysPublicDetails(@Param("publicId") Integer publicId);

	List<SysNewAddMonthStatisticsVo> selectNewAddMonth();

	List<SysNewAddQuarterStatisticsVo> selectNewAddQuarter();

	int selectNewAddMonthMax();

	int selectNewAddQuarterMax();
	
    Integer selectPublicCompanyTotalCount();  
    
    List<SysCompanyVo> selectPublicCompanyCount();  

}