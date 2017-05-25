package com.yd.gcj.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.gcj.entity.YdMangerContract;
import com.yd.gcj.system.service.YdMangerServiceSystemContract;
import com.yd.gcj.tool.ObjectMapperFactory;

/**
 * description(项目任务所对应的合同信息管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemContract<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemContract ydMangerServiceSystemContract;
	
	/**
	 * description(用一句话来描述这个方法的作用)
	 * @param
	 * @param task_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryContractByTaskId/{task_id}")
	public String queryContractByTaskId(@PathVariable Integer task_id,Model model){
		YdMangerContract ydMangerContract = ydMangerServiceSystemContract.queryContractByTaskId(task_id);
		ObjectMapperFactory.doIt(ydMangerContract);
		model.addAttribute("ydMangerContract",ydMangerContract);
		return "system/contract/contract";
	}
}
