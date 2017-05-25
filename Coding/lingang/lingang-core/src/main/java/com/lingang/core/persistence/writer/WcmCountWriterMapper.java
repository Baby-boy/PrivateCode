package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.WcmCount;

public interface WcmCountWriterMapper {

	public void insertSelective(WcmCount wcmCount);

	public void updateSelective(WcmCount wc);
    
}