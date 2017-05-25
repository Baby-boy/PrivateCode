package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysCompany;

public interface SysCompanyReaderMapper {

    SysCompany selectByPrimaryKey(Integer companyId);
    
    int selectCompanyCount(@Param("map") Map<String, Object> map);
    
    List<SysCompany> selectCompanyPageList (@Param("map") Map<String, Object> map);

}