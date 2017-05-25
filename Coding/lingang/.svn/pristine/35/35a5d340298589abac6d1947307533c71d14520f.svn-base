package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysBasics;
import com.lingang.api.domain.vo.SysBasicsVo;

public interface SysBasicsReaderMapper {

	SysBasics selectByPrimaryKey(Integer basicsId);

	List<SysBasics> selectSysBasics();

	int selectSysBasicsCount(@Param("map") Map<String, Object> map);

	List<SysBasics> selectSysBasicsPageList(@Param("map") Map<String, Object> map);

	List<SysBasicsVo> selectTypeServiceStatisticsList();

	List<SysBasicsVo> selectTypePublicStatisticsList();

	List<SysBasics> selectBasicsListByBasicsType(Integer basicsType);
}