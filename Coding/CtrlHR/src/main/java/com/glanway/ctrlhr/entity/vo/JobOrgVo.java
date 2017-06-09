package com.glanway.ctrlhr.entity.vo;

import com.glanway.ctrlhr.entity.dept.Dept;
import com.glanway.ctrlhr.entity.job.OrgDept;

import java.util.Date;
import java.util.List;

/**
 * 说明 : 职位编制返回数据封装
 * 
 * @author 于瑞智
 * @version 1.0.0
 * @dateTime 2017年5月17日 下午1:04:30
 */
public class JobOrgVo {

	private Long id; // ID

	private String companyName; // 公司名称

	private String deptName; // 部门名称

	private String jobName; // 职位名称

	private String workSystemName; // 工作制名称

	private List<Dept> deptList; // 管辖的子部门实体集合

	private Long organizeNum; // 编制人数

	private Long jobNum; // 在职人数

	private Long companyId; // 公司ID

	private Long deptId; // 部门ID

	private Long jobId; // 职位ID

	private Long workSystemId; // 工作制ID

	private List<OrgDept> orgDeptList; // 管辖的子部门集合

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public List<Dept> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}

	public Long getOrganizeNum() {
		return organizeNum;
	}

	public void setOrganizeNum(Long organizeNum) {
		this.organizeNum = organizeNum;
	}

	public Long getJobNum() {
		return jobNum;
	}

	public void setJobNum(Long jobNum) {
		this.jobNum = jobNum;
	}

	public String getWorkSystemName() {
		return workSystemName;
	}

	public void setWorkSystemName(String workSystemName) {
		this.workSystemName = workSystemName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getWorkSystemId() {
		return workSystemId;
	}

	public void setWorkSystemId(Long workSystemId) {
		this.workSystemId = workSystemId;
	}

	public List<OrgDept> getOrgDeptList() {
		return orgDeptList;
	}

	public void setOrgDeptList(List<OrgDept> orgDeptList) {
		this.orgDeptList = orgDeptList;
	}




}
