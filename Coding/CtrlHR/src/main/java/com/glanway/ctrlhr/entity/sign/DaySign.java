package com.glanway.ctrlhr.entity.sign;

import java.math.BigDecimal;
import java.util.Date;

import com.glanway.ctrlhr.entity.BaseEntity;

public class DaySign extends BaseEntity {

	private static final long serialVersionUID = -8638521592358173146L;

	private Long employeeId;// 员工ID

	private Date dateFrom;// 每天考勤开始时间

	private Date dateTo;// 每天考勤结束时间

	private BigDecimal hours;// 出勤总时间(小时)

	private Integer state;// 状态(1:正常打卡,2:上班未打卡,3:下班未打卡)

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

	public BigDecimal getHours() {
		return hours;
	}

	public void setHours(BigDecimal hours) {
		this.hours = hours;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}