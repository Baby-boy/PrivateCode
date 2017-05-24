package com.glanway.iclock.entity.employee;

import com.glanway.iclock.entity.BaseEntity;

/**
 * 
 * 说明 : 考勤机上传的员工信息(密码,照片,卡号等)
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 上午10:36:02
 */
public class EmployeeDeviceInfo extends BaseEntity {

	/**
	 * @author zhangshuang 2017年4月19日 上午10:35:53
	 */
	private static final long serialVersionUID = -4798201159405616444L;

	/**
	 * 用户考勤号码
	 */
	private String employeeCode;

	/**
	 * 考勤权限
	 */
	private String pri;

	/**
	 * 用户照片(头像)
	 */
	private String pic;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 卡号
	 */
	private String card;

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

	public String getPri() {
		return pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}