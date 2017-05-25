package com.lingang.api.domain.basic;


import java.io.Serializable;
import java.util.Map;


/**
 * ajax json 返回值包装对象
 * @author Tommy
 *
 */
public class JsonResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2955969952867505921L;
	/**
	 * 状态码
	 */
	String stateCode = StateCodeConstant.SUCCESS_CODE;
	/**
	 * 操作提示信息
	 */
	String message;
	/**
	 * 备注
	 */
	String remark;
	/**
	 * 数据
	 */
	Object data;
	
	/**
	 * 额外数据Map
	 */
	Map<String, Object> dataMap;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
