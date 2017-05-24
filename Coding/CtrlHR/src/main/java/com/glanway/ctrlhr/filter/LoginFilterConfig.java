package com.glanway.ctrlhr.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilterConfig extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 不处理的请求
		String[] noFilter = new String[] { "/css/", "/js/", "/images/" };

		// 请求地址
		String reqUrl = request.getRequestURI();

		// 异常请求地址
		String loginURL = "/pageTo/login";

		String loginURI = "/api/user/login";

		if (!reqUrl.contains(loginURI) && !reqUrl.contains(loginURL)) {
			// 是否过滤
			boolean doFilter = true;

			for (String s : noFilter) {

				if (reqUrl.indexOf(s) != -1) {
					doFilter = false;
					break;
				}
			}

			if (doFilter) {

				String tokenStr = (String) request.getSession().getAttribute("token");

				String token = StringUtils.isEmpty(tokenStr) ? "" : tokenStr;

				if (StringUtils.isEmpty(token)) {
					// 未传入token信息
					request.getRequestDispatcher(loginURL).forward(request, response);
				} else {
					filterChain.doFilter(request, response);
				}
			} else {
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response);
		}

	}

}
