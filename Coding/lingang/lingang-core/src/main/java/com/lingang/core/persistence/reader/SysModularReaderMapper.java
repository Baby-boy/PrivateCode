package com.lingang.core.persistence.reader;

import com.lingang.api.domain.entity.SysModular;

public interface SysModularReaderMapper {

    SysModular selectByPrimaryKey(Integer modularId);

}