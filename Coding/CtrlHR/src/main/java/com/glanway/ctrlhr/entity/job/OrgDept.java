package com.glanway.ctrlhr.entity.job;

import com.glanway.ctrlhr.entity.BaseEntity;

public class OrgDept extends BaseEntity {

	private static final long serialVersionUID = 370977013136778732L;

	private Long jobOrgId; // 职位编制ID

	private Long deptId; // 部门ID

	private String deptName; // 部门名称

	private String deleted;// 是否删除(0:未删除,1:删除)

	public Long getJobOrgId() {
		return jobOrgId;
	}

	public void setJobOrgId(Long jobOrgId) {
		this.jobOrgId = jobOrgId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}