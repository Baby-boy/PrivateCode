package com.glanway.ctrlhr.entity.para;

import java.util.Date;

import com.glanway.ctrlhr.util.TimeUtil;

public class EmployeePara extends BasePara {

	private String keyword;// 搜索关键字

	/**
	 * 排序字段 :
	 * "employeeNameDesc","employeeNameAsc","employeeCodeDesc","employeeCodeAsc",
	 * "deptNameDesc""deptNameAsc","jobNameDesc","jobNameAsc","jobStateDesc","jobStateAsc",
	 * "entryDateDesc","entryDateAsc","formalDateDesc","formalDateAsc","quitDateDesc","quitDateAsc"
	 */
	private String orderBy;// 排序字段

	private Long companyId;// 公司ID
	
	private Long deptId; //部门id
	
	private String deptIds;// 部门ID(备注: 多个ID使用","分隔)

	private Integer jobId;// 职位ID

	private String jobStates;// 在职状态(1试用:, 2:正式, 3:离职)(备注: 多个ID使用","分隔)

	private String entryDateFrom;// 入职日期检索-开始

	private String entryDateTo;// 入职日期检索-结束

	private String formalDateFrom;// 转正日期检索-开始

	private String formalDateTo;// 转正日期检索-结束

	private String quitDateFrom;// 离职日期检索-开始

	private String quitDateTo;// 离职日期检索-结束

	private String[] deptIdList;// id数组
	
	
	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	private String[] jobStateList;// 状态数组

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobStates() {
		return jobStates;
	}

	public void setJobStates(String jobStates) {
		this.jobStates = jobStates;
	}

	public String getEntryDateFrom() {
		return entryDateFrom;
	}

	public void setEntryDateFrom(Long entryDateFrom) {
		if (null != entryDateFrom) {
			this.entryDateFrom = TimeUtil.format(new Date(entryDateFrom));
		}
	}

	public String getEntryDateTo() {
		return entryDateTo;
	}

	public void setEntryDateTo(Long entryDateTo) {
		if (null != entryDateTo) {
			this.entryDateTo = TimeUtil.format(new Date(entryDateTo));
		}
	}

	public String getFormalDateFrom() {
		return formalDateFrom;
	}

	public void setFormalDateFrom(Long formalDateFrom) {
		if (null != formalDateFrom) {
			this.formalDateFrom = TimeUtil.format(new Date(formalDateFrom));
		}
	}

	public String getFormalDateTo() {
		return formalDateTo;
	}

	public void setFormalDateTo(Long formalDateTo) {
		if (null != formalDateTo) {
			this.formalDateTo = TimeUtil.format(new Date(formalDateTo));
		}
	}

	public String getQuitDateFrom() {
		return quitDateFrom;
	}

	public void setQuitDateFrom(Long quitDateFrom) {
		if (null != quitDateFrom) {
			this.quitDateFrom = TimeUtil.format(new Date(quitDateFrom));
		}
	}

	public String getQuitDateTo() {
		return quitDateTo;
	}

	public void setQuitDateTo(Long quitDateTo) {
		if (null != quitDateTo) {
			this.quitDateTo = TimeUtil.format(new Date(quitDateTo));
		}
	}

	public String[] getDeptIdList() {
		return deptIdList;
	}

	public void setDeptIdList(String[] deptIdList) {
		this.deptIdList = deptIdList;
	}

	public String[] getJobStateList() {
		return jobStateList;
	}

	public void setJobStateList(String[] jobStateList) {
		this.jobStateList = jobStateList;
	}

}
