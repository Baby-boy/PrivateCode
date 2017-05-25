package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysPartner;
import com.lingang.api.domain.pfvo.SysPartnerPfVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysNewAddMonthStatisticsVo;
import com.lingang.api.domain.vo.SysNewAddQuarterStatisticsVo;
import com.lingang.api.domain.vo.SysPartnerBasicsStatisticsVo;
import com.lingang.api.domain.vo.SysPartnerVo;

public interface SysPartnerReaderMapper {

	public SysPartner selectByPrimaryKey(Integer partnerId);

	public int selectPartnerCount(@Param("map") Map<String, Object> map);

	public List<SysPartnerVo> selectPartnerPageList(@Param("map") Map<String, Object> map);

	public SysPartnerVo selectPartnerVoByPartnerId(Integer partnerId);

	public int selectUserCollectCount(@Param("map") Map<String, Object> map);

	public List<SysPartnerVo> selectUserCollectPageList(@Param("map") Map<String, Object> map);

	/** 后台逻辑 */

	public int selectSysPartnerPfVoCount(@Param("map") Map<String, Object> map);

	public List<SysPartnerPfVo> selectSysPartnerPfVoPageList(@Param("map") Map<String, Object> map);

	int selectTopPartnerCount(@Param("map") Map<String, Object> map);

	public List<SysPartnerVo> selectTopPartnerVoPageList(@Param("map") Map<String, Object> map);

	/****** 后台 *****/
	public Integer querySysPartnerPfVoCount(@Param("map") Map<String, Object> map);

	public List<SysPartnerPfVo> queryAllByTop(@Param("map") Map<String, Object> map);

	public List<SysPartnerBasicsStatisticsVo> selectPartnerLevel();

	public List<SysNewAddMonthStatisticsVo> selectNewAddMonth();

	public List<SysNewAddQuarterStatisticsVo> selectNewAddQuarter();

	public int selectNewAddMonthMax();

	public int selectNewAddQuarterMax();

	Integer selectPartnerCompanyTotalCount();

	List<SysCompanyVo> selectPartnerCompanyCount();

}