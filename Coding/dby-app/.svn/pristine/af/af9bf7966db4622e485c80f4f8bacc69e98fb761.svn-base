package com.yd.dby.app.config.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import net.sf.json.JSONObject;

public class FilterConfig2 extends OncePerRequestFilter {

	@Autowired
	protected StringRedisTemplate redisTemplate;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

		// String headerStr = request.getHeader("test");
		// System.out.println(headerStr);

		// 用户token
		String token = "";
		ServletRequest requestWrapper = null;
		if (request instanceof HttpServletRequest) {
			requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
		}
		if (requestWrapper == null) {// GET请求过滤

			token = request.getParameter("token");
			doFilterRequest(request, response, chain, token);
		} else {// POST请求过滤

			StringBuffer sb = new StringBuffer();
			InputStream is = requestWrapper.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			String str = sb.toString();

			if (!StringUtils.isEmpty(str)) {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) JSONObject.fromObject(str);
				if (map.get("token") != null) {
					token = map.get("token").toString();
				}
			}

			doFilterRequest(requestWrapper, response, chain, token);
		}

	}

	private void doFilterRequest(ServletRequest request, ServletResponse response, FilterChain chain, String token) {
		HttpServletRequest request2 = (HttpServletRequest) request;
		// 请求地址
		String reqUrl = request2.getRequestURI();
		// 异常的请求地址
		String errorUrl = "/main/tokenError";

		try {
			// 针对不同的请求做处理
			if (reqUrl.endsWith("/main/userLogin") || reqUrl.contains("/userRegister") || reqUrl.contains("/getRegCode") || reqUrl.contains("/getPwdCode")) {

				// 过滤登录、注册、获取注册验证码、获取忘记密码验证码
				chain.doFilter(request, response);
			} else if (reqUrl.contains("swagger") || reqUrl.contains("/configuration") || reqUrl.contains("/api-docs") || reqUrl.contains("/favicon")) {

				// System.out.println("swagger.." + reqUrl);
				// swagger-ui测试
				chain.doFilter(request, response);
			} else {

				// redis token
				String redisToken = redisTemplate.opsForValue().get(token);
				if (StringUtils.isEmpty(redisToken)) {

					// System.out.println("error.." + reqUrl);
					// token身份异常
					request.getRequestDispatcher(errorUrl).forward(request, response);
				} else {

					// 正常请求
					chain.doFilter(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
