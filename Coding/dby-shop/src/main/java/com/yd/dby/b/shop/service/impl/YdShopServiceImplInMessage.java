package com.yd.dby.b.shop.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.a.sys.entity.YdSysUserFull;
import com.yd.dby.b.shop.entity.YdCtcMessage;
import com.yd.dby.b.shop.entity.YdShopInMessage;
import com.yd.dby.b.shop.entity.YdShopMessage;
import com.yd.dby.b.shop.mapper.YdShopMapperInMessage;
import com.yd.dby.b.shop.service.YdShopServiceInMessage;
import com.yd.dby.d.seller.entity.YdSellerGoods;
import com.yd.dby.utils.YdUtilResponse;

@SuppressWarnings("all")
@Service("_YdShopServiceInMessage")
public class YdShopServiceImplInMessage implements YdShopServiceInMessage {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdShopMapperInMessage ydShopMapperInMessage;

	/**
	 * 用户关于普通商品给卖家留言
	 */
	@Override
	public Object inMessage(Integer im_store_id, Integer im_goods_id, String im_message) {
		// 普通商品
		YdShopInMessage ydShopInMessage = new YdShopInMessage();

		// 留言
		YdShopMessage ydShopMessage = new YdShopMessage();

		// 获取当前时间戳
		Date date = new Date();
		long time = date.getTime();
		String create_date = time + "";

		Integer user_id = null;
		try {
			// 判断用户是否登录
			user_id = (Integer) session.getAttribute("user_id");
			if (user_id == null) {
				return YdUtilResponse.fail("请先登录！！！");
			}
			// 根据留言用户id查询当前用户的会员名称和头像
			YdSysUserFull user = ydShopMapperInMessage.queryInMessageUser(user_id);
			if (user == null) {
				return YdUtilResponse.fail("请先登录！！！");
			}

			// 根据仓库id查询商品信息
			YdSellerGoods ydSellerGoods = ydShopMapperInMessage.queryGoods(im_goods_id);

			// 赋值给普通商品
			ydShopInMessage.setIm_depot_id(ydSellerGoods.getDepot_id());
			ydShopInMessage.setGoods_name(ydSellerGoods.getGoods_name());
			ydShopInMessage.setGoods_pic(ydSellerGoods.getGoods_cover());
			ydShopInMessage.setGoods_price(ydSellerGoods.getGoods_price());
			ydShopInMessage.setIm_store_id(im_store_id);
			ydShopInMessage.setUser_id(user_id);
			ydShopInMessage.setUser_name(user.getUser_nickname());
			ydShopInMessage.setUser_avatar(user.getUser_avatar());
			ydShopInMessage.setIm_message(im_message);

			// 保存普通商品留言
			Integer addNum = ydShopMapperInMessage.inMessage(ydShopInMessage);

			if (addNum > 0) {
				ydShopMessage.setMessage_author_id(user_id);
				ydShopMessage.setCreate_date(create_date);
				ydShopMessage.setMessage_author_name(user.getUser_username());
				ydShopMessage.setMessage_author_img(user.getUser_avatar());
				ydShopMessage.setMessage_obj_id(ydShopInMessage.getIm_mid());
				ydShopMessage.setMessage_content(im_message);

				// 保存到留言表
				Integer addMsgNum = ydShopMapperInMessage.addMessage(ydShopMessage);
				if (addMsgNum > 0) {
					return YdUtilResponse.success(null, "留言成功");
				} else {
					return YdUtilResponse.fail("留言失败");
				}
			} else {
				return YdUtilResponse.fail("留言失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return YdUtilResponse.fail("留言失败");
		}
	}

	/**
	 * 添加ctc商品留言
	 */
	@Override
	public Object ctcSave(Map<String, Object> request) {

		YdCtcMessage ydCtcMessage = new YdCtcMessage();
		ydCtcMessage.setCtc_id(Integer.valueOf(request.get("ctc_id") + ""));
		ydCtcMessage.setCtc_img(request.get("ctc_img") + "");
		ydCtcMessage.setCtc_name(request.get("ctc_name") + "");
		ydCtcMessage.setCtc_price((BigDecimal) request.get("ctc_price"));
		ydCtcMessage.setCtc_summary(request.get("ctc_summary") + "");

		// 获取当前时间戳
		Date date = new Date();
		long time = date.getTime();
		String create_date = time + "";
		Integer user_id = (Integer) session.getAttribute("user_id");
		if (user_id == null) {
			return YdUtilResponse.fail("请先登录");
		}
		// 根据user_id查询留言用户的昵称,头像
		YdSysUserFull messageUser = ydShopMapperInMessage.queryMessageUserByUserId(user_id);

		Integer sellerUserId = (Integer) request.get("seller_userId");

		// 根据卖家id查询昵称 头像
		YdSysUserFull sellerUser = ydShopMapperInMessage.querySellerUserByUserId(sellerUserId);

		if (messageUser != null && sellerUser != null) {
			request.put("message_author_id", user_id);
			request.put("message_author_name", messageUser.getUser_nickname());
			request.put("message_author_img", messageUser.getUser_avatar());

			request.put("message_receive_id", sellerUserId);
			request.put("message_receive_name", sellerUser.getUser_nickname());
			request.put("message_receive_img", sellerUser.getUser_avatar());
			request.put("message_farther_id", 0);
			request.put("create_date", create_date);

			// 添加ctc商品留言之前查询是否存在此商品的留言
			HashMap<String, Object> ctcMap = ydShopMapperInMessage.queryCtcMessage(Integer.valueOf(request.get("ctc_id") + ""));

			// 没有当前商品留言
			if (ctcMap == null) {
				// 添加ctc商品留言
				Integer addNum = ydShopMapperInMessage.ctcSaveMessage(ydCtcMessage);
				request.put("message_obj_id", ydCtcMessage.getMessae_ctc_id());

				if (addNum > 0) {
					// 添加message数据
					Integer addMsgNum = ydShopMapperInMessage.saveMessage(request);

					if (addMsgNum > 0) {
						return YdUtilResponse.success(null, "留言成功");
					} else {
						
						return YdUtilResponse.fail("留言失败");
					}
				} else {
					return YdUtilResponse.fail("留言失败");
				}
			} else {
				request.put("message_ctc_id", ctcMap.get("message_ctc_id"));
				// 更新当前商品
				Integer updateNum = ydShopMapperInMessage.updateCtcMessage(request);
				request.put("message_obj_id", ctcMap.get("message_ctc_id"));
				// 添加message数据
				Integer updateMsgNum = ydShopMapperInMessage.saveMessage(request);
				if (updateMsgNum > 0) {
					return YdUtilResponse.success(null, "留言成功");
				} else {
					return YdUtilResponse.fail("留言失败");
				}
			}

		} else {

			return YdUtilResponse.fail("留言失败");
		}
	}

	/**
	 * 回复留言
	 */
	@Override
	public Object ctcRecive(Map<String, Object> request) {
		//获取当前登录用户的id
		Integer user_id = (Integer) session.getAttribute("user_id");
		//判断是买家或者卖家回复
		
		//获取接收人的id
		Integer recive_id = (Integer) request.get("receive_id");
		
		
		return null;
	}

}