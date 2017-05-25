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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yd.gcj.entity.YdMangerTaskTemp;
import com.yd.gcj.system.service.YdMangerServiceSystemWorkHall;


/**
 * description(工作大厅管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemWorkHall<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemWorkHall ydMangerServiceSystemWorkHall;
	
	/**
	 * description(查询所有工作大厅的数据)
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping( "/queryAllWorkHall" )
	public String queryAllWorkHall(Integer p, String tasktemp_num, Model model){
		//当前页
		if (p==null) {
			p=1;
			PageHelper .startPage(p,8);
		}else {
			PageHelper .startPage(p,8);
		};
		List<YdMangerTaskTemp> taskTempList = ydMangerServiceSystemWorkHall.queryAllWorkHall(tasktemp_num);
		PageInfo<YdMangerTaskTemp> pageInfo = new PageInfo<YdMangerTaskTemp>(taskTempList);
		//总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total%8 !=0) {
			totalPage = total/8+1;
		}else {
			totalPage = total/8;
		}
						
		model.addAttribute("p",p);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("taskTempList", taskTempList);
		model.addAttribute("tasktemp_num",tasktemp_num);
		return "system/gzdt/workhall";
	}
	
	/**
	 * description(添加工作大厅数据)
	 * @param
	 * @param ydMangerTaskTemp
	 * @return
	 */
	@RequestMapping("/addWorkHall")
	@ResponseBody
	public Object addWorkHall(YdMangerTaskTemp ydMangerTaskTemp){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer addNum = ydMangerServiceSystemWorkHall.addWorkHall(ydMangerTaskTemp);
		if (addNum>0) {
			map.put("msg", true);
		}else {
			map.put("msg", false);
		}
		return map;
	}
	
	/**
	 * description(根据指定tasktemp_id删除工作大厅数据)
	 * @param
	 * @param tasktemp_id
	 * @return
	 */
	@RequestMapping("/deleteWorkHall")
	@ResponseBody
	public Object deleteWorkHall(Integer tasktemp_id){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer delNum = ydMangerServiceSystemWorkHall.deleteWorkHallByTaskTempId(tasktemp_id);
		if (delNum>0) {
			map.put("msg", true);
		}else {
			map.put("msg", false);
		}
		return map;
	}

	/**
	 * description(修改之前先根据tasktemp_id查到当前工作大厅的信息)
	 * @param
	 * @param tasktemp_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateWorkHallBytTaskTempId/{tasktemp_id}")
	public String queryWorkHallByTaskTempId(@PathVariable Integer tasktemp_id,Model model){
		YdMangerTaskTemp ydMangerTaskTemp = ydMangerServiceSystemWorkHall.queryWorkHallByTaskTempId(tasktemp_id);
		model.addAttribute("ydMangerTaskTemp",ydMangerTaskTemp);
		return "system/gzdt/workhallupdate";
	}
	
	/**
	 * description(根据tasktemp_id修改指定的大厅数据)
	 * @param
	 * @param ydMangerTaskTemp
	 * @return
	 */
	@RequestMapping("/updateWorkHall")
	@ResponseBody
	public Object updateWorkHall(YdMangerTaskTemp ydMangerTaskTemp){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer updateNum = ydMangerServiceSystemWorkHall.updateWorkHallByTaskTempId(ydMangerTaskTemp);
		if (updateNum>0) {
			map.put("msg", true);
		} else {
			map.put("msg",false);
		}
		return map;
	}
	
}
