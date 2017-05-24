package com.glanway.iclock.common.json;

public class ResponseModel {

	private String responseCode;
	private String message;
	private Object responseObject;
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	
	public String toJSONString(){
		String result = JSONUtil.getJSONString(this);
		
		return result;
	}
	
}
