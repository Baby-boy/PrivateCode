package com.glanway.ctrlhr.entity.para;

public class SignGroupPara {

	private Long id;// 考勤群组ID(编辑要用)

	private String name;// 考勤群组名称

	private String remark;// 备注

	// private Integer objectType;// 对象类型(1:选部门, 2:选人员)

	// private Integer placeType;// 地点类型(1:考勤点, 2:组织架构)

	private String jobDeptIds;// 职位部门ID(多个ID使用","分隔)

	private String jobIds;// 职位ID(多个ID使用","分隔)

	private String employeeIds;// 员工ID(多个ID使用","分隔)

	private String signPointIds;// 考勤点ID(多个ID使用","分隔)

	private String placeDeptIds;// 组织架构的部门ID(多个ID使用","分隔)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getJobDeptIds() {
		return jobDeptIds;
	}

	public void setJobDeptIds(String jobDeptIds) {
		this.jobDeptIds = jobDeptIds;
	}

	public String getJobIds() {
		return jobIds;
	}

	public void setJobIds(String jobIds) {
		this.jobIds = jobIds;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	public String getSignPointIds() {
		return signPointIds;
	}

	public void setSignPointIds(String signPointIds) {
		this.signPointIds = signPointIds;
	}

	public String getPlaceDeptIds() {
		return placeDeptIds;
	}

	public void setPlaceDeptIds(String placeDeptIds) {
		this.placeDeptIds = placeDeptIds;
	}

}
