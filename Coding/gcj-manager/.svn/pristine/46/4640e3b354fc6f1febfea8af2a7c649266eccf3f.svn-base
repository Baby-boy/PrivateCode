package com.yd.gcj.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yd.gcj.entity.vo.YdMangerTenderVo;
import com.yd.gcj.system.service.YdMangerServiceSystemTender;
import com.yd.gcj.tool.ObjectMapperFactory;

/**
 * description(项目任务所对应的投标信息管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemTender<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemTender ydMangerServiceSystemTender;
	
	/**
	 * description(根据task_id查询对应的投标信息)
	 * @param
	 * @param task_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryTenderByTaskId/{task_id}")
	public String queryTenderByTaskId(@PathVariable Integer task_id,Model model,Integer p,String tender_pname,String tender_uphone){
		//当前页
		if (p==null) {
			p=1;
			PageHelper .startPage(p,8);
		}else {
			PageHelper .startPage(p,8);
		};
		List<YdMangerTenderVo> ydMangerTender = ydMangerServiceSystemTender.queryTenderByTaskId(task_id,tender_pname,tender_uphone);
		PageInfo<YdMangerTenderVo> pageInfo = new PageInfo<YdMangerTenderVo>(ydMangerTender);
		//总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total % 8 != 0) {
			totalPage = total / 8 + 1;
		} else {
			totalPage = total / 8;
		}

		model.addAttribute("p", p);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("tender_pname",tender_pname);
		model.addAttribute("task_id",task_id);
		model.addAttribute("tender_uphone",tender_uphone);
		model.addAttribute("ydMangerTender",ydMangerTender);
		return "system/tender/tender";
	}
	
	/**
	 * description(条件查询)
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryTenderByTaskId")
	public String queryTenderByCondition(Integer task_id,Model model,Integer p,String tender_uname,String tender_uphone){
		//当前页
		if (p==null) {
			p=1;
			PageHelper .startPage(p,5);
		}else {
			PageHelper .startPage(p,5);
		};
		List<YdMangerTenderVo> ydMangerTender = ydMangerServiceSystemTender.queryTenderByCondition(task_id,tender_uname,tender_uphone);
		PageInfo<YdMangerTenderVo> pageInfo = new PageInfo<YdMangerTenderVo>(ydMangerTender);
		//总页数
		Integer totalPage = null;
		Integer total = (int) pageInfo.getTotal();
		if (total % 5 != 0) {
			totalPage = total / 5 + 1;
		} else {
			totalPage = total / 5;
		}

		model.addAttribute("p", p);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("tender_uname",tender_uname);
		model.addAttribute("tender_uphone",tender_uphone);
		model.addAttribute("ydMangerTender",ydMangerTender);
		return "system/tender/tender";
	}
	
	/**
	 * description(根据tender_id查询当前投标的详细信息)
	 * @param
	 * @param tender_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryTenderByTenderId/{tender_id}")
	public String queryTenderByTenderId(@PathVariable Integer tender_id,Model model){
		YdMangerTenderVo ydMangerTenderVo = ydMangerServiceSystemTender.queryTenderByTenderId(tender_id);
		ObjectMapperFactory.doIt(ydMangerTenderVo);
		model.addAttribute("ydMangerTenderVo",ydMangerTenderVo);
		return "system/tender/tenderdetails";
	}
}
