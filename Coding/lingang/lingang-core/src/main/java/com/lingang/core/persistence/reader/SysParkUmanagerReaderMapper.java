package com.lingang.core.persistence.reader;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysParkUmanager;

public interface SysParkUmanagerReaderMapper {

    SysParkUmanager selectByPrimaryKey(Integer parkUmanagerId);
    

    SysParkUmanager selectByParkIdAndUserAccount(@Param("parkId") Integer parkId,
			@Param("userAccount") String userAccount);
}