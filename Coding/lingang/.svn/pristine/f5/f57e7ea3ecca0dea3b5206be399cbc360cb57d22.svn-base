/**
 * 
 */package com.lingang.common.util;/** * @author   E-mail: * @date 创建时间：2016年1月11日 下午4:17:36  */

import javax.servlet.http.HttpServletRequest;

/**
 *@Description: 
 *@Author:   联系方式：
 *@Since:2016年1月11日 下午4:17:36
 *@Version:1.0
 */
public class SoleIpUtil {

	/**
	  * @Title: getIpAddr
	  * @Description: 获取唯一真实ip
	  * @param request
	  * @return  
	  * @return 返回类型  String   
	  * @throws
	  * @Author:   联系方式：
	  * @Since: 2016年1月11日 下午4:18:18
	 */
	public static String getIpAddr(HttpServletRequest request) {      

        String ip = request.getHeader("x-forwarded-for");      

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     

                ip = request.getHeader("Proxy-Client-IP");      

            }     

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     

                ip = request.getHeader("WL-Proxy-Client-IP");     

            }     

            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     

                ip = request.getRemoteAddr();      

            }   
            if(ip.indexOf(",")!=-1){
            	String ips[]=ip.split(",");
            	ip=ips[0];
            }
            if(ip.equals("0:0:0:0:0:0:0:1"))ip="127.0.0.1";
       return ip;     

    } 
}
