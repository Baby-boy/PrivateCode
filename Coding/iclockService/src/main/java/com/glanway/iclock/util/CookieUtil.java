package com.glanway.iclock.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * function: Cookie 工具类 date: 2014年09月20日 下午11:26:37
 *
 * @author baiyin
 * @version v 0.1
 */
public class CookieUtil {

    /**
     * 设置cookie有效期，这里设置为30天
     */
    private static final int COOKIE_MAX_AGE = 1000 * 60 * 60 * 24 * 30;

    /**
     * 获取指定name的Cookie的值
     *
     * @param request
     * @param name
     * @return value
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (null == cookie)
            return null;
        else
            return cookie.getValue();
    }

    /**
     * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     *
     * @param request
     * @param name    return Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (null == cookies || cookies.length == 0 || null == name
                || name.length() == 0)
            return null;
        Cookie cookie = null;
        for (int i = 0; i < cookies.length; i++) {
            if (!cookies[i].getName().equals(name))
                continue;
            cookie = cookies[i];
            if (request.getServerName().equals(cookie.getDomain()))
                break;
        }
        return cookie;
    }

    /**
     * 添加一条新的Cookie信息，默认有效时间
     *
     * @param response
     * @param name
     * @param value
     */
    public static void setCookie(HttpServletResponse response, String name,
                                 String value) {
        setCookie(response, name, value, COOKIE_MAX_AGE);
    }

    /**
     * 添加一条新的Cookie信息，可以设置其最长有效时间(单位：秒)
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletResponse response, String name,
                                 String value, int maxAge) {
        if (null == value)
            value = "";
        Cookie cookie = new Cookie(name, value);
        if (maxAge != 0) {
            cookie.setMaxAge(maxAge);
        } else {
            cookie.setMaxAge(COOKIE_MAX_AGE);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * @param request
     * @param response
     * @param cookie
     * @description 设置一个cookie的生命周期为0. <br/>
     * @author 侯澎帅
     * @change Log:
     * @since JDK 1.7
     */
    public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath(getPath(request));
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * <p>名称：删除cookie</p>
     * <p>描述：根据cookie名字删除cookie</p>
     *
     * @param request
     * @param response
     * @param cookieName cookie名称
     * @author：tianxuan
     */
    public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName,"");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
    }

    /**
     * @param request
     * @return
     * @description 等到站点跟路径. <br/>
     * @author Administrator
     * @change Log:
     * @since JDK 1.7
     */
    private static String getPath(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
        return tempContextUrl;
    }

    /**
     * @param request
     * @param response
     * @param name
     * @param value
     * @description 与楼上的set方法的差别在于这里setPath()的是站点跟路径而不是"/". <br/>
     * @author 侯澎帅
     * @change Log:
     * @since JDK 1.7
     */
    public static void setCookieNew(HttpServletRequest request,
                                    HttpServletResponse response, String name, String value) {
        setCookieNew(request, response, name, value, 0x278d00);
    }

    public static void setCookieNew(HttpServletRequest request,
                                    HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value == null ? "" : value);
        cookie.setValue(cookie.getValue().replace("\r", "").replace("\n", ""));
        cookie.setMaxAge(maxAge);
        cookie.setPath(getPath(request));
        response.addCookie(cookie);
    }
}
