package com.yd.dby.utils.response;

import java.util.HashMap;

import com.yd.dby.a.sys.entity.YdSysUserFull;

public abstract class YdUtilResponse {

	private static Object response(Integer code, String msg, Object data) {
		HashMap<String, Object> out = new HashMap<String, Object>();
		out.put("code", code);
		out.put("msg", msg);
		out.put("data", data);
		return out;
	}

	public static Object success() {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("code", "true");
		output.put("msg", "");
		return output;
	}
	
	public static Object success(Object data) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("code", "true");
		output.put("msg", "");
		output.put("data", data);
		return output;
	}

	public static Object failure(Object data) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("status", "false");
		output.put("msg", "");
		output.put("data", data);
		return output;
	}

	public static Object failure(String msg) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("status", "false");
		output.put("msg", msg);
		output.put("data", msg);
		return output;
	}

	public static Object failure(String msg, Object data) {
		HashMap<String, Object> output = new HashMap<String, Object>();
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("status", "false");
		output.put("msg", msg);
		output.put("data", data);
		return output;
	}

	public static Object LoginSuccess(String token, YdSysUserFull user) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("user", user);
		return response(200, "", map);
	}

	public static Object LoginFailure(String msg) {
		return response(401, msg, null);
	}

	public static Object RegisterSuccess(String token, YdSysUserFull user) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("user", user);
		return response(200, "", map);
	}

	public static Object RegisterFailure(String msg) {
		return response(401, msg, null);
	}

	public static Object ResetPasswordSuccess(String token) {
		return response(200, "", null);
	}

	public static Object ResetPasswordFailure(String msg) {
		return response(401, msg, null);
	}

	public static Object ForgetPasswordSuccess() {
		return response(200, "", null);
	}

	public static Object ForgetPasswordFailure(String msg) {
		return response(401, msg, null);
	}

	public static Object SmsSuccess(String token) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		return response(200, "", map);
	}

	public static Object SmsFailure(String msg) {
		return response(401, msg, null);
	}
}