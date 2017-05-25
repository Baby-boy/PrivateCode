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
import com.yd.gcj.entity.vo.YdMangerFeedbackVo;
import com.yd.gcj.system.service.YdMangerServiceSystemFeedback;
/**
 * description(用户反馈信息管理)
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemFeedback<HttpServletRequest> {
	
	@Autowired
	private YdMangerServiceSystemFeedback ydMangerServiceSystemFeedback;
	/**
	 * description(查询所有用户反馈的信息)
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping( "/queryAllFeedback" )
	public String queryAllFeedback(Integer p,String user_name,Integer fb_isread, Model model){
		//当前页
		if (p==null) {
			p=1;
			PageHelper .startPage(p,8);
		}else {
			PageHelper .startPage(p,8);
		};
		
		List<YdMangerFeedbackVo> feedbackList = ydMangerServiceSystemFeedback.queryAllFeedback(user_name,fb_isread);
		PageInfo<YdMangerFeedbackVo> pageInfo = new PageInfo<YdMangerFeedbackVo>(feedbackList);
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
		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("user_name",user_name);
		model.addAttribute("fb_isread",fb_isread);
		return "system/khfw/feedback";
	}
	
	/**
	 * description(根据指定的fb_id删除用户反馈信息)
	 * @param
	 * @param fb_id
	 * @return
	 */
	@RequestMapping("/delFeedback")
	@ResponseBody
	public Object delFeedback(Integer fb_id){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer delNum = ydMangerServiceSystemFeedback.delFeedback(fb_id);
		if (delNum>0) {
			map.put("msg", true);
		}else {
			map.put("msg", false);
		}
		return map;
	}
	
	/**
	 * description(管理员备注信息之前先查到当前客户反馈信息)
	 * @param
	 * @param fb_id
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateRemarkByFbId/{fb_id}")
	public String queryFeedBackByFbId(@PathVariable Integer fb_id,Model model){
		YdMangerFeedbackVo ydMangerFeedbackVo = ydMangerServiceSystemFeedback.queryFeedBackByFbId(fb_id);
		model.addAttribute("ydMangerFeedbackVo",ydMangerFeedbackVo);
		return "system/khfw/feedbackremark";
	}
	
	/**
	 * description(添加备注)
	 * @param
	 * @param ydMangerFeedbackVo
	 * @return
	 */
	@RequestMapping("/updateRemark")
	@ResponseBody
	public Object updateRemarkByFbId(YdMangerFeedbackVo ydMangerFeedbackVo){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer addNum = ydMangerServiceSystemFeedback.updateRemarkByFbId(ydMangerFeedbackVo);
		if (addNum>0) {
			map.put("msg",true);
		} else {
			map.put("msg",false);
		}
		return map;
	}
}
