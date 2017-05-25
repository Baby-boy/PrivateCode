package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysHome;
import com.lingang.api.domain.vo.SysHomeVo;

public interface SysHomeReaderMapper {

    SysHome selectByPrimaryKey(Integer homeId);
    
    int selectSysHomeCount();
    
    List<SysHomeVo> selectSysHomePageList(@Param("map")Map<String,Object>map);
    
    SysHome selectSysHomeByUserId(Integer userId);

}