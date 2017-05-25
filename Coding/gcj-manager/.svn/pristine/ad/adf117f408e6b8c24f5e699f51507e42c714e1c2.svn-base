package com.yd.gcj.router;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class YdMangerMyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requestd = (HttpServletRequest) request;
		HttpServletResponse responsed = (HttpServletResponse) response;
		String requestURI = requestd.getRequestURI();
		if (requestURI.indexOf("system") > 0) {
			if (requestd.getSession().getAttribute("admin") == null) {
				if (isOk(requestURI)) {
					chain.doFilter(request, response);
				} else {
					responsed.sendRedirect("/system/myLogin");
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

	private boolean isOk(String pathName) {
		boolean is = false;
		String filterNames = "myLogin,css,js,images,layer,img,laydate,login";
		String[] names = filterNames.split(",");
		for (int i = 0; i < names.length; i++) {
			int t = pathName.indexOf(names[i]);
			if (t > 0) {
				is = true;
				break;
			}
		}
		return is;
	}
}
