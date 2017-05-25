package com.yd.dby.app.common;

import java.io.Serializable;
import java.util.Map;

/**
 * 说明: service返回值包装对象
 * 
 * @Version:1.0
 */
public class ServiceResult<T> implements Serializable {

	private static final long serialVersionUID = 557229634762292882L;

	/** 状态code */
	private HttpCode statusCode;

	/** 提示信息 */
	private String msg;

	/** 数据 */
	private T data;

	/** 数据Map */
	private Map<String, Object> dataMap;

	/** 备注 */
	private String remarks;

	public HttpCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpCode statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
