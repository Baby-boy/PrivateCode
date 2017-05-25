package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.entity.User;
import com.yd.dby.c.member.mapper.YdMemberMapperFavorite;
import com.yd.dby.c.member.mapper.YdMemberMapperGoodsBuf;
import com.yd.dby.c.member.mapper.YdMemberMapperGoodsRecord;
import com.yd.dby.c.member.mapper.YdMemberMapperInfo;
import com.yd.dby.c.member.mapper.YdMemberMapperOrder;
import com.yd.dby.c.member.mapper.YdMemberMapperUser;
import com.yd.dby.c.member.service.YdMemberServiceUser;

@Service("_YdMemberServiceUser")
public class YdMemberServiceImplUser implements YdMemberServiceUser {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberMapperUser ydWebMapperMemberUser;

	@Autowired
	private YdMemberMapperInfo ydWebMapperMemberInfo;

	@Autowired
	private YdMemberMapperOrder ydWebMapperMemberOrder;

	@Autowired
	private YdMemberMapperGoodsBuf ydWebMapperMemberGoodsBuf;

	@Autowired
	private YdMemberMapperFavorite ydWebMapperMemberFollow;

	@Autowired
	private YdMemberMapperGoodsRecord ydWebMapperMemberGoodsRecord;

	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer user_id = (Integer) session.getAttribute("user_id");
		request.put("user_id", user_id);
		map.put("data", ydWebMapperMemberInfo.info(user_id));

		map.put("dataOrderTotal", ydWebMapperMemberUser.dataOrderTotal(request));

		map.put("dataCouponTotal", ydWebMapperMemberUser.dataCouponTotal(request));

		map.put("dataFollowTotal", ydWebMapperMemberUser.dataFollowTotal(request));

		request.put("buf_type", 2);
		map.put("dataHot", ydWebMapperMemberGoodsBuf.lists(request));

		// 关注的商品
		request.put("type", 2);
		map.put("dataFollow", ydWebMapperMemberFollow.goods(request));

		// 历史记录
		map.put("dataGoodsRecord", ydWebMapperMemberGoodsRecord.lists(request));

		Integer perPage = 10;

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);

		map.put("dataOrder", ydWebMapperMemberOrder.index(request));

		Integer total = ydWebMapperMemberOrder.total(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	// 查询个人详情
	@Override
	public User queryDetailByUid(Integer uid) {
		return ydWebMapperMemberUser.queryDetailByUid(uid);
	}

}