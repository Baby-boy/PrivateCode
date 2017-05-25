package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.pfvo.SysManagerPfVo;

public interface SysManagerReaderMapper {

	SysManager selectByPrimaryKey(Integer managerId);

	int selectSysManagerCount(@Param("map") Map<String, Object> map);
    
	SysManager selectByManagerAccount(String managerAccount);

	List<SysManagerPfVo> selectSysManagerPageList(@Param("map") Map<String, Object> map);

}