package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysPartnerType;
import com.lingang.api.domain.vo.SysPartnerTypeStatisticsVo;

public interface SysPartnerTypeReaderMapper {

	public SysPartnerType selectByPrimaryKey(Integer typeId);

	public int selectPartnerTypeCount();

	public List<SysPartnerType> selectPartnerTypeList(@Param("map") Map<String, Object> map);

	public List<SysPartnerTypeStatisticsVo> selectPartnerType();

	/** 查询所有合作类型 */
	public List<SysPartnerType> selectSysPartnerType();

	/** 后台逻辑 */
	public int selectPartnerTypePageListCount(@Param("map") Map<String, Object> map);

	public List<SysPartnerType> selectPartnerTypePageList(@Param("map") Map<String, Object> map);

}