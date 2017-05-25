package com.lingang.core.persistence.reader;

import com.lingang.api.domain.entity.SysStationIndustry;

public interface SysStationIndustryReaderMapper {

    SysStationIndustry selectByPrimaryKey(Integer stationIndustryId);

}