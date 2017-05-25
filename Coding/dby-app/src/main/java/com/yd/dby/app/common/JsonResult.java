package com.yd.dby.app.common;

import java.io.Serializable;
import java.util.Map;

/**
 * 说明: ajax json 返回值包装对象
 * 
 * @Version:1.0
 */
public class JsonResult implements Serializable {

	private static final long serialVersionUID = 2955969952867505921L;

	/** 状态码 */
	String statusCode = HttpCode.SERVICE_UNAVAILABLE.toString();

	/** 提示信息 */
	String msg;

	/** 数据 */
	Object data;

	/** 额外数据Map */
	Map<String, Object> dataMap;

	/** 备注 */
	String remarks;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpCode statusCode) {
		this.statusCode = statusCode.toString();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
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
