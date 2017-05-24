package com.glanway.ctrlhr.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 说明 : 考勤月记录返回数据封装
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月18日 下午7:40:47
 */
public class MonthlySignVo {

	private Long id; // ID

	private String employeeName;// 员工名称

	private String employeeCode;// 员工代号

	private String deptName;// 部门名称

	private String jobName;// 职位名称

	private Date dateFrom;// 月考勤开始时间

	private Date dateTo;// 月考勤结束时间

	private BigDecimal days;// 出勤天数

	private BigDecimal hours;// 出勤总时间(小时)

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

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public BigDecimal getDays() {
		return days;
	}

	public void setDays(BigDecimal days) {
		this.days = days;
	}

	public BigDecimal getHours() {
		return hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

}
