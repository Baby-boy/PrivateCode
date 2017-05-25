package com.connect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {

	@RequestMapping(value = "redirect", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:http://ydgcj.tunnel.2bdata.com/user/wxLogin";
	}
}
