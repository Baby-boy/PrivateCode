package com.lingang.consumer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description: (token过滤)
 * @Author: lgl(lgl1012dream@foxmail.com)
 * @date:2016年12月2日 下午2:50:02
 * @Version:1.0
 */
public class TokenFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest sreq, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) sreq;
		HttpSession session = request.getSession(false);
		// //获取请求地址
		String reqUrl = request.getRequestURI();
		// //正常请求（开发中不加过滤）
		// request.getRequestDispatcher(reqUrl.substring("/lingang-consumer".length())).forward(request,response);
		// 针对不同的请求做处理
		if (reqUrl.endsWith("/lingang-consumer/user/userLogin.do")) {
			// 登录过滤
			request.getRequestDispatcher(reqUrl.substring("/lingang-consumer".length())).forward(request, response);
		} else if (reqUrl.contains("/wcm")) {
			// wcm
			filterChain.doFilter(request, response);
		} else if (reqUrl.contains("/lingang-consumer/share/")) {
			// 分享
			request.getRequestDispatcher(reqUrl.substring("/lingang-consumer".length())).forward(request, response);
		} else if(reqUrl.endsWith("/lingang-consumer/userImgUpload/imageUpload.do")){
			// 头像上传
			request.getRequestDispatcher(reqUrl.substring("/lingang-consumer".length())).forward(request, response);
		}else {
			String requestToken = request.getParameter("token");
			if (session == null || session.getAttribute("token") == null || requestToken == null
					|| !requestToken.equals(session.getAttribute("token"))) {
				// //token身份异常
				request.getRequestDispatcher("/main/tokenError.do").forward(request, response);
			} else {
				// 正常请求
				request.getRequestDispatcher(reqUrl.substring("/lingang-consumer".length())).forward(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
