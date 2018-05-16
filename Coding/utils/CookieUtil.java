package com.glanway.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * Cookie工具类.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年1月1日
 */
public abstract class CookieUtil {

	/** 设置cookie有效期，默认设置为30天 */
	private static final int DEFAULT_COOKIE_MAX_AGE = 60 * 60 * 24 * 30;

	/**
	 * 获取指定name的cookie值.
	 *
	 * @param request
	 * @param name
	 * @return
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getValue(final HttpServletRequest request, final String name) {
		final Cookie cookie = getCookie(request, name);
		if (null != cookie) {
			return cookie.getValue();
		}

		return null;
	}

	/**
	 * 根据名称获得Cookie对象.
	 *
	 * @param request
	 * @param name
	 * @return
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Cookie getCookie(final HttpServletRequest request, final String name) {
		final Cookie[] cookies = request.getCookies();
		if (null == cookies || 0 >= cookies.length || StringUtils.isEmpty(name)) {
			return null;
		}

		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())) {
				cookie = cookies[i];
				if (request.getServerName().equals(cookies[i].getDomain())) {
					break;
				}
			}
		}

		return cookie;
	}

	public static void setCookie(final HttpServletResponse response, final String name, final String value) {
		setCookie(response, name, value, DEFAULT_COOKIE_MAX_AGE);
	}

	public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
			final String name, final String value) {
		setCookie(request, response, name, value, DEFAULT_COOKIE_MAX_AGE, null);
	}

	public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
			final String name, final String value, final String path) {
		setCookie(request, response, name, value, DEFAULT_COOKIE_MAX_AGE, path);
	}

	/**
	 * 添加Cookie信息,可以设置其最长有效时间(单位：秒),默认路径(/).
	 *
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static void setCookie(final HttpServletResponse response, final String name, final String value,
			final int maxAge) {
		final Cookie cookie = new Cookie(name, value);
		if (0 != maxAge) {
			cookie.setMaxAge(maxAge);
		} else {
			cookie.setMaxAge(DEFAULT_COOKIE_MAX_AGE);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 添加Cookie信息,可以设置其最长有效时间(单位：秒),默认路径为资源路径.
	 *
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @param path
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
			final String name, final String value, final int maxAge, final String path) {
		final Cookie cookie = new Cookie(name, value);
		if (0 != maxAge) {
			cookie.setMaxAge(maxAge);
		} else {
			cookie.setMaxAge(DEFAULT_COOKIE_MAX_AGE);
		}

		if (StringUtils.isEmpty(path)) {
			cookie.setPath(getPath(request));
		} else {
			cookie.setPath(path);
		}

		response.addCookie(cookie);
	}

	/**
	 * 删除一个cookie信息(设置时间为0).
	 *
	 * @param request
	 * @param response
	 * @param cookie
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static void deleteCookie(final HttpServletRequest request, final HttpServletResponse response,
			final Cookie cookie) {
		if (null != cookie) {
			cookie.setPath(getPath(request));
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 * 根据cookie名称删除cookie信息.
	 *
	 * @param request
	 * @param response
	 * @param name
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static void deleteCookie(final HttpServletRequest request, final HttpServletResponse response,
			final String name) {
		final Cookie cookie = getCookie(request, name);
		deleteCookie(request, response, cookie);
	}

	/**
	 * 获取站点根路径(对应cookie路径为资源路径).
	 * 
	 * @param request
	 * @return
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	private static String getPath(final HttpServletRequest request) {
		final StringBuffer url = request.getRequestURL();
		final String uri = request.getRequestURI();
		return url.delete(url.length() - uri.length(), url.length()).toString();
	}

}
