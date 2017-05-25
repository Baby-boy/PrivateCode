package com.yd.dby.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;



/**
 * cookie
 * @author HH志
 *
 */
public class YdUtilCookie {
	
	/**
	 * 获取cookie
	 * @param name String key
	 * @param request HttpServletRequest 
	 * @return
	 */
	public static String getCookieValue(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
        	if ( cookie.getName().equals(name) ) {
        		return cookie.getValue();
        	}
        }
		return null;
	}
}