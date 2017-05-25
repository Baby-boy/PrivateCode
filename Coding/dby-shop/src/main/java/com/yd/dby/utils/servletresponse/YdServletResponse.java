package com.yd.dby.utils.servletresponse;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class YdServletResponse {

	public static void WriterJson(HttpServletResponse response, String json) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}