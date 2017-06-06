package com.tadpole.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求HttpServletRequest相关工具类.
 *
 * @author FUQIHAO
 * @version 1.0.0
 * @dateTime 2017年6月6日 上午9:26:02
 */
public abstract class RequestUtil {

	/** 未知常量 */
	public static final String UNKNOWN = "unknown";

	/** 请求头属性X-Forwarded-For */
	public static final String X_FORWARDED_FOR = "X-Forwarded-For";

	/** 请求头属性Proxy-Client-IP */
	public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

	/** 请求头属性WL-Proxy-Client-IP */
	public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

	/** 请求头属性HTTP_CLIENT_IP */
	public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";

	/** 请求头属性HTTP_X_FORWARDED_FOR */
	public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

	/** TCP/IPV4本地IP */
	public static final String LOCALHOST_IPV4 = "127.0.0.1";

	/** TCP/IPV6本地IP */
	public static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

	/**
	 * 获取请求的IP地址.
	 *
	 * @param request
	 * @return
	 * @author FUQIHAO
	 * @dateTime 2017年6月6日 上午9:26:51
	 */
	public static String getIPAddr(HttpServletRequest request) {
		if (request == null)
			return null;
		String ip = request.getHeader(X_FORWARDED_FOR);
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getHeader(PROXY_CLIENT_IP);
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getHeader(WL_PROXY_CLIENT_IP);
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getHeader(HTTP_CLIENT_IP);
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getHeader(HTTP_X_FORWARDED_FOR);
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();
		if (LOCALHOST_IPV4.equals(ip) || LOCALHOST_IPV6.equals(ip))
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException unknownhostexception) {
			}
		return ip;
	}
}
