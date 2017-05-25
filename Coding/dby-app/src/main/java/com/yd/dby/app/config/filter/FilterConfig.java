package com.yd.dby.app.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

public class FilterConfig extends OncePerRequestFilter {

	@Autowired
	protected StringRedisTemplate redisTemplate;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		// 请求地址
		String reqUrl = request.getRequestURI();

		// 异常的请求地址
		String errorUrl = "/main/tokenError";

		// 用户token(放在Header里)
		String token = StringUtils.isEmpty(request.getHeader("token")) ? "" : request.getHeader("token");

		// 针对不同的请求做处理
		if (reqUrl.contains("/main/userLogin") || reqUrl.contains("/userRegister") || reqUrl.contains("/getRegCode")
				|| reqUrl.contains("/forgetPwd") || reqUrl.contains("/getPwdCode") || reqUrl.contains("/getThirdCode")
				|| reqUrl.contains("/userThirdBinding") || reqUrl.contains("/userLoginThird")) {
			// 过滤登录、注册、获取注册验证码、忘记密码、获取忘记密码验证码 接口、获取第三方绑定验证码、第三方绑定、第三方登录
			chain.doFilter(request, response);
		} else {
			// redis token-value
			String redisToken = redisTemplate.opsForValue().get(token);
			if (StringUtils.isEmpty(redisToken)) {
				// token身份异常
				request.getRequestDispatcher(errorUrl).forward(request, response);
				// chain.doFilter(request, response);
			} else {
				// 正常请求
				chain.doFilter(request, response);
			}
		}
	}
}
