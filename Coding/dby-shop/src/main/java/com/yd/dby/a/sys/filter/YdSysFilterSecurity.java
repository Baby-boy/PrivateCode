package com.yd.dby.a.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.yd.dby.utils.security.YdSecurity;

public class YdSysFilterSecurity implements Filter {
	@Autowired
	private HttpSession session;

	@Autowired
	private YdSecurity ydSecurity;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest __request, ServletResponse __response, FilterChain chain)
			throws IOException, ServletException {

		 HttpServletRequest request = (HttpServletRequest) __request;
		 HttpServletResponse response = (HttpServletResponse) __response;
		 String requestURI = request.getRequestURI();
	
		 if ( requestURI.startsWith("/pay/notify")) {
			 chain.doFilter(__request, __response);
			 return;
		 }
		 
		 if (requestURI.startsWith("/member") || requestURI.startsWith("/seller") || requestURI.startsWith("/pay")) {
			 if ( session.getAttribute("user") == null ) {
				 response.sendRedirect("/login");
				 return;
			 }
		 }
		 
		 if (requestURI.startsWith("/circulation/appointment/") || requestURI.startsWith("/substitution/appointment/")) {
			 if ( session.getAttribute("user") == null ) {
				 response.sendRedirect("/login");
				 return;
			 }
		 }
		
		chain.doFilter(__request, __response);
		
		/*
		System.out.println( "-------------------" );
		System.out.println(requestURI);
		System.out.println( "-------------------" );
		*/
		
		/*
		if (requestURI.equals("/member")) {
			__request.setAttribute("user", ydSecurity.verifyToken(request.getHeader("Authorization")));
			chain.doFilter(__request, __response);
			return;
		}
		
		if (requestURI.equals("/member/register")) {
			__request.setAttribute("user", ydSecurity.verifyToken(request.getHeader("Authorization")));
			chain.doFilter(__request, __response);
			return;
		}
		

		if (requestURI.startsWith("/member")) {
			YdSysUserJwt user = ydSecurity.verifyToken(request.getHeader("Authorization"));

			if (user == null) {
				if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))
						|| "application/json".equals(request.getHeader("Content-Type"))) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				} else {
					response.sendRedirect("/member");
				}
			} else {
				__request.setAttribute("user", user);
				__request.setAttribute("id", user.getId());
				__request.setAttribute("role", user.getId());
				chain.doFilter(__request, __response);
			}
			
			return;
		}

		chain.doFilter(__request, __response);
		*/
	}

	@Override
	public void destroy() {

	}
}
