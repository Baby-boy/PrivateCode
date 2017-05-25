package com.lingang.consumer.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
* @ClassName: CookieUtil 
* @Description: cookie工具类
* @author 
* @date 2015年9月22日 下午2:32:35
 */
public class CookieUtil {
	
	/**
	 * @param request
	 * @return
	 */
	public static Cookie[] getAllCookies(HttpServletRequest request){
		return request.getCookies();
	}
	
	/**
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String cookieName){
		Cookie[] cookies = getAllCookies(request);
		if(cookies == null || cookies.length == 0){
			return null;
		}
		Cookie resultCookie = null;
		for(Cookie cookie : cookies){
			if(cookieName.equals(cookie.getName())){
				resultCookie = cookie;
				break;
			}
		}
		return resultCookie;
	}
	
	public static String getCookieValueByName(Cookie[] cookies,String cookieName){
		String resultCookieValue = null;
		for(Cookie cookie : cookies){
			if(cookieName.equals(cookie.getName())){
				resultCookieValue = cookie.getValue();
				break;
			}
		}
		return resultCookieValue;
	}
	
	public static void addCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue){
		Cookie cookie = new Cookie(cookieName,cookieValue);
		cookie.setDomain("localhost");  //请用自己的域
		cookie.setMaxAge(864000); //1天
		cookie.setPath("/lingang-consumer/");
		response.addCookie(cookie);
	}
	
	public static void delectCookie(HttpServletRequest request,HttpServletResponse response,String cookieName){
		Cookie cookie = new Cookie(cookieName,null);
		cookie.setDomain("localhost");  //请用自己的域
		cookie.setMaxAge(0); //cookie立即失效
		cookie.setPath("/lingang-consumer/");
		response.addCookie(cookie);
	}
	
}