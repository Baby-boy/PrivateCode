package com.yd.dby.utils.oss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yd.dby.utils.json.YdUtilJson;
import com.yd.dby.utils.servletrequest.YdServletRequest;
import com.yd.dby.utils.servletresponse.YdServletResponse;

public class YdOssFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest __request, ServletResponse __response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) __request;
		HttpServletResponse response = (HttpServletResponse) __response;
		String requestURI = request.getRequestURI();

		if (requestURI.equals(YdOssEnv.TOKEN_RequestURI)) {
			try {
				YdServletResponse.WriterJson(response, YdUtilJson.toString(YdOssUtil.generateToken()));
			} catch (Exception e) {
				YdServletResponse.WriterJson(response, "{code:401}");
			}
		} else if (requestURI.equals(YdOssEnv.CALLBACK_RequestURI)) {

			String authorization = request.getHeader("authorization");
			String x_oss_pub_key_url = request.getHeader("x-oss-pub-key-url");
			String paramsString = YdServletRequest.getParameters(request);

			try {
				YdOssUtil.verifyCallback(authorization, x_oss_pub_key_url, requestURI, paramsString);
				YdServletResponse.WriterJson(response, "{code:200}");
			} catch (Exception e) {
				YdServletResponse.WriterJson(response, "{code:401}");
			}
		} else {
			chain.doFilter(__request, __response);
		}
	}

	@Override
	public void destroy() {

	}
}
