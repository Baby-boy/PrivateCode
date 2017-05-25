package com.lingang.core.persistence.reader;

import com.lingang.api.domain.entity.SysPartnerPark;

public interface SysPartnerParkReaderMapper {
	
    SysPartnerPark selectByPrimaryKey(Integer partnerParkId);
    
}