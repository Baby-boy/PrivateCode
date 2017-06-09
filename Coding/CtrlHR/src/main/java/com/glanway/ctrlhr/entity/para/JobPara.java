package com.glanway.ctrlhr.entity.para;

import com.glanway.ctrlhr.util.TimeUtil;

import java.util.Date;

public class JobPara extends BasePara {

	private String keyword;// 搜索关键字 职位名称

	/**
	 * 排序字段 :
	 * "employeeNameDesc","employeeNameAsc","employeeCodeDesc","employeeCodeAsc",
	 * "deptNameDesc""deptNameAsc","jobNameDesc","jobNameAsc","jobStateDesc","jobStateAsc",
	 * "entryDateDesc","entryDateAsc","formalDateDesc","formalDateAsc","quitDateDesc","quitDateAsc"
	 */
	private String orderBy; // 排序字段

	private String jobTypeIds; // 职位ID(备注: 多个ID使用","分隔)

	private String[] jobTypeIdList; // 职位ID数组

	private String jobGradeIds; // 职位等级ID(备注: 多个ID使用","分隔)

	private String[] jobGradeIdList; // 职位等级ID数组

	private String salaryTypeIds; // 薪资类型ID(备注: 多个ID使用","分隔)

	private String[] salaryTypeIdList; // 薪资类型ID数组

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

	public String getJobTypeIds() {
		return jobTypeIds;
	}

	public void setJobTypeIds(String jobTypeIds) {
		this.jobTypeIds = jobTypeIds;
	}

	public String[] getJobTypeIdList() {
		return jobTypeIdList;
	}

	public void setJobTypeIdList(String[] jobTypeIdList) {
		this.jobTypeIdList = jobTypeIdList;
	}

	public String getJobGradeIds() {
		return jobGradeIds;
	}

	public void setJobGradeIds(String jobGradeIds) {
		this.jobGradeIds = jobGradeIds;
	}

	public String[] getJobGradeIdList() {
		return jobGradeIdList;
	}

	public void setJobGradeIdList(String[] jobGradeIdList) {
		this.jobGradeIdList = jobGradeIdList;
	}

	public String getSalaryTypeIds() {
		return salaryTypeIds;
	}

	public void setSalaryTypeIds(String salaryTypeIds) {
		this.salaryTypeIds = salaryTypeIds;
	}

	public String[] getSalaryTypeIdList() {
		return salaryTypeIdList;
	}

	public void setSalaryTypeIdList(String[] salaryTypeIdList) {
		this.salaryTypeIdList = salaryTypeIdList;
	}






}
