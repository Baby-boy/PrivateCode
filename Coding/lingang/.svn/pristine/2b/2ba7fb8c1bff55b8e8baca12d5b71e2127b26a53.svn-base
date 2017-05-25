package com.lingang.core.persistence.reader;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysCollect;

public interface SysCollectReaderMapper {

	SysCollect selectByPrimaryKey(Integer collectId);

	SysCollect selectByUserIdAndCollectTypeAndObjId(@Param("userId") Integer userId,
			@Param("collectType") Integer collectType, @Param("objId") Integer objId);

}