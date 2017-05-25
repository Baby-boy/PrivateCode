package com.lingang.core.persistence.reader;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysServiceUmanager;

public interface SysServiceUmanagerReaderMapper {

    SysServiceUmanager selectByPrimaryKey(Integer serviceUmanagerId);
    
	SysServiceUmanager selectByServiceIdAndUserAccount(@Param("serviceId") Integer serviceId,
			@Param("userAccount") String userAccount);

}