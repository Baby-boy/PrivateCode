package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysLog;

public interface SysLogReaderMapper {

    SysLog selectByPrimaryKey(Integer logId);
    
    int selectSysLogCount(@Param("map") Map<String, Object> map);
    
    List<SysLog> selectSysLogPageList(@Param("map") Map<String, Object> map);
}