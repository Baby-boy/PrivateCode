package com.yd.dby.c.member.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdCtcOrder;
import com.yd.dby.c.member.mapper.YdMemberMapperCtcOrder;
import com.yd.dby.c.member.service.YdMemberServiceCtcOrder;

@Service("_YdWebServiceMemberCtcOrder")
public class YdMemberServiceImplCtcOrder implements YdMemberServiceCtcOrder {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberMapperCtcOrder ydWebMapperMemberCtcOrder;

	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		Integer perPage = 10;

		HashMap<String, Object> map = new HashMap<String, Object>();

		Integer user_id = (Integer) session.getAttribute("user_id");
		request.put("user_id", user_id);
		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);

		List<Map<String, Object>> list = ydWebMapperMemberCtcOrder.index(request);
		for (Map<String, Object> map2 : list) {
			Long time = Long.parseLong((String) map2.get("createdAt").toString());
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(time);
			String createdAt = formatter.format(calendar.getTime());
			map2.put("time", createdAt);
		}
		map.put("data", list);
		map.put("p", request.get("p"));
		map.put("user_id", user_id);
		Integer total = ydWebMapperMemberCtcOrder.total(request);
		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}
		return map;
	}

	@Override
	public YdCtcOrder queryOneCtcOrderDetailById(Integer ctc_order_id) {
		return ydWebMapperMemberCtcOrder.queryOneCtcOrderDetailById(ctc_order_id);
	}

}