package com.glanway.ctrlhr.service.job;

import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.job.JobOrg;
import com.glanway.ctrlhr.entity.job.OrgDept;
import com.glanway.ctrlhr.entity.para.JobOrgPara;
import com.glanway.ctrlhr.entity.vo.DeptOrgTreeVO;
import com.glanway.ctrlhr.entity.vo.JobOrgVo;
import com.glanway.ctrlhr.service.BaseService;

import java.util.List;
import java.util.Map;

public interface JobOrgService extends BaseService<JobOrg> {

	/**
	 * 说明 : 查询职位编制分页列表
	 * 
	 * @param para
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月16日 下午9:11:18
	 */
	public Page<JobOrgVo> findList(JobOrgPara para);

	/**
	 * 说明 : 新增职位编制
	 * 
	 * @param jobOrg
	 * @author 于瑞智
	 * @dateTime 2017年5月16日 下午9:11:18
	 */
	public void saveJobOrg(JobOrg jobOrg);

	/**
	 * 说明 : 根据ID获取职位编制信息
	 * 
	 * @param id
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月16日 下午9:11:18
	 */
	public JobOrgVo getInfo(Long id);

	/**
	 * 说明 : 通过职位编制ID查找子部门集合
	 *
	 * @param id
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月19日 下午5:11:18
	 */
	public List<OrgDept> getChindDept(Long id);

	/**
	 * 说明 : 编辑职位编制信息
	 * 
	 * @param jobOrg
	 * @author 于瑞智
	 * @dateTime 2017年5月16日 下午9:11:18
	 */
	public void updateJobOrg(JobOrg jobOrg);
	
	/**
	 * 说明 : 部门一览
	 *
	 * @author 王晨
	 * @dateTime 2017年5月22日 下午9:11:18
	 */
	public List<DeptOrgTreeVO> jobOrgList(Map<String,Object> parMap);
	
	/**
	 * 说明 : 通过编制查找同部门父编制集合
	 *
	 * @param jobOrg
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月28日 下午3:20:18
	 */
	public List<JobOrg> getParentJobOrg(Long deptId, Long jobGradeId);
	
	/**
	 * 说明 : 通过编制查找同部门子孙编制集合
	 *
	 * @param jobOrg
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月28日 下午3:11:18
	 */
	public List<JobOrg> getChildJobOrg(Long deptId, Long jobGradeId);
	
	/**
	 * 说明 : 通过编制查找同部门同级编制集合
	 *
	 * @param jobOrg
	 * @return
	 * @author 于瑞智
	 * @dateTime 2017年5月28日 下午7:11:18
	 */
	public List<JobOrg> getSiblingsJobOrg(Long deptId, Long jobGradeId);

}
