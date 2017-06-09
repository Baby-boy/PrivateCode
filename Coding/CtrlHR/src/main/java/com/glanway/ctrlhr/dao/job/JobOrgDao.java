package com.glanway.ctrlhr.dao.job;

import com.glanway.ctrlhr.dao.BaseDao;
import com.glanway.ctrlhr.entity.dept.DeptJobORGVO;
import com.glanway.ctrlhr.entity.dept.ORGChildDetp;
import com.glanway.ctrlhr.entity.job.JobOrg;
import com.glanway.ctrlhr.entity.job.OrgDept;
import com.glanway.ctrlhr.entity.para.BasePara;
import com.glanway.ctrlhr.entity.vo.DeptOrgTreeVO;
import com.glanway.ctrlhr.entity.vo.JobOrgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobOrgDao extends BaseDao<JobOrg> {

	public int deleteByPrimaryKey(Long id);

	public int insert(JobOrg record);

	public int insertSelective(JobOrg record);

	public JobOrg selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(JobOrg record);

	public int updateByPrimaryKey(JobOrg record);

	/** 通过部门ID查找部门对应的职位编制 */
	public List<JobOrg> findJobOrgByDeptId(@Param("deptId") Long JobOrgDeptId);



	/** 查询职位编制总数 */
	public int findListCount(@Param("para") BasePara para);

	/** 查询职位编制列表 */
	public List<JobOrgVo> findList(@Param("para") BasePara para);

	/** 根据ID获取职位编制信息 */
	public JobOrgVo findOne(@Param("id") Long id);

	/** 根据职位编制名称查询职位编制是否存在 */
	public JobOrg findByName(@Param("name") String name);

	/** 保存编制管理子部门 */
	public int insertOrgDept(OrgDept orgDept);

	/** 通过职位编制ID查找子部门集合 */
	public List<OrgDept> findChindDept(@Param("id") Long id);
	
	/**获得部门对应的 编制*/
	List<DeptOrgTreeVO> getJobOrgByDeptId (Long id);
	
	/**根据编制 找到对应  管理子部门 以及 子部门下面编制*/
	List<DeptOrgTreeVO> getOrgDeptByOrgId(Long id);;
	
	/** 通过编制查询同部门父编制集合 */
	public List<JobOrg> findParentJobOrg(Long deptId, Long jobGradeId);
	
	/** 通过编制查询同部门子孙编制集合 */
	public List<JobOrg> findChildJobOrg(Long deptId, Long jobGradeId);
	
	/** 通过编制查询同部门同级编制集合 */
	public List<JobOrg> findSiblingsJobOrg(Long deptId, Long jobGradeId);
}