package com.glanway.ctrlhr.entity.vo;

import java.util.Date;

/**
 * 说明 : 临时考勤记录导出(后期添加定时任务) TODO 替换为定时任务
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月24日 下午12:01:30
 */
public class SignVo {

	private Long id; // ID

	private String employeeName;// 员工名称

	private String employeeCode;// 员工代号

	private String deptName;// 部门名称

	private String jobName;// 职位名称

	private Date time;// 月考勤开始时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
