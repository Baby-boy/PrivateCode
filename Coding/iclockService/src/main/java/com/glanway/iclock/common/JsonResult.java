package com.glanway.iclock.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonResult implements Serializable {

	private static final long serialVersionUID = 2955969952867505921L;

	/** 状态码 */
	String code = HttpCode.OK.toString();

	/** 提示信息 */
	String msg = "操作成功!";

	/** 登录状态 */
	Integer loginState = 2;// 1:未登录, 2:已登录, 3:未检查

	/** 数据 */
	Object data = new Object();

	/** 扩展数据 */
	Map<String, Object> dataMap = new HashMap<>();

	public String getCode() {
		return code;
	}

	public void setCode(HttpCode httpCode) {
		this.code = httpCode.toString();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getLoginState() {
		return loginState;
	}

	public void setLoginState(Integer loginState) {
		this.loginState = loginState;
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

}
