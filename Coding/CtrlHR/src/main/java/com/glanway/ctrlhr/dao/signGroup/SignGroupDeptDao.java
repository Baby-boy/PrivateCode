package com.glanway.ctrlhr.dao.signGroup;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.signGroup.SignGroupDept;

public interface SignGroupDeptDao extends BaseDao<SignGroupDept> {

	public int deleteByPrimaryKey(Long id);

	public int insert(SignGroupDept record);

	public int insertSelective(SignGroupDept record);

	public SignGroupDept selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(SignGroupDept record);

	public int updateByPrimaryKey(SignGroupDept record);

	/** 根据考勤群组ID查询关联信息 */
	public List<SignGroupDept> findMany(@Param("id") Long id);
}