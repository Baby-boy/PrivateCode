package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysPartnerLabel;

public interface SysPartnerLabelWriterMapper {
    int deleteByPrimaryKey(Integer partnerLabelId);

    int insert(SysPartnerLabel record);

    int insertSelective(SysPartnerLabel record);

    int updateByPrimaryKeySelective(SysPartnerLabel record);

    int updateByPrimaryKey(SysPartnerLabel record);
    
	int deleteByObjectIdAndLabelId(@Param("objectId") Integer objectId, @Param("labelId") Integer labelId);
}