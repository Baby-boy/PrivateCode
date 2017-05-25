package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.pfvo.SysStationPfVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysLabelsVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysStationVo;

public interface SysStationReaderMapper {

	SysStationVo selectByPrimaryKey(Integer stationId);

	int selectSysStationCount(@Param("map") Map<String, Object> map);

	List<SysStationVo> selectSysStationPageList(@Param("map") Map<String, Object> map);

	/** 多点布局列表 */
	List<SysStationVo> selectMoreSysStationPageList(@Param("map") Map<String, Object> map);

	// SysStationVo selectSysStationVoByStationId(Integer stationId);

	int selectUserCollectCount(@Param("map") Map<String, Object> map);

	List<SysStationVo> selectUserCollectPageList(@Param("map") Map<String, Object> map);

	/** 后台逻辑 */
	int selectSysStationPfVoCount(@Param("map") Map<String, Object> map);

	List<SysStationPfVo> selectSysStationPfVoPageList(@Param("map") Map<String, Object> map);

	List<SysNewAddMonthStatisticsVo> selectNewAddMonth();

	List<SysNewAddQuarterStatisticsVo> selectNewAddQuarter();

	int selectStationCount(@Param("sysNum") Integer sysNum);

	/****** 后台 *****/
	int querySysStationCount(@Param("map") Map<String, Object> map);

	List<SysStationVo> queryAllByPage(@Param("map") Map<String, Object> map);

	int selectNewAddMonthMax();

	int selectNewAddQuarterMax();

	Integer selectStationLabelTotalCount();

	List<SysLabelsVo> selectStationCountLabel();

	/** 统计 **/
	Integer selectStationCompanyTotalCount();

	List<SysCompanyVo> selectStationCompanyCount();

	int selectTopStationCount(@Param("map") Map<String, Object> map);

	List<SysStationVo> selectTopStationVoPageList(@Param("map") Map<String, Object> map);

}