package com.glanway.ctrlhr.dao.job;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.job.Job;
import com.glanway.ctrlhr.entity.job.JobGrade;
import com.glanway.ctrlhr.entity.job.JobType;
import com.glanway.ctrlhr.entity.job.SalaryType;
import com.glanway.ctrlhr.entity.job.WorkSystem;
import com.glanway.ctrlhr.entity.para.BasePara;
import com.glanway.ctrlhr.entity.vo.JobVo;
import com.glanway.ctrlhr.entity.vo.SimpleJobVo;

public interface JobDao extends BaseDao<Job> {

	public int deleteByPrimaryKey(Long id);

	public int insert(Job record);

	public int insertSelective(Job record);

	public Job selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(Job record);

	public int updateByPrimaryKey(Job record);

	/** 通过部门ID查找部门对应的职位 */
	public List<Job> findJobByDeptId(@Param("deptId") Long jobDeptId);

	/** 查询职位总数 */
	public int findListCount(@Param("para") BasePara para);

	/** 查询职位列表 */
	public List<JobVo> findList(@Param("para") BasePara para);

	/** 根据ID获取职位信息 */
	public JobVo findOne(@Param("id") Long id);

	/** 根据职位名称查询职位是否存在 */
	public Job findByName(@Param("name") String name);

	/** 此处为逻辑删除 */
	public void delete(String id);

	/** 获取职位类型集合 */
	public List<JobType> findJobType();

	/** 获取职位等级集合 */
	public List<JobGrade> findJobGrade();

	/** 获取薪资类型集合 */
	public List<SalaryType> findSalaryType();

	/** 获取工作制集合 */
	public List<WorkSystem> findWorkSystem();

	/** 根据部门查找职位 */
	public List<SimpleJobVo> findSimpleJobByDeptId(@Param("deptIdArr") String[] deptIdArr);

	/** 查询新建编制的职位下拉列表 */
	public List<SimpleJobVo> findDropDownList();

}