package com.lingang.core.persistence.reader;

import com.lingang.api.domain.entity.SysParkPublic;

public interface SysParkPublicReaderMapper {

    SysParkPublic selectByPrimaryKey(Integer parkPublicId);

}