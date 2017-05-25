package com.lingang.api.domain.basic;

import java.io.Serializable;
import java.util.Map;

/**
 * service返回值包装对象
 * 
 * @param <T>
 * 
 */
public class ServiceResult<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 557229634762292882L;

	/**
	 * msg 状态0表示异常、1表示正确
	 */
	private int msgType = 0;

	/**
	 * 操作提示信息
	 */
	String message;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 消息code 默认成功，状态码为 1000
	 */
	private String stateCode = "1000";
	/**
	 * 消息参数
	 */
	private Object[] msgParams;

	/**
	 * 数据
	 */
	private T data;

	/**
	 * 数据Map
	 */
	private Map<String, Object> dataMap;

	public int getMsgType() {
		return this.msgType;
	}

	public String getStateCode() {
		return stateCode;
	}

	/**
	 * 根据msg第二位字母取得msg类型
	 * 
	 * @param msgCode
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
		try {
			char state = stateCode.charAt(1);
			switch (state) {
			case 'I':
				this.msgType = 1;
				break;
			case 'Q':
				this.msgType = 1;
				break;
			case 'E':
				this.msgType = 0;
				break;

			default:
				this.msgType = 0;
				break;
			}

		} catch (Exception e) {
			this.msgType = 0;
		}
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

	public Object[] getMsgParams() {
		return msgParams;
	}

	public void setMsgParams(Object[] msgParams) {
		this.msgParams = msgParams;
	}

}
