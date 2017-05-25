package com.yd.dby.d.seller.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.d.seller.service.YdSellerServiceCoupon;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月15日 上午10:05:38
 * 
 */
@Controller
@RequestMapping(value = "seller/coupon")
public class YdSellerControllerCoupon {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private YdSellerServiceCoupon ydSellerServiceCoupon;

	/**
	 * @param request
	 * @return
	 * 方法作用(查询优惠券列表)
	 */
	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceCoupon", "index", request);
	}

	/**
	 * @param request
	 * @return
	 * 方法作用(店铺添加优惠券跳转页面)
	 */
	@RequestMapping("create")
	public String couponCreate() {
		return "seller/temp/coupon/coupon_create";
	}

	/**
	 * @param request
	 * @return
	 * 方法作用(跳转到添加页面之后执行优惠券保存方法)
	 */
	@RequestMapping(value = "store")
	public ModelAndView store(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceCoupon", "store", request);
	}

	/**
	 * @param request
	 * @return
	 * 方法作用(跳转到优惠券修改页面之前先查询到当前优惠券的信息)
	 */
	@RequestMapping(value = "edit")
	public ModelAndView edit(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceCoupon", "edit", request);
	}


	/**
	 * @param request
	 * @return
	 * 方法作用(优惠券修改提交页面)
	 */
	@RequestMapping(value = "update")
	public ModelAndView update(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdSellerServiceCoupon", "update", request);
	}

	/**
	 * @param request
	 * @return
	 * 方法作用(删除优惠券)
	 */
	@RequestMapping(value = "destroy")
	public ModelAndView destroy(@RequestBody Map<String, HashMap<String, Object>> request,Integer coupon_id) {
		return YdMain.MAV("_YdSellerServiceCoupon", "destroy", request);
	}
}
