package com.yd.dby.c.member.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.yd.dby.c.member.entity.YdOnLineBookIng;
import com.yd.dby.c.member.mapper.YdMemberMapperCirculation;
import com.yd.dby.c.member.service.YdMemberServcieCirculation;
import com.yd.dby.utils.YdUtilResponse;

@SuppressWarnings("all")
@Service("_YdMemberServcieCirculation")
public class YdMemberServiceImplCirculation implements YdMemberServcieCirculation {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberMapperCirculation ydMemberMapperCirculation;

	/**
	 * 查询当前登录用户的流通 置换
	 */
	@Override
	public Map<String, Object> queryCirculationByUserId(HashMap<String, Object> request) {
		Integer perPage = 10;

		Map<String, Object> map = new HashMap<String, Object>();

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("user_id", session.getAttribute("user_id"));
		request.put("state_service", request.get("state"));

		Integer total = ydMemberMapperCirculation.totalCirculation(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		// 获取当前登录用户的id
		Integer user_id = (Integer) session.getAttribute("user_id");

		// 根据id查询当前用户的流通置换
		List<HashMap<String, Object>> culList = ydMemberMapperCirculation.queryCirculationByUserId(request);
		for (HashMap<String, Object> hashMap : culList) {
			String time = (String) hashMap.get("created_time");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sdTime = sdf.format(new Date(Long.parseLong(time)));
			hashMap.put("created_time", sdTime);

		}
		map.put("data", culList);
		return map;
	}

	/**
	 * 流通置换 删除
	 */
	@Override
	public Object delete(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer user_id = (Integer) session.getAttribute("user_id");
		request.put("user_id", user_id);
		ydMemberMapperCirculation.delete(request);
		map.put("p", 1);
		return queryCirculationByUserId(map);
		
	}

	/**
	 * 查询流通弄置换 商品详情
	 */
	public Map<String, Object> circulationDetailes(HashMap<String, Object> request) {
		Map<String,Object> map = new HashMap<String, Object>();
		YdOnLineBookIng ydOnLineBookIng = ydMemberMapperCirculation.circulationDetailes(request);
		String created_time = ydOnLineBookIng.getCreated_time();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sdTime = sdf.format(new Date(Long.parseLong(created_time)));
		ydOnLineBookIng.setCreated_time(sdTime);
		map.put("data",ydOnLineBookIng);
		return map;
	}

}
