package com.yd.gcj.tool;

import javax.servlet.http.HttpSession;

import com.yd.gcj.entity.vo.PageNum;

public class MySessionFactory {
	
	public static PageNum getPageMsg(HttpSession session,Integer num){
		PageNum pageNum = null;
		if(session.getAttribute("pageNum")!=null){
			pageNum = (PageNum) session.getAttribute("pageNum");
		}else{
			pageNum = new PageNum();
		}
		pageNum.setPageCount(num);
		return pageNum;
	}
	
	public static void setPageMsg(HttpSession session,PageNum pageNum){
		session.setAttribute("pageNum", pageNum);
	}
	
}
