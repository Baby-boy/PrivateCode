package com.glanway.iclock.entity.employee;

import java.util.Date;

import com.glanway.iclock.entity.BaseEntity;


public class Employee extends BaseEntity {

	private static final long serialVersionUID = 6257405540445588272L;

	private String name;// 员工名称

	private String code;// 员工代号

	private String mobile;// 手机号码

	private Integer sex;// 性别(1:男, 2:女.3:保密)

	private Integer jobState;// 在职状态(1试用:, 2:正式, 3:离职)

	private Date entryDate;// 入职时间

	private Date formalDate;// 转正时间

	private Date quitDate;// 离职时间

	private Long deptId;// 部门ID

	private Long jobId;// 职位ID

	private Long salaryId;// 工资档位ID

	private Long perfId;// 绩效档位ID

	private String deleted;// 是否删除(0:否, 1:是)

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

	public Integer getJobState() {
		return jobState;
	}

	public void setJobState(Integer jobState) {
		this.jobState = jobState;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Long entryDate) {
		if (null != entryDate) {
			this.entryDate = new Date(entryDate);
		}
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getFormalDate() {
		return formalDate;
	}

	public void setFormalDate(Long formalDate) {
		if (null != formalDate) {
			this.formalDate = new Date(formalDate);
		}
	}

	public void setFormalDate(Date formalDate) {
		this.formalDate = formalDate;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Long quitDate) {
		if (null != quitDate) {
			this.quitDate = new Date(quitDate);
		}
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

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Long salaryId) {
		this.salaryId = salaryId;
	}

	public Long getPerfId() {
		return perfId;
	}

	public void setPerfId(Long perfId) {
		this.perfId = perfId;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}