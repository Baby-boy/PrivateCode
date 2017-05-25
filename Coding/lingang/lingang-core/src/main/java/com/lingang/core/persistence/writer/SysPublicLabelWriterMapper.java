package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysPublicLabel;

public interface SysPublicLabelWriterMapper {
    int deleteByPrimaryKey(Integer publicLabelId);

    int insert(SysPublicLabel record);

    int insertSelective(SysPublicLabel record);

    int updateByPrimaryKeySelective(SysPublicLabel record);

    int updateByPrimaryKey(SysPublicLabel record);
    
	int deleteByObjectIdAndLabelId(@Param("objectId") Integer objectId, @Param("labelId") Integer labelId);
}