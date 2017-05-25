package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysPublic;

public interface SysPublicWriterMapper {
    int deleteByPrimaryKey(Integer publicId);

    int insert(SysPublic record);

    int insertSelective(SysPublic record);

    int updateByPrimaryKeySelective(SysPublic record);

    int updateByPrimaryKeyWithBLOBs(SysPublic record);

    int updateByPrimaryKey(SysPublic record);
    
    int delPark(Integer publicId);
}