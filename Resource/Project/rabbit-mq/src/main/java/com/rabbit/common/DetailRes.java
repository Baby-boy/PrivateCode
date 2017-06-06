package com.rabbit.common;

public class DetailRes {

	private boolean isSuccess;
	private String errMsg;

	public DetailRes(boolean isSuccess, String errMsg) {
		this.isSuccess = isSuccess;
		this.errMsg = errMsg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "DetailRes [isSuccess=" + isSuccess + ", errMsg=" + errMsg + "]";
	}

}
