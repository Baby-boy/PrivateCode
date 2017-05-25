package com.lingang.core.persistence.reader;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysAssort;

public interface SysAssortReaderMapper {

    SysAssort selectByPrimaryKey(Integer assortId);
    
    int selectCompanyCascadeCount(@Param("assortType")Integer assortType,@Param("objId")Integer objId);

}