package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysParkLabel;

public interface SysParkLabelWriterMapper {
	int deleteByPrimaryKey(Integer parkLabelId);

	int insert(SysParkLabel record);

	int insertSelective(SysParkLabel record);

	int updateByPrimaryKeySelective(SysParkLabel record);

	int updateByPrimaryKey(SysParkLabel record);

	int deleteByObjectIdAndLabelId(@Param("objectId") Integer objectId, @Param("labelId") Integer labelId);
}