package com.yd.dby.utils;

import java.util.HashMap;

public class YdResponseUtil {

	public static Object success(Object input) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("status", "success");
		output.put("header", header);
		output.put("body", input);
		return output;
	}

	public static Object failure(Object input) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("status", "failure");
		output.put("header", header);
		output.put("body", input);
		return output;
	}
}
