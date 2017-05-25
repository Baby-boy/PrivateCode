package com.yd.dby.utils.session;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public class YdSession {

	@Autowired
	private HttpSession session;
	
	public static Object get( String code ) {
		YdSession ydSession = new YdSession();
		Object obj = ydSession.session.getAttribute( code );
		if ( obj != null ) {
			return obj;
		}else{
			return null;
		}
	}
	
}