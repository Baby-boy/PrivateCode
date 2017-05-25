package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysParkIndustry;

public interface SysParkIndustryWriterMapper {
    int deleteByPrimaryKey(Integer parkIndustryId);

    int insert(SysParkIndustry record);

    int insertSelective(SysParkIndustry record);

    int updateByPrimaryKeySelective(SysParkIndustry record);

    int updateByPrimaryKey(SysParkIndustry record);
    
    int deleteByObjectIdAndIndustryId(@Param("objectId") Integer objectId, @Param("industryId") Integer industryId);
}