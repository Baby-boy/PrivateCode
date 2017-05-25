package com.yd.dby.utils;

import java.util.HashMap;

public class YdUtilResponse {
	private static String failStr = "操作失败";
	private static String successStr = "操作成功";
	
	/*
	 *   {
         *       "code": 200,
         *       "data": [], 
         *       "success": true // 成功
         *   }
         *   失败返回的数据
         *   {
         *       "code": 200, 
         *       "info": 'error', 
         *       "success": false // 失败
         *   }
	*/
	public static Object success(Object input, String info) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		if (info == "") {
			info = successStr;
		}
		output.put("code", 200);
		output.put("data", input);
		output.put("info", info);
		output.put("success", true);
		return output;
	}
	
	
	public static Object fail(String info) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		if (info == "") {
			info = failStr;
		}
		output.put("code", 200);
		output.put("info", info);
		output.put("success", false);
		return output;
	}
	
	

	/*
	public static Object success() {
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("code", 200);
		output.put("msg", "");
		output.put("data", 1);
		return output;
	}

	public static Object success(Object input) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("code", 200);
		output.put("msg", "");
		output.put("data", input);
		return output;
	}

	public static Object NotFound(String input) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("code", 404);
		output.put("msg", input);
		output.put("data", new HashMap<String, Object>());
		return output;
	}

	public static Object success2(Object input) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("code", 200);
		output.put("msg", "");
		output.put("data", input);
		return output;
	}

	public static Object failure(Object input) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("code", 500);
		output.put("msg", input);
		output.put("data", 0);
		return output;
	}

	public static Object failure(Integer state, Object input) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		output.put("code", state);
		output.put("msg", input);
		output.put("data", 0);
		return output;
	}
	*/
}
