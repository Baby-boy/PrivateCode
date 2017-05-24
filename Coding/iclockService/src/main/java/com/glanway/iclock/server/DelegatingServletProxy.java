/**
 * @author zhangshuang
 * 2017年4月24日 下午2:09:36
 */
package com.glanway.iclock.server;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 说明 : 
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月24日 下午2:09:36
 */
public class DelegatingServletProxy extends GenericServlet {
	
	private String targetBean;
	
	private Servlet proxy;

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		proxy.service(req, res);
	}
	
	@Override
	public void init() throws ServletException{
		this.targetBean = getServletName();
		getServletBean();
		proxy.init(getServletConfig());
	}
	
	private void getServletBean(){
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		this.proxy=(Servlet) wac.getBean(targetBean);// get proxyBean
	}
}
