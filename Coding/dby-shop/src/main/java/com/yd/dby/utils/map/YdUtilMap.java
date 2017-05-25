package com.yd.dby.utils.map;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class YdUtilMap {

	public static Map<String, Object> fromObject(Object bean) throws IllegalAccessException {
		Map<String, Object> bucket = new LinkedHashMap<>();
		for (Field field : bean.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String key = field.getName();
			Object value = field.get(bean);
			if (value instanceof String || value instanceof Boolean || value instanceof Character || value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long || value instanceof Float || value instanceof Double) {
				bucket.put(key, value.toString());
			}
		}
		return bucket;
	}

	// public static Map<String, Object> fromObject(Object bean) throws
	// IllegalAccessException {
	// Map<String, Object> bucket = new LinkedHashMap<>();
	// for (Field field : bean.getClass().getDeclaredFields()) {
	// field.setAccessible(true);
	// String key = field.getName();
	// Object value = field.get(bean);
	// if (value instanceof String || value instanceof Boolean || value
	// instanceof Character || value instanceof Byte || value instanceof Short
	// || value instanceof Integer || value instanceof Long || value instanceof
	// Float || value instanceof Double) {
	// bucket.put(key, value.toString());
	// } else {
	// break;
	// }
	// }
	// return bucket;
	// }

	// public static List<?> fromCollection(Object bean, Collection<?> bucket)
	// throws IllegalAccessException {
	// if (bucket == null) {
	// bucket = new ArrayList<>(1);
	// fromCollection(bean, bucket);
	// } else {
	// for (Field field : bean.getClass().getDeclaredFields()) {
	// field.setAccessible(true);
	// String key = field.getName();
	// Object value = field.get(bean);
	// if (value != null) {
	// if (value instanceof Collection) {
	// List<Object> node = new ArrayList<Object>();
	// bucket.add(node);
	// for (Iterator<?> iterator = ((Collection<?>) value).iterator();
	// iterator.hasNext();) {
	// Map<String, Object> node2 = new LinkedHashMap<>();
	// node.add(node2);
	// fromObject(iterator.next(), node2);
	// }
	// } else if (value instanceof Map) {
	// bucket.add(key, value);
	// } else if (value instanceof String || value instanceof Boolean || value
	// instanceof Character || value instanceof Byte || value instanceof Short
	// || value instanceof Integer || value instanceof Long || value instanceof
	// Float || value instanceof Double) {
	// bucket(key, value.toString());
	// } else if (value instanceof Object) {
	// Map<String, Object> node = new LinkedHashMap<>();
	// bucket.add(key, node);
	// fromObject(value, node);
	// } else {
	// System.out.println(value.getClass());
	// }
	// }
	// }
	// }
	// return bucket;
	// }
	//
	// public static List<?> fromCollection(Collection<?> beans, Collection<?>
	// bucket) throws IllegalAccessException {
	// if (bucket == null) {
	// bucket = new ArrayList<>(beans.size());
	// fromCollection(beans, bucket);
	// } else {
	// for (java.util.Iterator<?> iterator = beans.iterator();
	// iterator.hasNext();) {
	// fromObject(iterator.next(), node2);
	// }
	// for (Field field : bean.getClass().getDeclaredFields()) {
	// field.setAccessible(true);
	// String key = field.getName();
	// Object value = field.get(bean);
	//// if (value != null) {
	//// if (value instanceof Collection) {
	//// List<Object> node = new ArrayList<Object>();
	//// bucket.add(node);
	//// for (Iterator<?> iterator = ((Collection<?>) value).iterator();
	// iterator.hasNext();) {
	//// Map<String, Object> node2 = new LinkedHashMap<>();
	//// node.add(node2);
	//// fromObject(iterator.next(), node2);
	//// }
	//// } else if (value instanceof Map) {
	//// bucket.add(key, value);
	//// } else if (value instanceof String || value instanceof Boolean || value
	// instanceof Character || value instanceof Byte || value instanceof Short
	// || value instanceof Integer || value instanceof Long || value instanceof
	// Float || value instanceof Double) {
	//// bucket.add(key, value.toString());
	//// } else if (value instanceof Object) {
	//// Map<String, Object> node = new LinkedHashMap<>();
	//// bucket.add(key, node);
	//// fromObject(value, node);
	//// } else {
	//// System.out.println(value.getClass());
	//// }
	//// }
	// }
	// }
	// return bucket;
	// }
	//
	// public static Map<String, ?> fromObject(Object bean, Map<String, Object>
	// bucket) throws IllegalAccessException {
	// if (bucket == null) {
	// bucket = new LinkedHashMap<>();
	// fromObject(bean, bucket);
	// } else {
	// for (Field field : bean.getClass().getDeclaredFields()) {
	// field.setAccessible(true);
	// String key = field.getName();
	// Object value = field.get(bean);
	// if (value != null) {
	// if (value instanceof Collection) {
	// Collection<?> node = new ArrayList<>();
	// bucket.put(key, node);
	// fromCollection((Collection<?>) value, node);
	//// bucket.put(key, node);
	//// for (Iterator<?> iterator = ((Collection<?>) value).iterator();
	// iterator.hasNext();) {
	//// Map<String, Object> node2 = new LinkedHashMap<>();
	//// node.add(node2);
	//// fromObject(iterator.next(), node2);
	//// }
	// } else if (value instanceof Map) {
	// bucket.put(key, value);
	// } else if (value instanceof String || value instanceof Boolean || value
	// instanceof Character || value instanceof Byte || value instanceof Short
	// || value instanceof Integer || value instanceof Long || value instanceof
	// Float || value instanceof Double) {
	// bucket.put(key, value.toString());
	// } else if (value instanceof Object) {
	// Map<String, Object> node = new LinkedHashMap<>();
	// bucket.put(key, node);
	// fromObject(value, node);
	// } else {
	// System.out.println(value.getClass());
	// }
	// }
	// }
	// }
	// return bucket;
	// }

}