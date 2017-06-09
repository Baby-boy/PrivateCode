package com.glanway.ctrlhr.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明 : 考勤群组列表返回数据封装
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月21日 上午10:30:19
 */
public class SignGroupVo {

	private Long id;// 考勤群组id

	private String name;// 考勤群组名称

	private String remark;// 备注

	private String state;// 考勤群组状态

	private Integer employeeType;// 选择部门职位还是人员,1:部门职位,2:人员

	private Integer signType;// 选择考勤点还是组织架构,1:考勤点,2:组织架构

	private List<SignGroupDeptVo> jobDepts = new ArrayList<>();// 职位部门关联

	private List<SignGroupJobVo> jobs = new ArrayList<>();// 职位关联

	private List<SignGroupEmployeeVo> employees = new ArrayList<>();// 人员关联

	private List<SignGroupSignPointVo> signPoints = new ArrayList<>();// 考勤点关联

	private List<SignGroupDeptVo> placeDepts = new ArrayList<>();// 组织架构关联

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(Integer employeeType) {
		this.employeeType = employeeType;
	}

	public Integer getSignType() {
		return signType;
	}

	public void setSignType(Integer signType) {
		this.signType = signType;
	}

	public List<SignGroupDeptVo> getJobDepts() {
		return jobDepts;
	}

	public void setJobDepts(List<SignGroupDeptVo> jobDepts) {
		this.jobDepts = jobDepts;
	}

	public List<SignGroupJobVo> getJobs() {
		return jobs;
	}

	public void setJobs(List<SignGroupJobVo> jobs) {
		this.jobs = jobs;
	}

	public List<SignGroupSignPointVo> getSignPoints() {
		return signPoints;
	}

	public void setSignPoints(List<SignGroupSignPointVo> signPoints) {
		this.signPoints = signPoints;
	}

	public List<SignGroupEmployeeVo> getEmployees() {
		return employees;
	}

	public void setEmployees(List<SignGroupEmployeeVo> employees) {
		this.employees = employees;
	}

	public List<SignGroupDeptVo> getPlaceDepts() {
		return placeDepts;
	}

	public void setPlaceDepts(List<SignGroupDeptVo> placeDepts) {
		this.placeDepts = placeDepts;
	}

}
