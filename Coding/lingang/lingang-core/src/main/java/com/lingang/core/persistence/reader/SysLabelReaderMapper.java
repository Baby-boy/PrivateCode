package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysLabel;

public interface SysLabelReaderMapper {

	SysLabel selectByPrimaryKey(Integer labelId);

	List<SysLabel> selectSysLabelByType();

	int selectSysLabelCount(@Param("map") Map<String, Object> map);

	List<SysLabel> selectSysLabelPageList(@Param("map") Map<String, Object> map);

}