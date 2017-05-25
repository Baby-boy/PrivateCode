package com.yd.gcj.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.gcj.entity.YdMangerLabel;
import com.yd.gcj.entity.YdMangerTaskType;
import com.yd.gcj.entity.vo.YdMangerLabelVo;
import com.yd.gcj.service.YdMangerServiceLabel;
import com.yd.gcj.system.service.YdMangerServiceSystemTaskType;
import com.yd.gcj.tool.ObjectMapperFactory;

/**
 * description(任务类型信息管理)
 * 
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemTaskType<HttpServletRequest> {

	@Autowired
	private YdMangerServiceSystemTaskType ydMangerServiceSystemTaskType;

	@Autowired
	private YdMangerServiceLabel serviceLabel;

	/**
	 * description(查询所有的任务类型)
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllTaskType")
	public String queryAllTaskType(Integer p, Model model) {
		// 当前页
		/*
		 * if (p==null) { p=1; PageHelper .startPage(p,8); }else { PageHelper
		 * .startPage(p,8); };
		 */

		List<YdMangerTaskType> taskTypeList = ydMangerServiceSystemTaskType.queryAllTaskType();
		List<YdMangerLabelVo> taskLabelVos = serviceLabel.$queryAll();
		/*
		 * PageInfo<YdMangerTaskType> pageInfo = new
		 * PageInfo<YdMangerTaskType>(taskTypeList); //总页数 Integer totalPage =
		 * null; Integer total = (int) pageInfo.getTotal(); if (total%8 !=0) {
		 * totalPage = total/8+1; }else { totalPage = total/8; }
		 * model.addAttribute("p",p); model.addAttribute("totalPage",totalPage);
		 */
		model.addAttribute("taskTypeList", taskTypeList);
		model.addAttribute("taskLabelVos", taskLabelVos);
		return "system/rwgl/tasktype";
	}

	/**
	 * description(根据指定的task_id删除任务类型信息)
	 * 
	 * @param
	 * @param taskt_id
	 * @return
	 */
	@RequestMapping("/deleteTaskType")
	@ResponseBody
	public Object deleteTaskType(Integer taskt_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer delNum = ydMangerServiceSystemTaskType.deleteTaskTypeByTasktId(taskt_id);
		if (delNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	@RequestMapping("/addTaskType")
	@ResponseBody
	public Object addAdmin(YdMangerTaskType ydMangerTaskType, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (ydMangerTaskType == null || ydMangerTaskType.getTaskt_name() == "") {
			map.put("msg", 100);
			return map;
		}
		String taskt_name = ydMangerTaskType.getTaskt_name();
		String name = taskt_name.trim();
		ydMangerTaskType.setTaskt_name(name);
		Integer addNum = ydMangerServiceSystemTaskType.addTaskType(ydMangerTaskType);

		if (addNum > 0) {
			// 添加成功
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(修改任务类型之前先根据taskType_id查询当前任务类型信息)
	 * 
	 * @param
	 * @param task_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateTaskTypeByTaskTypeId/{taskt_id}")
	public String updateTaskTypeByTaskTypeId(@PathVariable Integer taskt_id, Model model) {
		YdMangerTaskType ydMangerTaskType = ydMangerServiceSystemTaskType.updateTaskTypeByTaskTypeId(taskt_id);
		model.addAttribute("ydMangerTaskType", ydMangerTaskType);
		return "system/rwgl/tasktypeupdate";
	}

	/**
	 * description(修改任务类型信息)
	 * 
	 * @param
	 * @param ydMangerTaskType
	 * @return
	 */
	@RequestMapping("/updateTaskType")
	@ResponseBody
	public Object updateTaskType(YdMangerTaskType ydMangerTaskType) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer updateNum = ydMangerServiceSystemTaskType.updateTaskType(ydMangerTaskType);
		if (updateNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(查询所有的专业及任务类型)
	 * 
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping("/professionalTaskType")
	public String professionalTaskType(Integer p, Model model) {
		List<YdMangerLabelVo> taskLabelVos = serviceLabel.$queryAll();
		model.addAttribute("taskLabelVos", taskLabelVos);
		ObjectMapperFactory.doIt(taskLabelVos);
		return "system/rwgl/professionaltasktype";
	}

	/**
	 * description(查询所有的专业及任务类型)
	 * 
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateProTaskType")
	@ResponseBody
	public Object updateProTaskType(YdMangerLabel ydMangerLabel) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer updateNum = serviceLabel.$update(ydMangerLabel);
		if (updateNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(根据指定的label_id删除专业及任务类型信息)
	 * 
	 * @param
	 * @param label_id
	 * @return
	 */
	@RequestMapping("/deleteProTaskType")
	@ResponseBody
	public Object deleteProTaskType(Integer label_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer delNum = serviceLabel.$delete(label_id);
		if (delNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	@RequestMapping("/addProTaskType")
	@ResponseBody
	public Object addProTaskType(YdMangerLabel ydMangerLabel, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (ydMangerLabel == null || ydMangerLabel.getLabel_name() == "") {
			map.put("msg", 100);
			return map;
		}
		String taskt_name = ydMangerLabel.getLabel_name();
		String name = taskt_name.trim();
		ydMangerLabel.setLabel_name(name);
		Integer addNum = serviceLabel.$insert(ydMangerLabel);

		if (addNum > 0) {
			// 添加成功
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(查询所有的专业及任务类型)
	 * 
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddProTaskType")
	public String toAddProTaskType(Integer p, Model model) {
		List<YdMangerLabelVo> taskLabelVos = serviceLabel.$queryAll();
		model.addAttribute("taskLabelVos", taskLabelVos);
		ObjectMapperFactory.doIt(taskLabelVos);
		return "/system/rwgl/profestasktypeadd";
	}
}
