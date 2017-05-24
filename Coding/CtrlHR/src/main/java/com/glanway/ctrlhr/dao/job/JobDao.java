package com.glanway.ctrlhr.dao.job;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.job.Job;

public interface JobDao extends BaseDao<Job> {
	public int deleteByPrimaryKey(Long id);

	public int insert(Job record);

	public int insertSelective(Job record);

	public Job selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(Job record);

	public int updateByPrimaryKey(Job record);

	/** 通过部门ID查找部门对应的职位 */
	public List<Job> findJobByDeptId(@Param("deptId") Long jobDeptId);
}