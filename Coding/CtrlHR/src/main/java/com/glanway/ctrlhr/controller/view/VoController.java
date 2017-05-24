package com.glanway.ctrlhr.controller.view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/view")
public class VoController {

	@RequestMapping(value = "viewName")
	public void Savepoint(Long viewName, HttpServletResponse response) {

		response.addCookie(new Cookie("viewName", viewName.toString()));

	}

}
