package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysAssort;

public interface SysAssortWriterMapper {
	int deleteByPrimaryKey(Integer assortId);

	int insert(SysAssort record);

	int insertSelective(SysAssort record);

	int updateByPrimaryKeySelective(SysAssort record);

	int updateByPrimaryKey(SysAssort record);

	int deleteCompanyCascade(@Param("assortType") Integer assortType, @Param("objId") Integer objId,
			@Param("companyId") Integer companyId);
}