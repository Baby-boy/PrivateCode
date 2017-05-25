package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.c.member.entity.vo.YdMemberRefundOrderVo;
import com.yd.dby.c.member.service.YdMemberServiceOrderRefund;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/member/refund")
public class YdMemberControllerOrderRefund {
	
	@Autowired
	private YdMemberServiceOrderRefund ydMemberServiceOrderRefund;


	/**
	 * 发起-退款
	 * @param reasonId 			退款原因id
	 * @param reasonContent		退款原因文本
	 * @param reasonNum			退款商品数量
	 * @param buyerMessage		退款说明
	 * @param picInfo			退款凭证-图片
	 * @param goodsId			退款商品id
	 * @param type				退款类型    1：单个商品     2：全部商品
	 * @return
	 */
	@RequestMapping(value = "store", method = RequestMethod.POST)
	@ResponseBody
	public Object store(Integer reasonId, String reasonContent, Integer reasonNum,
			String buyerMessage, String picInfo, Integer goodsId,
			Integer type, Integer orderId, Integer orderGoodsId) {
		return ydMemberServiceOrderRefund.store(reasonId, reasonContent, reasonNum, buyerMessage, picInfo, goodsId, type, orderId, orderGoodsId);
	}
	
	
	
	/**
	 * 售后列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberOrderRefund", "index", request);
	}
	
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "detail")
	public ModelAndView detail(@RequestBody Map<String, HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberOrderRefund", "detail", request);
	}
}
