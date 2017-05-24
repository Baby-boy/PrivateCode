package com.glanway.iclock.util;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Component;

import com.glanway.iclock.common.Constant;



/**
 * Created by YuRuizhi on 2016/1/18.
 */
@Component("UrlUtil")
public class UrlUtil {

    public static String getServerFullBaseUrl(HttpServletRequest request) {
        String root = request.getScheme() + "://"
                + request.getServerName()
                + ":" + request.getServerPort()
                + request.getContextPath();
        return root;
    }

    /**
     *
     *
     * @return String
     */
    public static String getFullFilePath(HttpServletRequest request, String fileName) {
        return getFullFilePath(request, fileName, "");
    }

    /**
     * 
     *
     * @return String
     */
    public static String getDefaultAvatar(HttpServletRequest request, String fileName) {
        return getFullFilePath(request, fileName, Constant.DEFAULT_HEAD_IMAGE);
    }

    public static String getUrlPath(HttpServletRequest request) {
        String url = getServerFullBaseUrl(request);
        return url.replaceAll("/" + getUrlFileName(request), "");
    }

    public static String getUrlFileName(HttpServletRequest request) {
        String url = getServerFullBaseUrl(request);
        return url.split("/")[url.split("/").length - 1];
    }

    public static String getFullFilePath(HttpServletRequest request, String fileName, String defaultFileName) {
        String url = getServerFullBaseUrl(request);
        if (url == null) {
            url = "";
        }

        if (fileName == null) {
            fileName = defaultFileName;
        }

        fileName = fileName.toLowerCase();

        if (fileName.startsWith("http://") || fileName.startsWith("https://")) {
            return fileName;
        }

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        if (fileName.startsWith("../") || fileName.startsWith("/")) {
            fileName = fileName.substring(fileName.indexOf("/") + 1);
        }
        //System.out.println("return:"+url+"/"+fileName);
        return url + "/" + fileName;
    }
}
