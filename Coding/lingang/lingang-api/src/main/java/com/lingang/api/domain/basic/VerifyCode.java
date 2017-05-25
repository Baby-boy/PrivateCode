package com.lingang.api.domain.basic;

import java.io.Serializable;

/**
 *@Description: (验证码实体bean)
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月2日 下午3:41:36
 *@Version:1.0
 */
public class VerifyCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5596589111706917634L;

	/**
	 * 验证码
	 */
	public String verifyCode;

	/**
	 * 触发时间
	 */
	public long time;

	public VerifyCode(String verifyCode) {
		super();
		this.verifyCode = verifyCode;
		this.time = System.currentTimeMillis() / 1000;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}