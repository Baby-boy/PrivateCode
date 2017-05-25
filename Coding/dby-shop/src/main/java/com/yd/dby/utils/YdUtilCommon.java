package com.yd.dby.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class YdUtilCommon {
	
	/**
	 * 根据数据集获取其中某一列数据的集合
	 * @param data
	 * @param field
	 * @return List<String> fieldList;
	 */
	public static List<String> getFields(List<?> data, String field) {
		List<String> fieldList = new ArrayList<String>();
		for (Object str : data)
	    {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> itemHashMap = (HashMap<String, Object>) str;
			fieldList.add( itemHashMap.get(field).toString() );
	    }
		return fieldList;
	}
	
	
	public static List<String> getSplitList(String str, String sign) {
		try {
			List<String> valueList = new ArrayList<String>();
			String[] arr = str.split( sign );
			for (String val : arr)
		    {
				valueList.add(val);
		    }
			return valueList;
		} catch (Exception e) {
			return null;
		}
	}
}
