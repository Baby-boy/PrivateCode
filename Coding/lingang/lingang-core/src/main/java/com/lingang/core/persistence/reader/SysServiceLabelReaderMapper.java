package com.lingang.core.persistence.reader;

import java.util.List;

import com.lingang.api.domain.entity.SysServiceLabel;
import com.lingang.api.domain.vo.SysLabelsVo;

public interface SysServiceLabelReaderMapper {

    SysServiceLabel selectByPrimaryKey(Integer serviceLabelId);
    
    Integer selectServiceTotalCount();   
    
    
    List<SysLabelsVo> selectServiceCountLabel();  
    
    
    
}