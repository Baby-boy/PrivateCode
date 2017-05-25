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
import com.yd.gcj.entity.YdMangerDictionaryData;
import com.yd.gcj.system.service.YdMangerServiceSystemTaskSize;

/**
 * description(项目任务所对应的合同信息管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemTaskSize<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemTaskSize ydMangerServiceSystemTaskSize;
	
	/**
	 * description(查询所有的项目规模)
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllTaskSize")
	public String queryAllTaskSize(Integer p,String dictdata_value, Model model){
		//当前页
		if (p==null) {
			p=1;
			PageHelper .startPage(p,8);
		}else {
			PageHelper .startPage(p,8);
		};
		
		List<YdMangerDictionaryData> dictionaryDataList = ydMangerServiceSystemTaskSize.queryAllTaskSize(dictdata_value);
		PageInfo<YdMangerDictionaryData> pageInfo = new  PageInfo<YdMangerDictionaryData>(dictionaryDataList);
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
		model.addAttribute("dictdata_value",dictdata_value);
		model.addAttribute("dictionaryDataList", dictionaryDataList);
		return "system/rwgl/tasksize";
	}
	
	/**
	 * description(根据dict_id删除指定的任务规模)
	 * @param
	 * @param dict_id
	 * @return
	 */
	@RequestMapping("/deleteTaskSzie")
	@ResponseBody
	public Object deleteTaskSzie(Integer dict_id){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer delNum= ydMangerServiceSystemTaskSize.deleteTaskSzie(dict_id);
		if (delNum>0) {
			map.put("msg",true);
		} else {
			map.put("msg",false);
		}
		return map;
	}
	
	/**
	 * description(修改之前先根据dict_id查询当前任务规模的信息)
	 * @param
	 * @param dict_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateTaskSizeByDictId/{dict_id}")
	public String queryTaskSizeByDictId(@PathVariable Integer dict_id,Model model){

		YdMangerDictionaryData ydMangerDictionaryData = ydMangerServiceSystemTaskSize.queryTaskSizeByDictId(dict_id);
		model.addAttribute("ydMangerDictionaryData",ydMangerDictionaryData);
		return "system/rwgl/tasksizeupdate";
	}
	
	@RequestMapping("/updateTaskSize")
	@ResponseBody
	public Object updateTaskSize(YdMangerDictionaryData ydMangerDictionaryData){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer updateNum = ydMangerServiceSystemTaskSize.updateTaskSize(ydMangerDictionaryData);
		if (updateNum>0) {
			map.put("msg",true);
		}else {
			map.put("msg",false);
		}
		return map;
	}
	
	@RequestMapping("/addTaskSize")
	@ResponseBody
	public Object addTaskSize(YdMangerDictionaryData ydMangerDictionaryData){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer addNum = ydMangerServiceSystemTaskSize.addTaskSize(ydMangerDictionaryData);
		if (addNum>0) {
			map.put("msg",true);
		}else {
			map.put("msg",false);
		}
		return map;
	}
}
