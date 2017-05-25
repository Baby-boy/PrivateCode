package com.yd.dby.utils.pingpp;

import java.util.Map;

import com.yd.dby.utils.map.YdUtilMap;

public class YdUtilPingppApp {

	/* Ping++ 应用 ID */
	private String id;

	public YdUtilPingppApp(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> toMap() throws IllegalAccessException {
		return YdUtilMap.fromObject(this);
	}
}
