package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysPartner;

public interface SysPartnerWriterMapper {
    int deleteByPrimaryKey(Integer partnerId);

    int insert(SysPartner record);

    int insertSelective(SysPartner record);

    int updateByPrimaryKeySelective(SysPartner record);

    int updateByPrimaryKeyWithBLOBs(SysPartner record);

    int updateByPrimaryKey(SysPartner record);
}