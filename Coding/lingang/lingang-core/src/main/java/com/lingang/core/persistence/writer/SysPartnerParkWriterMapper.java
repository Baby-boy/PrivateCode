package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysPartnerPark;

public interface SysPartnerParkWriterMapper {
    int deleteByPrimaryKey(Integer partnerParkId);

    int insert(SysPartnerPark record);

    int insertSelective(SysPartnerPark record);

    int updateByPrimaryKeySelective(SysPartnerPark record);

    int updateByPrimaryKey(SysPartnerPark record);
}