package com.lingang.core.persistence.reader;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysPartnerUmanager;

public interface SysPartnerUmanagerReaderMapper {

	SysPartnerUmanager selectByPrimaryKey(Integer partnerUmanagerId);

	SysPartnerUmanager selectByPartnerIdAndUserAccount(@Param("partnerId") Integer partnerId,
			@Param("userAccount") String userAccount);

}