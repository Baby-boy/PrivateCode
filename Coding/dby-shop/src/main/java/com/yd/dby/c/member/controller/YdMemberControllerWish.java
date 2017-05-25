package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.b.shop.entity.YdOrder;
import com.yd.dby.b.shop.entity.YdOrderGoods;
import com.yd.dby.c.member.entity.YdComment;
import com.yd.dby.c.member.entity.YdWish;
import com.yd.dby.c.member.service.YdMemberServiceOrder;
import com.yd.dby.c.member.service.YdMemberServiceWish;

/**
 * @author Administrator 说明(用户许愿)
 */

@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/member/wish")
public class YdMemberControllerWish {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberServiceWish ydMemberServiceWish;

	/**
	 * @param ydWish
	 * @return 方法作用(用户添加许愿)
	 */
	@RequestMapping(value = "/addWish")
	@ResponseBody
	public Object addWish(YdWish ydWish) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer user_id = (Integer) session.getAttribute("user_id");
		ydWish.setUser_id(user_id);
		System.out.println(ydWish.getWish_content());
		Integer addNum = ydMemberServiceWish.addWish(ydWish);
		if (addNum > 0) {
			map.put("msg", true);
		} else {
			map.put("msg", false);
		}
		return map;
	}

	@RequestMapping(value = "queryWish")
	public Object queryWish(@RequestBody Map<String,HashMap<String, Object>> request) {
		return YdMain.MAV("_YdWebServiceMemberWish", "queryWishAll", request);
	}
}
