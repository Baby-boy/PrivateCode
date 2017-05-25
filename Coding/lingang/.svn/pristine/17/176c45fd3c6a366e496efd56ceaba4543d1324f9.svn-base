package com.lingang.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		
//		//获取请求地址
		String reqUrl = request.getRequestURI();
		
//		//请求地址
		String urlStr=reqUrl.substring("/lingang-consumer".length());
		
		//登录过滤
		if (reqUrl.endsWith("/login.htm")) {
			
		}else if(session.getAttribute("manager")==null || session.getAttribute("manager").equals("")){
			//非法请求
			urlStr="/errorRequst.do";
		}else {
			
			String pStr=session.getAttribute("p").toString();
			
			//非法请求
			if (reqUrl.endsWith("/sysBasics/sysBasicsPagePageListP.do")) {
				
				//基础类型
				if(pStr.indexOf("/1/") <0 || pStr.indexOf("/2/") <0){
					urlStr="/errorRequst.do";
				}
				
			}else if (reqUrl.endsWith("/sysPartnerType/selectPartnerTypePageListP.do")) {
				
				//合作类型
				if(pStr.indexOf("/1/") <0 || pStr.indexOf("/3/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysLabel/sysLabelPagePageListP.do")) {
				
				//标签管理
				if(pStr.indexOf("/1/") <0 || pStr.indexOf("/4/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysRegion/sysRegionPagePageListP.do")) {
				
				//地区管理
				if(pStr.indexOf("/1/") <0 || pStr.indexOf("/27/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/company/selectCompanyPageListP.do")) {
				
				//公司信息
				if(pStr.indexOf("/1/") <0 || pStr.indexOf("/5/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysNews/newsList.do")) {
				
				//新闻列表
				if(pStr.indexOf("/6/") <0 || pStr.indexOf("/7/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysPartner/queryAllByPage.do")) {
				
				//新闻列表
				if(pStr.indexOf("/6/") <0 || pStr.indexOf("/8/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysTopStation/queryAllByTop.do")) {
				
				//最新入驻
				if(pStr.indexOf("/6/") <0 || pStr.indexOf("/9/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/policy/queryAllByPage.do")) {
				
				//政策列表
				if(pStr.indexOf("/6/") <0 || pStr.indexOf("/10/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysPark/sysParkPageListP.do")) {
				
				//产业园区
				if(pStr.indexOf("/11/") <0 || pStr.indexOf("/12/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysPartner/sysPartnerPageListP.do")) {
				
				//合作伙伴
				if(pStr.indexOf("/11/") <0 || pStr.indexOf("/13/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysIndustry/sysIndustryPageListP.do")) {
				
				//产业集群
				if(pStr.indexOf("/11/") <0 || pStr.indexOf("/14/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysService/sysServicePageListP.do")) {
				
				//服务机构
				if(pStr.indexOf("/11/") <0 || pStr.indexOf("/15/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysStation/sysStationPageListP.do")) {
				
				//入驻企业
				if(pStr.indexOf("/11/") <0 || pStr.indexOf("/16/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysPublic/sysPublicPageListP.do")) {
				
				//公共平台
				if(pStr.indexOf("/11/") <0 || pStr.indexOf("/17/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/user/sysUserPagePageList.do")) {
				
				//APP账户
				if(pStr.indexOf("/18/") <0 || pStr.indexOf("/19/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysMessage/sysMessagePagePageListE.do")) {
				
				//信息纠错
				if(pStr.indexOf("/20/") <0 || pStr.indexOf("/21/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysMessage/sysMessagePagePageList.do")) {
				
				//意见反馈
				if(pStr.indexOf("/20/") <0 || pStr.indexOf("/22/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/sysManager/sysManagerPagePageList.do")) {
				
				//管理员账户
				if(pStr.indexOf("/23/") <0 || pStr.indexOf("/24/") <0){
					urlStr="/errorRequst.do";
				}
			
			}else if (reqUrl.endsWith("/trs/trsPost.do")) {
				
				//站点发布系统
				if(pStr.indexOf("/25/") <0 || pStr.indexOf("/26/") <0){
					urlStr="/errorRequst.do";
				}
			
			}
			
			
		}
		
		

		
		request.getRequestDispatcher(urlStr).forward(request,response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
