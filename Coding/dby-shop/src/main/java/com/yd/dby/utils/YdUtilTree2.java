package com.yd.dby.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YdUtilTree2 {


	@SuppressWarnings("unchecked")
	public static List<HashMap<String, Object>> toList(List<HashMap<String, Object>> input) {
		List<HashMap<String, Object>> output = new ArrayList<HashMap<String, Object>>();

		HashMap<Integer, HashMap<String, Object>> map = new HashMap<Integer, HashMap<String, Object>>();

		for (HashMap<String, Object> item : input) {
			if (!item.containsKey("nodes")) {
				item.put("nodes", new ArrayList<HashMap<String, Object>>());
			}

			Integer id = Integer.valueOf(item.get("id").toString());
			Integer pid = Integer.valueOf(item.get("pid").toString());

			if (pid == 0) {
				output.add(item);
			}

			map.put(id, item);
		}

		for (HashMap<String, Object> item : input) {
			Integer key = Integer.valueOf(item.get("pid").toString());
			if (key > 0) {
				((List<HashMap<String, Object>>) map.get(key).get("nodes")).add(item);
			}
		}
		return output;
	}

}
