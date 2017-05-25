package com.lingang.platform.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lingang.common.constants.CommonConstants;
import com.lingang.common.util.StringUtils;


/**
 * ClassName: SecurityInterceptor
 * @Description: 判断用户权限，未登录用户跳转到登录页面
 * @author 
 * @date 2015年5月9日上午9:48:20
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	private List<String> excludedUrls;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		//请求参数
		String reqParaString = request.getQueryString();
		//请求地址
		String requestUri = request.getRequestURI();

		if(excludedUrls != null){
			requestUri = request.getRequestURI();
		    for (String url : excludedUrls) {
		      if (requestUri.endsWith(url)) {
		        return true;
		      }
		    }
		}
		if(session.getAttribute(CommonConstants.LOGIN_USER_KEY)==null) {
			//当前url
		    StringBuffer beforeUrl = request.getRequestURL();
		    //参数
            if(!StringUtils.isStrNull(reqParaString)){
            	beforeUrl.append("?").append(reqParaString);
            }
    		response.setHeader("P3P","CP='CP=CAO PSA OUR'");
			response.sendRedirect(request.getContextPath() + "/login.htm");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}
}