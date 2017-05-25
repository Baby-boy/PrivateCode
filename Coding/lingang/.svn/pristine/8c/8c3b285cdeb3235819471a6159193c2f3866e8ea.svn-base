package com.lingang.core.persistence.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysSearchRecords;

public interface SysSearchRecordsReaderMapper {

	SysSearchRecords selectByPrimaryKey(Integer searchId);

	/** 查询热搜词 **/
	public List<SysSearchRecords> selectHotSearch();

	public List<SysSearchRecords> selectByPrimaryKey();

	/** 查询搜索词 **/
	public SysSearchRecords selectRecords(@Param("keywords") String keywords);

}