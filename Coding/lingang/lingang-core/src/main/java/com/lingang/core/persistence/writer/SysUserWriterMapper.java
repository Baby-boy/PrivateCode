package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysUser;

public interface SysUserWriterMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}