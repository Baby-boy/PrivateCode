package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.c.member.service.YdMemberServiceCtc;

/**
 * @Description: TODO
 * @author lgl
 * @date 2017年2月7日 下午2:55:49
 */
@Controller
@RequestMapping(value = "/api/v1/get/member/ctc")
public class YdMemberControllerCtc {

	@Autowired
	private YdMemberServiceCtc ydMemberServiceCtc;

	// 懒鱼首页
	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtc", "index", request);
	}

	// 懒鱼--发布
	@RequestMapping(value = "create")
	public Object create(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtc", "create", request);
	}

	// 懒鱼--存储
	@RequestMapping(value = "store")
	public Object store(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtc", "store", request);
	}

	// 懒鱼--编辑
	@RequestMapping(value = "edit")
	public Object edit(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtc", "edit", request);
	}

	// 懒鱼--编辑
	@RequestMapping(value = "update")
	public Object update(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtc", "update", request);
	}

	// 懒鱼--删除
	@RequestMapping(value = "delete")
	public Object delete(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtc", "delete", request);
	}
	
	//懒鱼 -- 下架
	@RequestMapping(value="soldOut")
	public Object soldOut(@RequestBody Map<String,HashMap<String, Object>> request){
		return YdMain.MAV("_YdWebServiceMemberCtc", "soldOut", request);
	}
	
	//懒鱼 重新上架
	@RequestMapping(value="grand")
	public Object ground(@RequestBody Map<String, HashMap<String,Object>> request){
		return YdMain.MAV("_YdWebServiceMemberCtc", "grand", request);
	}

	// 懒鱼--删除订单
	@RequestMapping(value = "deleteOrder")
	public Object deleteOrder(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtc", "deleteOrder", request);
	}

	// 懒鱼--取消订单
	@RequestMapping(value = "cancel")
	public Object cancel(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtc", "cancel", request);
	}

	/**
	 * 订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/order")
	public ModelAndView order(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberCtcOrder", "index", request);
	}

}
