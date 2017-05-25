package com.yd.dby.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.service.TestService;

@RestController
@RequestMapping(value = "/test", produces = { "application/json;charset=UTF-8" })
public class TestController {

	@Autowired
	TestService testService;

	@RequestMapping("/testPage")
	public Object testPage(Integer pageNo, Integer pageSize) {
		return testService.queryByPage(pageNo, pageSize);
	}
}
