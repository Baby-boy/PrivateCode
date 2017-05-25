package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysPartnerUmanager;

public interface SysPartnerUmanagerWriterMapper {
    int deleteByPrimaryKey(Integer partnerUmanagerId);

    int insert(SysPartnerUmanager record);

    int insertSelective(SysPartnerUmanager record);

    int updateByPrimaryKeySelective(SysPartnerUmanager record);

    int updateByPrimaryKey(SysPartnerUmanager record);
}