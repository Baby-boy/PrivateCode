package com.yd.dby.c.member.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.entity.YdMemberCtc;
import com.yd.dby.c.member.mapper.YdMemberMapperCtc;
import com.yd.dby.c.member.mapper.YdMemberMapperCtcOrder;
import com.yd.dby.c.member.service.YdMemberServiceCtc;
import com.yd.dby.utils.YdUtilResponse;

@Service("_YdWebServiceMemberCtc")
public class YdMemberServiceImplCtc implements YdMemberServiceCtc {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberMapperCtc ydWebMapperMemberCtc;
	@Autowired
	private YdMemberMapperCtcOrder ydMemberMapperCtcOrder;

	// 我发布的
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {

		Integer perPage = 10;

		HashMap<String, Object> map = new HashMap<String, Object>();
		request.put("user_id", session.getAttribute("user_id"));
		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		List<HashMap<String, Object>> index = ydWebMapperMemberCtc.index(request);
		map.put("data", index);
		Integer total = ydWebMapperMemberCtc.total(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	@Override
	public Map<String, Object> create(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	@Override
	public Object store(HashMap<String, Object> request) {
		try {
			if (session.getAttribute("user_id") == null) {
				return YdUtilResponse.fail("请登录,再发布");
			}
			request.put("ctc_is_available", 1);
			request.put("user_id", session.getAttribute("user_id"));
			Integer id = ydWebMapperMemberCtc.store(request);
			return YdUtilResponse.success(id, "");
		} catch (Exception e) {
			e.printStackTrace();
			return YdUtilResponse.fail("新增失败");
		}
	}

	@Override
	public Map<String, Object> edit(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", ydWebMapperMemberCtc.info(Integer.parseInt(request.get("id").toString())));
		return map;
	}

	@Override
	public Object update(HashMap<String, Object> request) {
		try {
			System.out.println(request);
			Integer id = ydWebMapperMemberCtc.update(request);
			return YdUtilResponse.success(id, "");
		} catch (Exception e) {
			return YdUtilResponse.fail("更新失败");
		}
	}

	// 删除懒鱼订单
	@Override
	public Object deleteOrder(HashMap<String, Object> request) {
		try {
			// 修改订单状态已删除
			request.put("state", 9);
			Integer data = ydMemberMapperCtcOrder.update(request);
			return YdUtilResponse.success(data, "");
		} catch (Exception e) {
			return YdUtilResponse.fail("删除失败");
		}
	}

	// 取消懒鱼订单
	@Override
	public Object cancel(HashMap<String, Object> request) {
		try {
			// 修改订单状态为已取消
			request.put("state", 8);
			Integer data = ydMemberMapperCtcOrder.update(request);
			return YdUtilResponse.success(data, "");
		} catch (Exception e) {
			return YdUtilResponse.fail("取消失败");
		}
	}

	/**
	 * 查询所有的懒鱼列表
	 * 
	 * @author 作者 tpf
	 * @version 创建时间：2017年2月7日 上午11:20:10
	 */
	@Override
	public List<YdMemberCtc> queryAllCtcGoods(Integer cc_id, Integer _search, Integer lowPrice, Integer highPrice, Integer to) {
		List<YdMemberCtc> list = ydWebMapperMemberCtc.queryAllCtcGoods(cc_id, _search, lowPrice, highPrice, to);
		return list;
	}

	// 查询所有的懒鱼个数
	@Override
	public List<YdMemberCtc> queryTotal(Integer cc_id, Integer lowPrice, Integer highPrice) {
		return ydWebMapperMemberCtc.queryTotal(cc_id, lowPrice, highPrice);
	}

	// 查询出单个懒鱼的详情
	@Override
	public HashMap<String, Object> queryOneCtcDetailById(Integer ctc_id) {
		return ydWebMapperMemberCtc.queryOneCtcDetailById(ctc_id);
	}

	// 根据用户uid查询出所有的懒鱼
	@Override
	public List<YdMemberCtc> queryAllCtcByUid(Integer uid, Integer to) {
		return ydWebMapperMemberCtc.queryAllCtcByUid(uid, to);
	}

	// 删除懒鱼
	@Override
	public Object delete(HashMap<String, Object> request) {
		request.put("ctc_id", request.get("id"));
		request.put("ctc_is_available", 5);
		Integer updateNum = ydWebMapperMemberCtc.update(request);
		if (updateNum > 0) {
			return YdUtilResponse.success(null, "success");
		} else {
			return YdUtilResponse.fail("");
		}
	}

	/**
	 * 懒鱼 下架
	 */
	@Override
	public Object soldOut(HashMap<String, Object> request) {
		Integer updateNum = ydWebMapperMemberCtc.soldOut(request);
		if (updateNum>0) {
			return YdUtilResponse.success(null,"success");
		}else {
			return YdUtilResponse.fail("");
		}
	}

	/**
	 * 懒鱼 重新上架
	 */
	@Override
	public Object grand(HashMap<String, Object> request) {
		Integer updateNum = ydWebMapperMemberCtc.grand(request);
		if (updateNum > 0) {
			return YdUtilResponse.success(null, "success");
		} else {
			return YdUtilResponse.fail("");
		}
	}
}