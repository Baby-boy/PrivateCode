package com.yd.gcj.system.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
public class YdMangerSystemRouterTaskType {
	@RequestMapping("/system/rwgl/tasktype")
	public String addAdmin() {
		return "system/rwgl/tasktype";
	}
	
	@RequestMapping("/system/addtasktype")
	public String addTaskType() {
		return "system/rwgl/tasktypeadd";
	}
	
	@RequestMapping("/system/addProTaskTypes")
	public String addProTaskType() {
		return "system/rwgl/protasktypeadd";
	}
}
