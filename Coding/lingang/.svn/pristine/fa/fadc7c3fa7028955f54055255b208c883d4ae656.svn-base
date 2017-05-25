package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysNews;
import com.lingang.api.domain.vo.SysNewsVo;

public interface SysNewsReaderMapper {

	SysNews selectByPrimaryKey(Integer newsId);

	int selectSysNewsCount(@Param("map") Map<String, Object> map);

	List<SysNewsVo> selectSysNewsAll(@Param("map") Map<String, Object> map);

	/****** 后台 ******/
	SysNews queryById(@Param("newsId") Integer newsId);

	int querySysNewsCount(@Param("map") Map<String, Object> map);

	List<SysNewsVo> queryAllByPage(@Param("map") Map<String, Object> map);

	SysNewsVo selectByPrimaryKeys(Integer newsId);
}