package com.yd.dby.c.member.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdOrder;
import com.yd.dby.b.shop.entity.YdOrderGoods;
import com.yd.dby.c.member.entity.YdComment;
import com.yd.dby.c.member.entity.YdWish;
import com.yd.dby.c.member.mapper.YdMemberMapperOrder;
import com.yd.dby.c.member.mapper.YdmemberMapperWish;
import com.yd.dby.c.member.service.YdMemberServiceOrder;
import com.yd.dby.c.member.service.YdMemberServiceWish;
import com.yd.dby.utils.YdUtilResponse;

@SuppressWarnings("all")
@Service("_YdWebServiceMemberWish")
public class YdMemberServiceImpWish implements YdMemberServiceWish {

	@Autowired
	private YdmemberMapperWish ydmemberMapperWish;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 用户添加许愿
	 */
	@Override
	public Integer addWish(YdWish ydWish) {
		
		Integer addNum = ydmemberMapperWish.addWish(ydWish);
		return addNum;
	}

	/**
	 * 查询当前用户的许愿列表 
	 */
	@Override
	public Map<String, Object> queryWishAll(HashMap<String, Object> request) {
		Integer perPage = 10;
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("user_id", session.getAttribute("user_id"));
		request.put("state_service", request.get("state"));
		
		Integer total = ydmemberMapperWish.totalWish(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}
		
		Integer user_id = (Integer) session.getAttribute("user_id");
		request.put("user_id",user_id);
		List<HashMap<String,Object>> wishList = ydmemberMapperWish.queryWishAll(request);
		map.put("data", wishList);
		return map;
	}

}