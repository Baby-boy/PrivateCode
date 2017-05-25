package com.lingang.core.persistence.reader;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysImages;

public interface SysImagesReaderMapper {

    SysImages selectByPrimaryKey(Integer imgId);

    SysImages selectSysImagesByImgTypeAndObjId(@Param("imgType")Integer imgType,@Param("objId") Integer objId);
}