package com.yd.dby.c.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.mapper.YdMemberMapperFavorite;
import com.yd.dby.c.member.mapper.YdMemberMapperGoods;
import com.yd.dby.c.member.service.YdMemberServiceFavorite;

@Service("_YdMemberServiceFavorite")
public class YdMemberServiceImplFavorite implements YdMemberServiceFavorite {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberMapperFavorite ydWebMapperMemberFollow;

	@Autowired
	private YdMemberMapperGoods ydMemberMapperGoods;

	@Override
	public Map<String, Object> goods(HashMap<String, Object> request) {

		Integer perPage = 10;

		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("user_id", session.getAttribute("user_id"));

		map.put("data", ydWebMapperMemberFollow.goods(request));
		Integer total = ydWebMapperMemberFollow.total(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	@Override
	public Map<String, Object> ctcs(HashMap<String, Object> request) {

		Integer perPage = 10;

		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("user_id", session.getAttribute("user_id"));

		map.put("data", ydWebMapperMemberFollow.ctcs(request));
		Integer total = ydWebMapperMemberFollow.total(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	@Override
	public Map<String, Object> store(HashMap<String, Object> request) {

		Integer perPage = 10;

		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("user_id", session.getAttribute("user_id"));

		List<HashMap<String, Object>> listStore = ydWebMapperMemberFollow.store(request);

		for (HashMap<String, Object> hashMap : listStore) {
			hashMap.put("goods", ydMemberMapperGoods.followStoreGoods((Integer) hashMap.get("fav_fid")));
		}

		map.put("data", listStore);

		Integer total = ydWebMapperMemberFollow.total(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	@Override
	public Map<String, Object> destroy(HashMap<String, Object> request) {
		request.put("user_id", session.getAttribute("user_id"));
		ydWebMapperMemberFollow.destroy(request);
		return goods(request);
	}

}