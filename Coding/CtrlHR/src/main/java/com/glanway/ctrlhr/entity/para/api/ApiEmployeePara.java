package com.glanway.ctrlhr.entity.para.api;

/**
 * 说明 : 接口服务员工列表接收参数
 * 
 * @author fuqihao
 * @version 1.0.0
 * @dateTime 2017年6月13日 下午5:15:18
 */
public class ApiEmployeePara extends ApiBasePara {

	private String keyword;// 搜索关键字

	private String deptIds;// 部门ID(备注: 多个ID使用","分隔)

	private String jobIds;// 职位ID(备注: 多个ID使用","分隔)

	private String jobStates;// 在职状态(1试用:, 2:正式, 3:离职)(备注: 多个ID使用","分隔)

	private String[] deptIdList;// 部门ID数组

	private String[] jobIdList;// 职位ID数组

	private String[] jobStateList;// 状态数组

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getJobIds() {
		return jobIds;
	}

	public void setJobIds(String jobIds) {
		this.jobIds = jobIds;
	}

	public String getJobStates() {
		return jobStates;
	}

	public void setJobStates(String jobStates) {
		this.jobStates = jobStates;
	}

	public String[] getDeptIdList() {
		return deptIdList;
	}

	public void setDeptIdList(String[] deptIdList) {
		this.deptIdList = deptIdList;
	}

	public String[] getJobIdList() {
		return jobIdList;
	}

	public void setJobIdList(String[] jobIdList) {
		this.jobIdList = jobIdList;
	}

	public String[] getJobStateList() {
		return jobStateList;
	}

	public void setJobStateList(String[] jobStateList) {
		this.jobStateList = jobStateList;
	}

}
