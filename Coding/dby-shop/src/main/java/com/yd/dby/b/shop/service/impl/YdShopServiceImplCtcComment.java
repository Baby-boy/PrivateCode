package com.yd.dby.b.shop.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdCtcComment;
import com.yd.dby.b.shop.entity.YdCtcOrder;
import com.yd.dby.b.shop.mapper.YdShopMapperCtcComment;
import com.yd.dby.b.shop.mapper.YdShopMapperCtcOrder;
import com.yd.dby.b.shop.service.YdShopServiceCtcComment;
import com.yd.dby.c.member.service.YdMemberServiceCtcOrder;
import com.yd.dby.utils.YdUtilResponse;

@Service("_CtcComment")
public class YdShopServiceImplCtcComment implements YdShopServiceCtcComment {
	@Autowired
	private YdShopMapperCtcComment ydShopMapperCtcComment;

	@Autowired
	private YdShopMapperCtcOrder ydMemberServiceCtcOrder;

	@Autowired
	private HttpSession session;

	// 根据卖家uid查询出所有的评论
	@Override
	public List<YdCtcComment> queryAllCtcCommentBySellerId(Integer uid) {
		return ydShopMapperCtcComment.queryAllCtcCommentBySellerId(uid);
	}

	@Override
	public List<YdCtcComment> queryAllCtcCommentsBySellerId(Integer uid, Integer type, Integer to) {
		return ydShopMapperCtcComment.queryAllCtcCommentsBySellerId(uid, type, to);
	}

	@Override
	public Integer goodCounts(Integer uid, Integer type) {
		return ydShopMapperCtcComment.goodCounts(uid, type);
	}

	// 发表评价
	@Override
	public Object addComment(String data, Integer ctc_order_id, Integer single) {
		YdCtcOrder ydCtcOrder = ydMemberServiceCtcOrder.queryOneDetailById(ctc_order_id);
		ydCtcOrder.setState(7);
		Integer updateNum = ydMemberServiceCtcOrder.update(ydCtcOrder);
		if (updateNum < 1) {
			return YdUtilResponse.fail("评价失败");
		}
		YdCtcComment ydCtcComment = new YdCtcComment();
		// 卖出者id
		Integer user_id = ydCtcOrder.getUser_id();
		ydCtcComment.setSeller_id(user_id);
		// 买家uid
		Integer buyer_id = ydCtcOrder.getBuyer_id();
		ydCtcComment.setUser_id(buyer_id);
		// 购买时间
		String created_at = ydCtcOrder.getCreated_at();
		ydCtcComment.setCtc_comment_buy_time(created_at);

		ydCtcComment.setCtc_comment_is_name(single);
		Date date = new Date();
		long time = date.getTime();
		ydCtcComment.setCtc_comment_created_time("" + time);
		Integer adNum = null;
		JSONObject jsonObject = JSONObject.fromObject(data);
		Iterator iterator = jsonObject.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			JSONObject value = (JSONObject) jsonObject.get(key);
			String contentString = value.getString("ctc_comment_content");
			String goodsId = value.getString("ctc_id");
			String score = value.getString("score");
			// 设置评分等级
			if (Integer.parseInt(score) >= 4) {
				// 好评
				ydCtcComment.setCtc_comment_grade(1);
			} else if (Integer.parseInt(score) <= 2) {
				ydCtcComment.setCtc_comment_grade(3);
			} else {
				ydCtcComment.setCtc_comment_grade(2);
			}
			ydCtcComment.setCtc_comment_score(Integer.parseInt(score));
			// 设置评论内容
			ydCtcComment.setCtc_comment_content(contentString);
			// 设置商品id
			ydCtcComment.setCtc_id(Integer.parseInt(goodsId));
		}
		adNum = ydShopMapperCtcComment.insertSelective(ydCtcComment);
		if (adNum > 0) {
			return YdUtilResponse.success(null, "评价成功");
		} else {
			return YdUtilResponse.fail("评价失败");
		}
	}
}
