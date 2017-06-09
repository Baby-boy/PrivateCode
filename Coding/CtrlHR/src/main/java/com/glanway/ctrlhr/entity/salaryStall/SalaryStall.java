package com.glanway.ctrlhr.entity.salaryStall;

import java.math.BigDecimal;

import com.glanway.ctrlhr.entity.BaseEntity;

public class SalaryStall extends BaseEntity{

	private static final long serialVersionUID = 6740092682322225715L;
	private String name; //工资档位名称
	private BigDecimal salaryNum; //薪资数目
	private String deleted; // 是否删除(0.否，1.是)

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSalaryNum() {
		return salaryNum;
	}
	public void setSalaryNum(BigDecimal salaryNum) {
		this.salaryNum = salaryNum;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}
