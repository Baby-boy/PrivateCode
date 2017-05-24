package com.glanway.ctrlhr.entity.vo;

import java.util.Date;

/**
 * 说明 : 员工返回数据封装
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月18日 下午6:04:30
 */
public class EmployeeVo {

	private Long id; // ID

	private String name;// 员工名称

	private String code;// 员工代号

	private Integer jobState;// 在职状态(1试用:, 2:正式, 3:离职)

	private Date entryDate;// 入职时间

	private Date formalDate;// 转正时间

	private Date quitDate;// 离职时间

	private Long deptId;// 部门ID

	private String deptName;// 部门名称

	private Long jobId;// 职位ID

	private String jobName;// 职位名称

	// 获取编辑信息数据相关字段

	private String mobile;// 手机号码

	private Integer sex;// 性别(1:男, 2:女.3:保密)

	private Long salaryId;// 工资档位ID

	private String salaryName;// 工资档位ID

	private Long perfId;// 绩效档位ID

	private String perfName;// 绩效档位ID

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getJobState() {
		return jobState;
	}

	public void setJobState(Integer jobState) {
		this.jobState = jobState;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getFormalDate() {
		return formalDate;
	}

	public void setFormalDate(Date formalDate) {
		this.formalDate = formalDate;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
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

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Long salaryId) {
		this.salaryId = salaryId;
	}

	public String getSalaryName() {
		return salaryName;
	}

	public void setSalaryName(String salaryName) {
		this.salaryName = salaryName;
	}

	public Long getPerfId() {
		return perfId;
	}

	public void setPerfId(Long perfId) {
		this.perfId = perfId;
	}

	public String getPerfName() {
		return perfName;
	}

	public void setPerfName(String perfName) {
		this.perfName = perfName;
	}

}
