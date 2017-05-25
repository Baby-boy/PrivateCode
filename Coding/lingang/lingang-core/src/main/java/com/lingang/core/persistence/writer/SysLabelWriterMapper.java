package com.lingang.core.persistence.writer;

import com.lingang.api.domain.entity.SysLabel;

public interface SysLabelWriterMapper {
    int deleteByPrimaryKey(Integer labelId);

    int insert(SysLabel record);

    int insertSelective(SysLabel record);

    int updateByPrimaryKeySelective(SysLabel record);

    int updateByPrimaryKey(SysLabel record);
}