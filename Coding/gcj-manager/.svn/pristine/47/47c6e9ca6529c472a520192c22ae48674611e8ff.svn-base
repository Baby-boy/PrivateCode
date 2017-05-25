package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerVerified {
	/**唯一标识*/
	private Integer v_id;
	/**用户信息id*/
	private Integer v_uid;
	/**真实姓名*/
	private String v_name;
	/**身份证号码*/
	private String v_idnum;
	/**从业年限（以id形式存储）*/
	private Integer v_yos;
	/**身份证正面图片地址*/
	private String v_idcaraimg;
	/**身份证反面图片地址*/
	private String v_idcarbimg;
	/**信息添加时间*/
	private Date v_create_time;
	/**信息更新时间*/
	private Date v_update_time;
	
	/**姓名带星号替换描述*/
	private String name;
	/**身份证星号替换处理*/
	private String idNumDes;
	
	/**从业年限描述*/;
	private String yosDes;
	
	/**
	 * 获取 唯一标识
	 * @return v_id
	 */
	public Integer getV_id() {
		return v_id;
	}
	
	/**
	 * 设置 唯一标识
	 * @param v_id
	 */
	public void setV_id(Integer v_id) {
		this.v_id = v_id;
	}
	
	/**
	 * 获取 用户id
	 * @return v_uid
	 */
	public Integer getV_uid() {
		return v_uid;
	}
	
	/**
	 * 设置 用户id
	 * @param v_uid
	 */
	public void setV_uid(Integer v_uid) {
		this.v_uid = v_uid;
	}
	
	/**
	 * 获取 用户姓名
	 * @return v_name
	 */
	public String getV_name() {
		return v_name;
	}
	
	/**
	 * 设置 用户姓名
	 * @param v_name
	 */
	public void setV_name(String v_name) {
		this.v_name = v_name;
	}
	
	/**
	 * 获取 身份证号码
	 * @return v_idnum
	 */
	public String getV_idnum() {
		return v_idnum;
	}
	
	/**
	 * 设置 身份证号码
	 * @param v_idnum
	 */
	public void setV_idnum(String v_idnum) {
		this.v_idnum = v_idnum;
	}
	
	/**
	 * 获取 从业年限（以id形式存储）
	 * @return v_yos
	 */
	public Integer getV_yos() {
		return v_yos;
	}
	
	/**
	 * 设置 从业年限（以id形式存储）
	 * @param v_yos
	 */
	public void setV_yos(Integer v_yos) {
		this.v_yos = v_yos;
	}
	
	/**
	 * 获取 身份证正面图片地址
	 * @return v_idcaraimg
	 */
	public String getV_idcaraimg() {
		return v_idcaraimg;
	}
	
	/**
	 * 设置 身份证正面图片地址
	 * @param v_idcaraimg
	 */
	public void setV_idcaraimg(String v_idcaraimg) {
		this.v_idcaraimg = v_idcaraimg;
	}
	
	/**
	 * 获取 身份证反面图片地址
	 * @return v_idcarbimg
	 */
	public String getV_idcarbimg() {
		return v_idcarbimg;
	}
	
	/**
	 * 设置 身份证反面图片地址
	 * @param v_idcarbimg
	 */
	public void setV_idcarbimg(String v_idcarbimg) {
		this.v_idcarbimg = v_idcarbimg;
	}
	
	/**
	 * 获取 信息添加时间
	 * @return v_create_time
	 */
	public Date getV_create_time() {
		return v_create_time;
	}
	
	/**
	 * 设置 信息添加时间
	 * @param v_create_time
	 */
	public void setV_create_time(Date v_create_time) {
		this.v_create_time = v_create_time;
	}
	
	/**
	 * 获取 信息更新时间
	 * @return v_update_time
	 */
	public Date getV_update_time() {
		return v_update_time;
	}
	
	/**
	 * 设置 信息更新时间
	 * @param v_update_time
	 */
	public void setV_update_time(Date v_update_time) {
		this.v_update_time = v_update_time;
	}

	/**
	 * 获取 yosDes
	 * @return yosDes
	 */
	public String getYosDes() {
		switch (v_yos) {
		case 0:
			yosDes = "未选择";
			break;
		case 1:
			yosDes = "1年以内";
			break;
		case 2:
			yosDes = "1~3年";
			break;
		case 3:
			yosDes = "3~5年";
			break;
		case 4:
			yosDes = "5年以上";
			break;
		default:
			yosDes = "暂无";
			break;
		}
		return yosDes;
	}
	
	/**
	 * 设置 yosDes
	 * @param yosDes
	 */
	public void setYosDes(String yosDes) {
		this.yosDes = yosDes;
	}
	
	/**
	 * 获取 姓名带星号替换描述
	 * @return name
	 */
	public String getName() {
		if(v_name != null && !v_name.isEmpty() && v_name.length()>1){
			String tmpname = v_name.substring(1, v_name.length());
			StringBuffer tmpx = new StringBuffer();
			for(int i = 0;i<tmpname.length();i++){
				tmpx.append("*");
			}
			name = v_name.replaceAll(tmpname,tmpx.toString());
		}else{
			name = "与要求不符";
		}
		return name;
	}
	
	/**
	 * 设置 姓名带星号替换描述
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取 身份证星号替换处理
	 * @return idNumDes
	 */
	public String getIdNumDes() {
		if(v_idnum != null && !v_idnum.isEmpty() && v_idnum.length() >= 15){
			idNumDes = v_idnum.replaceAll(v_idnum.substring(6, 13), "********");
		}else{
			idNumDes = "";
		}
		return idNumDes;
	}
	
	/**
	 * 设置 身份证星号替换处理
	 * @param idNumDes
	 */
	public void setIdNumDes(String idNumDes) {
		this.idNumDes = idNumDes;
	}
	
}
