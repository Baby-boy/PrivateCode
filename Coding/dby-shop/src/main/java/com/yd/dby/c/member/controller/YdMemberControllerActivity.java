package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.dby.c.member.entity.YdActivity;
import com.yd.dby.c.member.service.YdMemberServiceActivity;
import com.yd.dby.d.seller.entity.YdSellerGoods;

/**
 * @author Administrator 说明(活动)
 */
@Controller
public class YdMemberControllerActivity {

	@Autowired
	private YdMemberServiceActivity ydMemberServiceActivity;

	/**
	 * @param model
	 * @param id
	 * @return 方法作用(查询活动商品并分页)
	 */
	@RequestMapping(value = "activity")
	public String activity(Model model, Integer id/*  */, Integer p) {

		if (p == null || p == 0) {
			p = 1;
		}
		Integer to = (p - 1) * 40;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//查询活动的详细信息
		YdActivity ydActivity = ydMemberServiceActivity.queryActivityDetailsById(id);
		
		//查询所有的活动商品
		List<YdSellerGoods> ydSellerGoodsList = ydMemberServiceActivity.queryActivityByActivityId(id, to);
		List<YdSellerGoods> tatols = ydMemberServiceActivity.queryTotal(id);
		if (tatols != null && !tatols.isEmpty()) {
			map.put("total", tatols.size());
		} else {
			map.put("total", 0);
		}
		map.put("ydSellerGoodsList", ydSellerGoodsList);
		map.put("p", p);
		map.put("to", to);
		map.put("perPage", 40);
		if (tatols.size() % 40 == 0) {
			map.put("totalPage", Math.ceil(tatols.size() / 40));
		} else {
			map.put("totalPage", Math.ceil(tatols.size() / 40) + 1);
		}
		model.addAttribute("ydSellerGoodsList", ydSellerGoodsList);
		model.addAttribute("ydActivity", ydActivity);
		model.addAttribute("data", map);
		return "member/temp/activity/activityDetails";
	}
}
