package com.yd.dby.c.member.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.c.member.entity.YdComplain;
import com.yd.dby.c.member.entity.YdOrderRefund;
import com.yd.dby.c.member.entity.vo.YdMemberRefundOrderVo;
import com.yd.dby.c.member.mapper.YdMemberMapperComplain;
import com.yd.dby.c.member.mapper.YdMemberMapperOrder;
import com.yd.dby.c.member.service.YdMemberServiceComplain;
import com.yd.dby.utils.YdUtilResponse;
import com.yd.dby.utils.date.YdUtilDate;

@SuppressWarnings("all")
@Service("_YdWebServiceMemberComplain")
public class YdMemberServiceImplComplain implements YdMemberServiceComplain {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberMapperComplain ydMemberMapperComplain;

	@Autowired
	private YdMemberMapperOrder ydMemberMapperOrder;

	/**
	 * 投诉列表
	 */
	@Override
	public Map<String, Object> index(HashMap<String, Object> request) {
		Integer perPage = 10;

		HashMap<String, Object> map = new HashMap<String, Object>();

		request.put("to", (Integer.valueOf(request.get("p").toString()) - 1) * perPage);
		request.put("perPage", perPage);
		request.put("user_id", session.getAttribute("user_id"));
		
		List<YdOrderRefund> saleService = ydMemberMapperComplain.lists(request);

		map.put("data", saleService);

		Integer total = ydMemberMapperComplain.total(request);

		map.put("p", request.get("p"));

		if (total % perPage == 0) {
			map.put("totalPage", Math.ceil(total / perPage));
		} else {
			map.put("totalPage", Math.ceil(total / perPage) + 1);
		}

		return map;
	}

	/**
	 * 投诉详情
	 */
	@Override
	public YdMemberRefundOrderVo info(Integer id) {
		Integer user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		YdMemberRefundOrderVo ydMemberRefundOrderVo = ydMemberMapperComplain.info(user_id, id);
		String add_time = ydMemberRefundOrderVo.getAdd_time();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long parseLong = Long.parseLong(add_time);
		ydMemberRefundOrderVo.setFormat_time(sdf.format(parseLong));
		;

		Integer goods_num = ydMemberRefundOrderVo.getGoods_num();
		Double goods_price = ydMemberRefundOrderVo.getGoods_price();
		Double goods_pay_price = goods_num * goods_price;
		ydMemberRefundOrderVo.setGoods_pay_price(goods_pay_price);

		return ydMemberRefundOrderVo;
	}

	
	/**
	 * 发起退款
	 */
	@Override
	public Object store(YdComplain ydComplain) {

		Integer userId = (Integer) session.getAttribute("user_id");
		HashMap<String, Object> orderMap = ydMemberMapperOrder.info(userId, ydComplain.getOrder_id());
		
		ydComplain.setAccuser_id( Integer.parseInt( orderMap.get("buyer_id").toString() ) );
		ydComplain.setAccuser_name( orderMap.get("buyer_name").toString() );
		ydComplain.setAccused_id( Integer.parseInt( orderMap.get("store_id").toString() ) );
		ydComplain.setAccused_name( orderMap.get("store_name").toString() );
		ydComplain.setComplain_datetime( YdUtilDate.currentMillisString() );
		ydComplain.setComplain_state(1);
		ydComplain.setComplain_active(1);
		
		try {
			ydMemberMapperComplain.store(ydComplain);
			return YdUtilResponse.success(null, "提交成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("提交失败");
		}
	}

	
	/**
	 * 退款-退货- 详情
	 */
	@Override
	public Object detail(HashMap<String, Object> request) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		Integer userId = Integer.parseInt(session.getAttribute("user_id").toString());
		
		YdMemberRefundOrderVo ydMemberRefundOrderVo = ydMemberMapperComplain.info(userId, Integer.parseInt( request.get("refundId").toString() ));
		returnMap.put("data", ydMemberRefundOrderVo);
		returnMap.put("dataGoods", ydMemberMapperOrder.orderGoodsRefund(ydMemberRefundOrderVo.getOrder_id()));
		returnMap.put("dataOrder", ydMemberMapperOrder.info(userId, ydMemberRefundOrderVo.getOrder_id()) );
		return returnMap;
	}

	
	/**
	 * 取消
	 */
	@Override
	public Object delete(Integer complainId) {
		Integer userId = Integer.parseInt(session.getAttribute("user_id").toString());
		try {
			ydMemberMapperComplain.delete(complainId, userId);
			return YdUtilResponse.success(null, "取消成功");
		} catch (Exception e) {
			return YdUtilResponse.fail("取消失败");
		}
	}
}