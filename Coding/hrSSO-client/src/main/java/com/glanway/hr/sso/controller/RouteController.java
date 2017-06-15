package com.glanway.hr.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("route")
public class RouteController {

	@RequestMapping("/{pagePath}")
	public ModelAndView toPage(@PathVariable("pagePath") String pagePath) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(pagePath);
		return mv;
	}

	@RequestMapping("/{filePath}/{pagePath}")
	public ModelAndView toPage01(@PathVariable("pagePath") String pagePath, @PathVariable("filePath") String filePath) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(filePath + "/" + pagePath);
		return mv;
	}

	@RequestMapping("/{filePath1}/{filePath2}/{pagePath}")
	public ModelAndView toPage01(@PathVariable("pagePath") String pagePath, @PathVariable("filePath1") String filePath1,
			@PathVariable("filePath2") String filePath2) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(filePath1 + "/" + filePath2 + "/" + pagePath);
		return mv;
	}

}
