package com.lingang.core.persistence.reader;

import java.util.List;

import com.lingang.api.domain.entity.SysStationLabel;
import com.lingang.api.domain.vo.SysLabelsVo;

public interface SysStationLabelReaderMapper {

    SysStationLabel selectByPrimaryKey(Integer stationLabelId);

    Integer selectStationTotalCount();   
	    
	    
    List<SysLabelsVo> selectStationCountLabel();

}