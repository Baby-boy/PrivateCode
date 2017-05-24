package com.glanway.iclock.entity.sign;

import java.util.Date;

import com.glanway.iclock.entity.BaseEntity;
/**
 * 
 * 说明 : 
 * 员工考勤记录 实体
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 下午9:01:20
 */
public class Sign extends BaseEntity {

	/**
	 * @author zhangshuang 2017年4月19日 下午8:49:33
	 */
	private static final long serialVersionUID = 9080554059029741007L;
	
	/**
	 * 员工代码
	 */
	private String employeeCode;

	/**
	 * 考勤机代码
	 */
	private String sn;
	
	/**
	 * 考勤时间
	 */
	private Date time;

	/**
	 * 考勤状态(1:上班,2:下班,3.外出,4:外出返回,5:加班签到,6:加班签退,7:就餐开始,8:就餐结束)
	 */
	private String state;
	
	/**
	 * 验证方式（0:密码,1:指纹,2打卡,9:其他）
	 */
	private String verify;
	
	/**
	 * 工作代码
	 */
	private String workcode;
	
	/**
	 * 是否删除(0.否，1.是)
	 */
	private String deleted;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}