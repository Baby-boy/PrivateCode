package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysServiceLabel;

public interface SysServiceLabelWriterMapper {
    int deleteByPrimaryKey(Integer serviceLabelId);

    int insert(SysServiceLabel record);

    int insertSelective(SysServiceLabel record);

    int updateByPrimaryKeySelective(SysServiceLabel record);

    int updateByPrimaryKey(SysServiceLabel record);
    
	int deleteByObjectIdAndLabelId(@Param("objectId") Integer objectId, @Param("labelId") Integer labelId);
	
}