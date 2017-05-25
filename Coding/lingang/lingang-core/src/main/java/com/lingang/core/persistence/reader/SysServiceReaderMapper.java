package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysServiceWithBLOBs;
import com.lingang.api.domain.pfvo.SysServicePfVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysServiceVo;

public interface SysServiceReaderMapper {

	SysServiceWithBLOBs selectByPrimaryKey(Integer serviceId);

	int selectServiceCount(@Param("map") Map<String, Object> map);

	List<SysServiceVo> selectServicePageList(@Param("map") Map<String, Object> map);

	SysServiceVo selectServiceVoByServiceId(Integer serviceId);

	int selectUserCollectCount(@Param("map") Map<String, Object> map);

	List<SysServiceVo> selectUserCollectPageList(@Param("map") Map<String, Object> map);

	/** 后台逻辑 */
	int selectServicePfVoCount(@Param("map") Map<String, Object> map);

	List<SysServicePfVo> selectServicePfVoPageList(@Param("map") Map<String, Object> map);

	List<SysNewAddMonthStatisticsVo> selectNewAddMonth();

	List<SysNewAddQuarterStatisticsVo> selectNewAddQuarter();

	int selectNewAddMonthMax();

	int selectNewAddQuarterMax();
}