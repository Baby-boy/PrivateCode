package com.yd.dby.b.shop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.b.shop.entity.YdCtcComment;
import com.yd.dby.b.shop.entity.YdCtcOrder;
import com.yd.dby.b.shop.service.YdShopServiceCtcComment;
import com.yd.dby.b.shop.service.YdShopServiceFavorite;
import com.yd.dby.b.shop.service.YdShopServicePay;
import com.yd.dby.c.member.entity.User;
import com.yd.dby.c.member.entity.YdMemberCtc;
import com.yd.dby.c.member.service.YdMemberServiceCtc;
import com.yd.dby.c.member.service.YdMemberServiceCtcInMsg;
import com.yd.dby.c.member.service.YdMemberServiceCtcOrder;
import com.yd.dby.c.member.service.YdMemberServiceUser;

/**
 * @Description: TODO
 * @author lgl
 * @date 2017年2月7日 下午2:55:49
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/ctc")
public class YdShopControllerCtc {

	@Autowired
	private HttpSession session;

	@Autowired
	private YdMemberServiceCtc ydMemberServiceCtc;

	@Autowired
	private YdMemberServiceUser ydMemberServiceUser;

	@Autowired
	private YdShopServicePay ydShopServicePay;

	@Autowired
	private YdShopServiceCtcComment ydShopServiceCtcComment;

	@Autowired
	private YdMemberServiceCtcOrder ydMemberServiceCtcOrder;
	@Autowired
	private YdShopServiceFavorite ydShopServiceFavorite;

	@Autowired
	private YdMemberServiceCtcInMsg ydMemberServiceCtcInMsg;

	/**
	 * 查询所有的懒鱼列表
	 * 
	 * @param _search
	 *            (排序类型1 ,发布时间2,价格)
	 * @param model
	 * @param p分页p
	 * @param lowPrice低价区间
	 * @param highPrice高价区间
	 * @return 方法作用()
	 */
	@RequestMapping(value = "/queryAllCtcGoods", method = RequestMethod.GET)
	public Object queryAllCtcGoods(Integer cc_id, Integer _search, Model model, Integer p, Integer lowPrice, Integer highPrice) {
		if (p == null || p == 0) {
			p = 1;
		}
		Integer to = (p - 1) * 40;
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<YdMemberCtc> list = ydMemberServiceCtc.queryAllCtcGoods(cc_id, _search, lowPrice, highPrice, to);
		List<YdMemberCtc> tatols = ydMemberServiceCtc.queryTotal(cc_id, lowPrice, highPrice);
		if (tatols != null && !tatols.isEmpty()) {
			map.put("total", tatols.size());
		} else {
			map.put("total", 0);
		}
		map.put("ctcs", list);
		map.put("lowPrice", lowPrice);
		map.put("highPrice", highPrice);
		map.put("_search", _search);
		map.put("cc_id", cc_id);
		map.put("p", p);
		map.put("to", to);
		map.put("perPage", 40);
		if (tatols.size() % 40 == 0) {
			map.put("totalPage", Math.ceil(tatols.size() / 40));
		} else {
			map.put("totalPage", Math.ceil(tatols.size() / 40) + 1);
		}
		model.addAttribute("date", map);
		return "shop/ctc/ctcgoods";
	}

	/**
	 * 根据id查看单个懒鱼的详情
	 * 
	 * @param ctc_id
	 *            懒鱼id
	 * @param model
	 * @return 方法作用()
	 */
	@RequestMapping(value = "/CtcComment/{ctc_id}", method = RequestMethod.GET)
	public Object queryOneCtcDetailById(@PathVariable("ctc_id") Integer ctc_id, Model model) {
		HashMap<String, Object> ydMemberCtc = ydMemberServiceCtc.queryOneCtcDetailById(ctc_id);
		// 判断是否已收藏
		boolean is_favorite = ydShopServiceFavorite.isFavorite(ctc_id, 4);
		model.addAttribute("item", ydMemberCtc);
		model.addAttribute("is_favorite", is_favorite);
		model.addAttribute("goodsPics", ydMemberCtc.get("ctc_pics").toString().split(","));
		return "shop/ctc/ctcDetail";
	}

	// 查看单个商品的留言列表及详情
	@RequestMapping(value = "/ctcMsg/{ctc_id}", method = RequestMethod.GET)
	public Object queryOneCtcMsgDetailById(@PathVariable("ctc_id") Integer ctc_id, Model model, Integer p) {
		HashMap<String, Object> ydMemberCtc = ydMemberServiceCtc.queryOneCtcDetailById(ctc_id);
		// 判断是否已收藏
		boolean is_favorite = ydShopServiceFavorite.isFavorite(ctc_id, 4);
		model.addAttribute("item", ydMemberCtc);
		model.addAttribute("is_favorite", is_favorite);
		model.addAttribute("goodsPics", ydMemberCtc.get("ctc_pics").toString().split(","));
		// 查询所有的留言
		if (p == null || p == 0) {
			p = 1;
		}
		Integer to = null;
		List<HashMap<String, Object>> totals = ydMemberServiceCtcInMsg.oneCtcMsg(ctc_id, to);
		to = (p - 1) * 10;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//查询ctc买家留言
		List<HashMap<String, Object>> list = ydMemberServiceCtcInMsg.oneCtcMsg(ctc_id, to);
		
		for (HashMap<String, Object> hashMap : list) {
			String time = (String) hashMap.get("create_date");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sdTime = sdf.format(new Date(Long.parseLong(time)));
			hashMap.put("create_date",sdTime);
		}
		
		//查询当前ctc回复留言(方法不对需要修改)
		List<HashMap<String,Object>> recevieList = ydMemberServiceCtcInMsg.querySellerMessage(ctc_id);
		System.out.println(recevieList.size());
		model.addAttribute("list", list);
		map.put("ctcMsgs", list);
		map.put("p", p);
		map.put("to", to);
		model.addAttribute("data", map);
		if (totals.size() % 10 == 0) {
			map.put("totalPage", Math.ceil(totals.size() / 10));
		} else {
			map.put("totalPage", Math.ceil(totals.size() / 10) + 1);
		}

		return "shop/ctc/ctcMsg";
	}

	/**
	 * 跳转订单确认页
	 * 
	 * @param ctc_id寄卖商品的id
	 * @param model
	 * @return 方法作用()
	 */
	@RequestMapping(value = "/ctc_order/{ctc_id}", method = RequestMethod.GET)
	public Object toCtcOderConfirm(@PathVariable("ctc_id") Integer ctc_id, Model model) {
		// if (session.getAttribute("user_id") == null) {
		// return YdUtilResponse.fail("请登录再购买");
		// }
		// 获取寄卖商品信息
		HashMap<String, Object> ydMemberCtc = ydMemberServiceCtc.queryOneCtcDetailById(ctc_id);
		model.addAttribute("item", ydMemberCtc);
		return "shop/pay/ctc_order_confirm";
	}

	// 卖家的懒鱼中心
	/**
	 * @param uid
	 *            用户id
	 * @param model
	 * @return 方法作用()
	 */
	@RequestMapping(value = "/sellHome/{uid}", method = RequestMethod.GET)
	public Object queryOneByUid(@PathVariable("uid") Integer uid, Model model, Integer p) {
		// 查询用户详情
		Integer type = 0;
		User user = ydMemberServiceUser.queryDetailByUid(uid);
		// 查询卖出的懒鱼评论
		// List<YdCtcComment> list =
		// ydShopServiceCtcComment.queryAllCtcCommentBySellerId(uid);
		Map<String, Object> map = fenye(p, type, uid);
		type = 0;
		// 所有评价数
		Integer counts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 好评数
		type = 1;
		Integer goodcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 中评数
		type = 2;
		Integer midcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 差评数
		type = 3;
		Integer lowcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		model.addAttribute("counts", counts);
		model.addAttribute("goodcounts", goodcounts);
		model.addAttribute("midcounts", midcounts);
		model.addAttribute("lowcounts", lowcounts);
		model.addAttribute("ctc_comments", map);
		model.addAttribute("user", user);
		model.addAttribute("uid", uid);
		return "shop/ctc/home";
	}

	// 分类查询所有的好评
	@RequestMapping(value = "/sellHome1/{uid}", method = RequestMethod.GET)
	public Object queryOneDetailByUid1(@PathVariable("uid") Integer uid, Model model, Integer p) {
		// 查询用户详情
		User user = ydMemberServiceUser.queryDetailByUid(uid);
		Integer type = 1;
		// 查询卖出的懒鱼评论
		// List<YdCtcComment> list =
		// ydShopServiceCtcComment.queryAllCtcCommentsBySellerId(uid, type);
		Map<String, Object> map = fenye(p, type, uid);
		// 所有评价数
		type = 0;
		Integer counts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 好评数
		type = 1;
		Integer goodcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 中评数
		type = 2;
		Integer midcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 差评数
		type = 3;
		Integer lowcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		model.addAttribute("counts", counts);
		model.addAttribute("goodcounts", goodcounts);
		model.addAttribute("midcounts", midcounts);
		model.addAttribute("lowcounts", lowcounts);
		// model.addAttribute("ctc_comments", list);
		model.addAttribute("ctc_comments", map);
		model.addAttribute("uid", uid);
		model.addAttribute("user", user);
		return "shop/ctc/home";
	}

	// 分类查询所有的中评
	@RequestMapping(value = "/sellHome2/{uid}", method = RequestMethod.GET)
	public Object queryOneDetailByUid2(@PathVariable("uid") Integer uid, Model model, Integer p) {
		// 查询用户详情
		User user = ydMemberServiceUser.queryDetailByUid(uid);
		Integer type = 2;
		// 查询卖出的懒鱼评论
		// List<YdCtcComment> list =
		// ydShopServiceCtcComment.queryAllCtcCommentsBySellerId(uid, type);
		Map<String, Object> map = fenye(p, type, uid);
		// 所有评价数
		type = 0;
		Integer counts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 好评数
		type = 1;
		Integer goodcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 中评数
		type = 2;
		Integer midcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 差评数
		type = 3;
		Integer lowcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		model.addAttribute("counts", counts);
		model.addAttribute("goodcounts", goodcounts);
		model.addAttribute("midcounts", midcounts);
		model.addAttribute("lowcounts", lowcounts);
		// model.addAttribute("ctc_comments", list);
		model.addAttribute("ctc_comments", map);
		model.addAttribute("user", user);
		model.addAttribute("uid", uid);
		return "shop/ctc/home";
	}

	// 分类查询所有的差评
	@RequestMapping(value = "/sellHome3/{uid}", method = RequestMethod.GET)
	public Object queryOneDetailByUid3(@PathVariable("uid") Integer uid, Model model, Integer p) {
		// 查询用户详情
		User user = ydMemberServiceUser.queryDetailByUid(uid);
		Integer type = 3;
		// 查询卖出的懒鱼评论
		// List<YdCtcComment> list =
		// ydShopServiceCtcComment.queryAllCtcCommentsBySellerId(uid, type);
		Map<String, Object> map = fenye(p, type, uid);

		// 所有评价数
		type = 0;
		Integer counts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 好评数
		type = 1;
		Integer goodcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 中评数
		type = 2;
		Integer midcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		// 差评数
		type = 3;
		Integer lowcounts = ydShopServiceCtcComment.goodCounts(uid, type);
		model.addAttribute("counts", counts);
		model.addAttribute("goodcounts", goodcounts);
		model.addAttribute("midcounts", midcounts);
		model.addAttribute("lowcounts", lowcounts);
		model.addAttribute("ctc_comments", map);
		model.addAttribute("user", user);
		model.addAttribute("uid", uid);
		return "shop/ctc/home";
	}

	// 懒鱼评价分页
	public Map<String, Object> fenye(Integer p, Integer type, Integer uid) {
		if (p == null || p == 0) {
			p = 1;
		}
		Integer to = (p - 1) * 10;
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer counts = ydShopServiceCtcComment.goodCounts(uid, type);
		List<YdCtcComment> list = ydShopServiceCtcComment.queryAllCtcCommentsBySellerId(uid, type, to);
		for (YdCtcComment ydCtcComment : list) {
			String ctc_comment_created_time = ydCtcComment.getCtc_comment_created_time();
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(Long.parseLong(ctc_comment_created_time));
			c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ydCtcComment.setTime(sdf.format(c.getTime()));

		}
		// if (list != null && !list.isEmpty()) {
		// map.put("total", list.size());
		// } else {
		// map.put("total", 0);
		// }
		map.put("ctcs", list);

		map.put("p", p);
		map.put("to", to);

		if (counts % 10 == 0) {
			map.put("totalPage", Math.ceil(counts / 10));
		} else {
			map.put("totalPage", Math.ceil(counts / 10) + 1);
		}

		return map;
	}

	// 懒鱼评价入口
	@RequestMapping(value = "/addOrderAssess/{ctc_order_id}", method = RequestMethod.GET)
	public Object addOrderAssess(@PathVariable("ctc_order_id") Integer ctc_order_id, Model model) {
		// 查询ctc订单信息
		YdCtcOrder ydCtcOrder = ydMemberServiceCtcOrder.queryOneCtcOrderDetailById(ctc_order_id);
		Integer ctc_id = ydCtcOrder.getCtc_id();
		HashMap<String, Object> ydMemberCtc = ydMemberServiceCtc.queryOneCtcDetailById(ctc_id);
		model.addAttribute("ydMemberCtc", ydMemberCtc);
		model.addAttribute("ydCtcOrder", ydCtcOrder);
		return "/member/temp/order/ctcOrderAssess";
	}

	// 发表评论
	@RequestMapping(value = "addOrderAssess1")
	@ResponseBody
	public Object addOrderAssess(String data, Integer ctc_order_id, Integer single) {
		return ydShopServiceCtcComment.addComment(data, ctc_order_id, single);
	}
}
