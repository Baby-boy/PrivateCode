package com.lingang.core.persistence.reader;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysPublicUmanager;

public interface SysPublicUmanagerReaderMapper {

    SysPublicUmanager selectByPrimaryKey(Integer publicUmanagerId);
    
    SysPublicUmanager selectByPublicIdAndUserAccount(@Param("publicId") Integer publicId,
			@Param("userAccount") String userAccount);
    
}