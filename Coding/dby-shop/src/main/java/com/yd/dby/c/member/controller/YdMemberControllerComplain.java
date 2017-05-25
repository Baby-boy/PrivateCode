package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.c.member.entity.YdComplain;
import com.yd.dby.c.member.service.YdMemberServiceComplain;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/member/complain")
public class YdMemberControllerComplain {
	
	@Autowired
	private YdMemberServiceComplain ydMemberServiceComplain;


	/**
	 * 发起-投诉
	 * @return
	 */
	@RequestMapping(value = "store", method = RequestMethod.POST)
	@ResponseBody
	public Object store(YdComplain ydComplain) {
		return ydMemberServiceComplain.store(ydComplain);
	}
	
	
	
	/**
	 * 投诉列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberComplain", "index", request);
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "detail")
	public ModelAndView detail(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberComplain", "detail", request);
	}
	
	
	/**
	 * 取消
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(Integer complainId) {
		return ydMemberServiceComplain.delete(complainId);
	}
}
