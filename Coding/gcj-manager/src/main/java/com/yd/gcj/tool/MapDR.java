package com.yd.gcj.tool;

import java.util.Map;

public class MapDR {
	public static Map<String, Object> action(Object obj){
		MapInitFactory mapInitFactory = new MapInitFactory();
		
		try {
			mapInitFactory.init().setData(obj);
		} catch (Exception e) {
			mapInitFactory.setSystemError();
		}
		
		return mapInitFactory.getMap();
	}
}
