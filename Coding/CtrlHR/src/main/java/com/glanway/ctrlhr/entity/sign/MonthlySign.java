package com.glanway.ctrlhr.entity.sign;

import java.math.BigDecimal;
import java.util.Date;

import com.glanway.ctrlhr.entity.BaseEntity;

public class MonthlySign extends BaseEntity {

	private static final long serialVersionUID = -5356235686454694068L;

	private Long employeeId;// 员工ID

	private Date dateFrom;// 月考勤开始时间

	private Date dateTo;// 月考勤结束时间

	private BigDecimal days;// 出勤天数

	private BigDecimal hours;// 出勤总时间(小时)

	private String deleted;// 是否删除(0.否，1.是)

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}