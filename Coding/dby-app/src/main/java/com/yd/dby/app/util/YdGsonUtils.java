package com.yd.dby.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;

public class YdGsonUtils {

	private static final Gson GSON;

	static {
		GSON = new Gson();
	}

	// 重新封装数据,数据结构为String str = "{'0':{'key':'款式','value':'戒指'},'1':{'key':'成色','value':'全新'}}"
	@SuppressWarnings("unchecked")
	public static List<Object> fromJson(String json) {

		Map<String, Object> map = GSON.fromJson(json, Map.class);
		Set<Entry<String, Object>> entrySet = map.entrySet();

		// 创建一个List,用于存储
		List<Object> list = new ArrayList<>();
		for (Entry<String, Object> entry : entrySet) {
			list.add(entry.getValue());
		}

		return list;
	}

	// 对象转换为json
	public static String toJson(Object object) {
		return GSON.toJson(object);
	}

	// String 按类型转换返回
	public static <T> T fromJson(String json, Class<T> classOfT) {
		return GSON.fromJson(json, classOfT);
	}

}
