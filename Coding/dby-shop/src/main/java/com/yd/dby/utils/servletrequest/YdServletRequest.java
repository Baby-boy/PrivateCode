package com.yd.dby.utils.servletrequest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class YdServletRequest {

	public static String getParameters(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		String queryString = new String();
		for (String key : params.keySet()) {
			for (String value : params.get(key)) {
				queryString += key + "=" + value + "&";
			}
		}
		return queryString.substring(0, queryString.length() - 1);
	}

}