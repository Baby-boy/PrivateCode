package com.glanway.hr.app.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.ImmutableMap;

import net.sf.json.JSONArray;

/**
 * IP的工具类
 * @author tianxuan
 * @Time 2016/4/13
 */
public class IPUtil {
    private static final String ISP_URL = "http://ip.taobao.com/service/getIpInfo.php";

    /**
     * 根据request获取ip
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
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
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

    /**
     * 获取域名
     * @param request
     * @return
     */
    public static String getDNS(HttpServletRequest request){
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
        return tempContextUrl;
    }

    /**
     * TODO
     *根据ip获取isp信息
     * @param ip
     * @return
     */
    public static String getISP(String ip){
        String isp = "";
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(ip);
        Boolean isIp = mat.find();
        if(isIp){
            String result =  HttpUtils.URLGet(ISP_URL, ImmutableMap.<String, String>of("ip", ip), null);
            try {
                new String (result.getBytes("UTF-8"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            JSONArray jsonArray = JSONArray.fromObject(result);
        }
        return isp;
    }

}
