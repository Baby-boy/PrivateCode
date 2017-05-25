package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysIndustryLabel;

public interface SysIndustryLabelWriterMapper {
    int deleteByPrimaryKey(Integer industryLabel);

    int insert(SysIndustryLabel record);

    int insertSelective(SysIndustryLabel record);

    int updateByPrimaryKeySelective(SysIndustryLabel record);

    int updateByPrimaryKey(SysIndustryLabel record);
    
	int deleteByObjectIdAndLabelId(@Param("objectId") Integer objectId, @Param("labelId") Integer labelId);
}