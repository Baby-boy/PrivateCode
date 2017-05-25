package com.yd.gcj.entity.vo;

import com.yd.gcj.entity.YdMangerTaskActive;

public class YdMangerTaskActiveVo extends YdMangerTaskActive{
	
	/**用户昵称*/
	private String nickname;
	/**企业名称*/
	private String ename;
	/**用户类型*/
	private Integer type;
	/**用户类型描述*/
	private String typeD;
	
	
	/**
	 * 获取 用户昵称
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * 设置 用户昵称
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * 获取 企业名称
	 * @return ename
	 */
	public String getEname() {
		return ename;
	}
	
	/**
	 * 设置 企业名称
	 * @param ename
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	/**
	 * 获取 用户类型
	 * @return type
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * 设置 用户类型
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 获取 用户类型描述
	 * @return typeD
	 */
	public String getTypeD() {
		switch (type) {
		case 0:
			typeD = "【雇主】";
			break;
		case 1:
			typeD = "【个人】";
			break;
		case 2:
			typeD = "【企业】";
			break;
		default:
			typeD = "";
			break;
		}
		return typeD;
	}
	
	
}
