package com.glanway.ctrlhr.entity.vo;

import java.math.BigDecimal;

public class SimplePerfStall {
	private Long id;
	private String name; //绩效档位名称
	private BigDecimal salaryNum; //薪资数目
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
	public BigDecimal getSalaryNum() {
		return salaryNum;
	}
	public void setSalaryNum(BigDecimal salaryNum) {
		this.salaryNum = salaryNum;
	}
	
	
}
