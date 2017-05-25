package com.yd.gcj.controller.page;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.service.YdMangerServiceLabel;

@RestController
@RequestMapping(value = "/page/taskLabel", produces = {"application/json;charset=UTF-8"})
public class YdMangerControllerPageTaskLabel {
	@Autowired
	private YdMangerServiceLabel ydMangerServiceTaskLabel;
	
	@RequestMapping("/queryAll")//method = RequestMethod
	public Object queryAll(HashMap<String, Object> map){
		
		return ydMangerServiceTaskLabel.$queryAll();
	}
}
