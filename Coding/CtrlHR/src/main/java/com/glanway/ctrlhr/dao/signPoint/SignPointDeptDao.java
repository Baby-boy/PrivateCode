package com.glanway.ctrlhr.dao.signPoint;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.signPoint.SignPointDept;

public interface SignPointDeptDao extends BaseDao<SignPointDept> {
	int deleteByPrimaryKey(Long id);

	int insert(SignPointDept record);

	int insertSelective(SignPointDept record);

	SignPointDept selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SignPointDept record);

	int updateByPrimaryKey(SignPointDept record);

	/** 根据考勤点ID查询关联信息 */
	public List<SignPointDept> findMany(@Param("signPointId") Long signPointId);

}