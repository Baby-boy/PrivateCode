package com.yd.dby.utils.json;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class YdUtilJson {
	private static final Gson GSON = new Gson();

	public static <T> T toObject(String jsonstr, Class<T> classOfT) {
		return GSON.fromJson(jsonstr, new YdParameterizedType(classOfT, classOfT));
	}

	public static <T> List<T> toList(String jsonstr, Class<T> classOfT) {
		return GSON.fromJson(jsonstr, new YdParameterizedType(List.class, classOfT));
	}

	public static Map<String, Object> toMap(String jsonstr) {
		return GSON.fromJson(jsonstr, new YdParameterizedType(Map.class, new Class[] { String.class, Object.class }));
	}

	public static Map<String, ?> toMap(String jsonstr, Class<?> vClass) {
		return GSON.fromJson(jsonstr, new YdParameterizedType(Map.class, new Class[] { String.class, vClass }));
	}

	public static Map<?, ?> toMap(String jsonstr, Class<?> kClass, Class<?> vClass) {
		return GSON.fromJson(jsonstr, new YdParameterizedType(Map.class, new Class[] { kClass, vClass }));
	}

	public static <T> T toMap(String jsonstr, Class<T> classOfT, Class<?> kClass, Class<?> vClass) {
		return GSON.fromJson(jsonstr, new YdParameterizedType(classOfT, new Class[] { kClass, vClass }));
	}

	public static <T> T fromMap(Map<String, Object> obj, Class<T> classOfT) {
		JsonElement jsonElement = GSON.toJsonTree(obj);
		return GSON.fromJson(jsonElement, classOfT);
	}

	public static String toString(Object obj, Class<?> class0) {
		return GSON.toJson(obj, new YdParameterizedType(class0, class0));
	}

	public static String toString(Object obj) {
		return GSON.toJson(obj);
	}
}