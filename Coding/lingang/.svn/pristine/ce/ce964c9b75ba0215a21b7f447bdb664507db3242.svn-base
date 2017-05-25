package com.lingang.core.persistence.reader;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysIndustryUmanager;

public interface SysIndustryUmanagerReaderMapper {

    SysIndustryUmanager selectByPrimaryKey(Integer industryUmanagerId);
    
	SysIndustryUmanager selectByIndustryIdAndUserAccount(@Param("industryId") Integer industryId,
			@Param("userAccount") String userAccount);

}