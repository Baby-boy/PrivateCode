package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerCount {
	
	private Integer count_id;//统计表主键
	private Integer countNum;//将当天的日期转为(列入2017*-01-01转为20170101)
	private Integer count_person;//当天会员新增人数
	private Integer count_money;//当天会员充值总金额
	private Date count_date;//日期
	public Integer getCount_id() {
		return count_id;
	}
	public void setCount_id(Integer count_id) {
		this.count_id = count_id;
	}
	public Integer getCountNum() {
		return countNum;
	}
	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
	public Integer getCount_person() {
		return count_person;
	}
	public void setCount_person(Integer count_person) {
		this.count_person = count_person;
	}
	public Integer getCount_money() {
		return count_money;
	}
	public void setCount_money(Integer count_money) {
		this.count_money = count_money;
	}
	public Date getCount_date() {
		return count_date;
	}
	public void setCount_date(Date count_date) {
		this.count_date = count_date;
	}
}
