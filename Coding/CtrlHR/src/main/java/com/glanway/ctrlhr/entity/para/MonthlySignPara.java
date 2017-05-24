package com.glanway.ctrlhr.entity.para;

public class MonthlySignPara extends BasePara {

	private String keyword;// 搜索关键字

	/**
	 * 排序字段 :
	 * “employeeNameDesc”,”employeeNameAsc”,”employeeCodeDesc”,”employeeCodeAsc”,
	 * ”deptNameDesc””deptNameAsc”,”jobNameDesc”,”jobNameAsc”,"dateFromDesc","dateFromAsc",
	 * "dateToDesc","dateToAsc","daysDesc" ,"daysAsc","hoursDesc","hoursAsc"
	 */
	private String orderBy;// 排序字段

	private String dateStart;// 开始考勤时间

	private String dateEnd;// 结束考勤时间

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

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

}
