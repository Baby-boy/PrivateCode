package com.lingang.core.persistence.writer;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysNews;

public interface SysNewsWriterMapper {
    int deleteByPrimaryKey(Integer newsId);

    int insert(SysNews record);

    int insertSelective(SysNews record);

    int updateByPrimaryKeySelective(SysNews record);

    int updateByPrimaryKeyWithBLOBs(SysNews record);

    int updateByPrimaryKey(SysNews record);
    
    
    /******后台******/
    
    int sysInsert(SysNews sysNews);
   
    int del(@Param("newsId") Integer newsId);
    
    int update(SysNews sysNews);
}