package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysServicePark;

public interface SysServiceParkWriterMapper {
    int deleteByPrimaryKey(Integer serviceParkId);

    int insert(SysServicePark record);

    int insertSelective(SysServicePark record);

    int updateByPrimaryKeySelective(SysServicePark record);

    int updateByPrimaryKey(SysServicePark record);
    
    int deleteByObjectIdAndServiceId(@Param("objectId") Integer objectId, @Param("serviceId") Integer serviceId);
}