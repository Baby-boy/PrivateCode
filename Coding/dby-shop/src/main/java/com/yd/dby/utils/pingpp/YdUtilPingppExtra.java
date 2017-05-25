package com.yd.dby.utils.pingpp;

import java.util.Map;

import com.yd.dby.utils.map.YdUtilMap;

public class YdUtilPingppExtra {

	public Map<String, Object> toMap() throws IllegalAccessException {
		return YdUtilMap.fromObject(this);
	}
}
