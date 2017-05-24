package com.glanway.ctrlhr.entity.signGroup;

import com.glanway.ctrlhr.entity.BaseEntity;

public class SignGroupDept extends BaseEntity {

	private static final long serialVersionUID = -4689634083344538151L;

	private Long signGroupId;// 考勤群组ID

	private Integer type;// 类型(1:选部门, 2:选人员)

	private Long deptId;// 部门ID

	private Long jobId;// 职位ID

	private Long employeeId;// 员工ID

	private String deleted;// 是否删除(0.否，1.是)

	public Long getSignGroupId() {
		return signGroupId;
	}

	public void setSignGroupId(Long signGroupId) {
		this.signGroupId = signGroupId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}