package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.dby.YdMain;
import com.yd.dby.c.member.service.YdMemberServcieCirculation;

/**
 * @author Administrator 
 * 说明(流通 置换,列表 删除 详情)
 */
@RequestMapping(value = "/member/circulation")
@Controller
public class YdMemberControllerCirculation {

	@Autowired
	private YdMemberServcieCirculation ydMemberServcieCirculation;
	
	/**
	 * @param request
	 * @return
	 * 方法作用(流通 置换 列表)
	 */
	@RequestMapping(value = "queryCirculation")
	public Object queryCirculation(@RequestBody Map<String,HashMap<String, Object>> request) {
		return YdMain.MAV("_YdMemberServcieCirculation", "queryCirculationByUserId", request);
	}
	
	/**
	 * @param request
	 * @return
	 * 方法作用(流通置换  )
	 */
	@RequestMapping(value="deleteCirculation")
	public Object deleteCirculation(@RequestBody Map<String, HashMap<String, Object>> request){
		
		return YdMain.MAV("_YdMemberServcieCirculation", "delete", request);
	}
	
	/**
	 * @param id(流通置换 id)
	 * @return
	 * 方法作用(查询ctc商品详情)
	 */
	@RequestMapping(value="circulationDetailes")
	public Object circulationDetailes(@RequestBody Map<String, HashMap<String, Object>> request){
		return YdMain.MAV("_YdMemberServcieCirculation","circulationDetailes", request);
	}
	
}
