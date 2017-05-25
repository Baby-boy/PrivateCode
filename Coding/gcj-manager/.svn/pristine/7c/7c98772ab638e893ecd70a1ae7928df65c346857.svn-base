package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerPhoneCode {
	private String phone;
	private String code;
	private long time = 0;
	private int sy = 60;
	private int state = 0;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getSy() {
		if(time!=0){
			long nowtime = new Date().getTime();
			sy = 60-(int) ((nowtime - time)/1000);
			sy = sy<0?60:sy;
		}
		return sy;
	}
	public void setSy(int sy) {
		this.sy = sy;
	}
	public int getState() {
		if(time!=0){
			long nowtime = new Date().getTime();
			if((nowtime - time) < 61000){
				state = 1;
			}else{
				state = 0;
			}
		}else{
			state = 0;
		}
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}	
