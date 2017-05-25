package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysPolicy;
import com.lingang.api.domain.vo.SysPolicyVo;

public interface SysPolicyReaderMapper {

	SysPolicyVo selectByPrimaryKey(Integer policyId);

	int selectSysPolicyCount(@Param("map") Map<String, Object> map);

	List<SysPolicy> selectSysPolicyAll(@Param("map") Map<String, Object> map);

	/** 后台 **/
	int querySysPolicyCount(@Param("map") Map<String, Object> map);

	SysPolicyVo selectByKey(Integer policyId);

	List<SysPolicy> queryAllByPage(@Param("map") Map<String, Object> map);

}