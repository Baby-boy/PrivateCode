package com.yd.dby.b.shop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.dby.b.shop.entity.YdAppOnlineBooking;
import com.yd.dby.b.shop.entity.YdArticle;
import com.yd.dby.b.shop.mapper.YdShopMapperArticle;
import com.yd.dby.b.shop.service.YdShopServiceComment;
import com.yd.dby.b.shop.service.YdShopServiceCtc;
import com.yd.dby.b.shop.service.YdShopServiceCtcClass;
import com.yd.dby.b.shop.service.YdShopServiceGoods;
import com.yd.dby.b.shop.service.YdShopServiceOnlinebooking;
import com.yd.dby.b.shop.service.YdShopServiceStore;
import com.yd.dby.b.shop.service.impl.YdShopServiceImplArticle;

@Controller
@RequestMapping(value = "")
public class YdShopControllerIndex {
	@Autowired
	private YdShopServiceOnlinebooking ydShopServiceOnlinebooking;
	@Autowired
	private YdShopServiceCtc ydShopServiceCtc;

	@Autowired
	private YdShopServiceCtcClass ydShopServiceCtcClass;

	@Autowired
	private YdShopServiceStore ydShopServiceStore;

	@Autowired
	private YdShopServiceComment ydShopServiceComment;

	@Autowired
	HttpSession session;

	@Autowired
	private HttpServletRequest ydRequest;

	@Autowired
	private YdShopServiceImplArticle ydShopServiceImplArticle;

	@Autowired
	private YdShopServiceGoods ydShopServiceGoods;

	@Autowired
	private YdShopMapperArticle ydShopMapperArticle;

	// 首页
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object index() {
		return "shop/index/home";
	}

	// 流通
	@RequestMapping(value = "circulation", method = RequestMethod.GET)
	public Object circulation(Model model) {
		// model.addAttribute("dataStoreList",ydShopServiceOnlinebooking.get_Store());
		List<Object> footer = ydShopMapperArticle.footer1();
		model.addAttribute("footer", footer);
		return "shop/index/circulation";
	}

	// 流通
	@RequestMapping(value = "replaced_map", method = RequestMethod.GET)
	public Object replaced_map(Model model) {
		return "shop/index/replaced_map";
	}

	// 置换
	@RequestMapping(value = "replacement", method = RequestMethod.GET)
	public Object substitution(Model model) {
		List<Object> footer = ydShopMapperArticle.footer1();
		model.addAttribute("footer", footer);
		return "shop/index/replacement";
	}

	// 品牌店
	@RequestMapping(value = "brandstore", method = RequestMethod.GET)
	public Object brand(Model model, Integer p, Integer brand_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("p", p);
		map.put("brand_id", brand_id);
		model.addAttribute("dataStoreList", ydShopServiceStore.list(map));
		return "shop/index/brandstore";
	}// 养护

	@RequestMapping(value = "maintenance", method = RequestMethod.GET)
	public Object maintenance() {
		return "shop/index/maintenance";
	}

	// 懒鱼
	@RequestMapping(value = "c2c", method = RequestMethod.GET)
	public Object c2c(Model model) {

		model.addAttribute("data", ydShopServiceCtcClass.listTreeGoods());
		return "shop/index/ctc";
	}

	// 拍卖
	@RequestMapping(value = "auction", method = RequestMethod.GET)
	public Object auction() {
		return "shop/index/auction";
	}

	// 团购
	@RequestMapping(value = "groupon", method = RequestMethod.GET)
	public Object groupon() {
		return "shop/index/groupon";
	}

	// 供货商城
	@RequestMapping(value = "supply", method = RequestMethod.GET)
	public Object supply() {
		return "shop/index/supply";
	}

	// 信息商城
	@RequestMapping(value = "information", method = RequestMethod.GET)
	public Object information() {
		return "shop/index/information";
	}

	// 流通预约
	@RequestMapping(value = "/circulation/appointment/{id}", method = RequestMethod.GET)
	public Object c_appointment(@PathVariable("id") Integer id) {
		return "shop/circulation/online_booking";
	}

	@RequestMapping(value = "/circulation/appointment", method = RequestMethod.POST)
	@ResponseBody
	public Object appointment(YdAppOnlineBooking ydAppOnlineBooking) {
		return ydShopServiceOnlinebooking.add(ydAppOnlineBooking);
	}

	// 置换预约
	@RequestMapping(value = "/substitution/appointment/{id}", method = RequestMethod.GET)
	public Object s_appointment(@PathVariable("id") Integer id) {
		return "shop/circulation/online_booking";
	}

	// 品牌店--店铺首页
	// @RequestMapping(value = "/brand/store/{id}", method = RequestMethod.GET)
	// public Object brand_store(@PathVariable("id") Integer id) {
	// return "shop/brand/store";
	// }

	// 懒鱼--发布
	@RequestMapping(value = "/c2c/release", method = RequestMethod.GET)
	public Object c2c_release(HttpServletResponse response) throws IOException {
		if (session.getAttribute("user_id") == null) {
			response.sendRedirect("/login");
			return null;
		}
		return "shop/ctc/release";
	}

	// 懒鱼--个人首页
	@RequestMapping(value = "/c2c/home/{id}", method = RequestMethod.GET)
	public Object ctc_home(@PathVariable("id") Integer id) {
		return "shop/c2c/home";
	}

	// @RequestMapping(value = "/api/shop/c2c")
	// public ModelAndView c2c_service(@RequestBody Map<String, HashMap<String,
	// Object>> request) {
	// return YdWeb.MAV("_YdShopServiceCtc", "Release", request);
	// }

	// 拍卖保证金缴纳
	@RequestMapping(value = "/auction/bond/{id}", method = RequestMethod.GET)
	public Object bond(@PathVariable("id") Integer id) {
		return "shop/auction/bond";
	}

	// 公告列表
	@RequestMapping(value = "/news")
	public Object news(Model model, Integer p, YdArticle ydArticle) {
		// 公告
		ydArticle.setAc_id(9);
		model.addAttribute("dataNotice", ydShopServiceImplArticle.lists(ydArticle, p));
		// 促销
		ydArticle.setAc_id(10);
		model.addAttribute("dataSales", ydShopServiceImplArticle.lists(ydArticle, p));
		model.addAttribute("np", 1);
		model.addAttribute("sp", 1);
		return "shop/news/index";
	}

	// 公告列表 - 分页搜索公告
	@RequestMapping(value = "/news/notice")
	public Object newsNotice(Model model, Integer p, YdArticle ydArticle) {
		model.addAttribute("article_title_n", ydArticle.getArticle_title());
		model.addAttribute("article_title_s", "");
		if (p == null) {
			p = 1;
		}
		// 公告
		ydArticle.setAc_id(9);
		model.addAttribute("dataNotice", ydShopServiceImplArticle.lists(ydArticle, p));
		// 促销
		ydArticle.setArticle_title(null);
		ydArticle.setAc_id(10);
		model.addAttribute("dataSales", ydShopServiceImplArticle.lists(ydArticle, 1));
		model.addAttribute("np", p);
		model.addAttribute("sp", 1);
		return "shop/news/index";
	}

	// 公告列表 - 分页搜索活动
	@RequestMapping(value = "/news/sales")
	public Object newsSales(Model model, Integer p, YdArticle ydArticle) {
		model.addAttribute("article_title_n", "");
		model.addAttribute("article_title_s", ydArticle.getArticle_title());
		if (p == null) {
			p = 1;
		}
		// 促销
		ydArticle.setAc_id(10);
		model.addAttribute("dataSales", ydShopServiceImplArticle.lists(ydArticle, p));
		// 公告
		ydArticle.setArticle_title(null);
		ydArticle.setAc_id(9);
		model.addAttribute("dataNotice", ydShopServiceImplArticle.lists(ydArticle, 1));
		model.addAttribute("np", 1);
		model.addAttribute("sp", p);
		return "shop/news/index";
	}

	// 公告详情
	@RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
	public Object news_details(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("data", ydShopServiceImplArticle.info(id));
		return "shop/details/news";
	}

	// 许愿池
	@RequestMapping(value = "/member/wish", method = RequestMethod.GET)
	public Object wish() {
		System.out.println(123);
		return "shop/index/wish";
	}

	// 登录
	// @RequestMapping(value = "/login", method = RequestMethod.GET)
	// public Object login() {
	// return "/shop/login/login";
	// }

	// 注册
	// @RequestMapping(value = "/register", method = RequestMethod.GET)
	// public Object register() {
	// return "/shop/login/register";
	// }

	// 企业注册
	// @RequestMapping(value = "/enterprise", method = RequestMethod.GET)
	// public Object enterprise() {
	// return "/shop/login/enterprise";
	// }
	//
	// // 信息商城详情页
	// @RequestMapping(value = "/information/{id}", method = RequestMethod.GET)
	// public Object details(@PathVariable("id") Integer id) {
	// return "/shop/information/details";
	// }
	//
	// @RequestMapping(value = "/api/shop/information/")
	// public ModelAndView service(@RequestBody Map<String, HashMap<String,
	// Object>> request) {
	// return YdWeb.MAV("_ydShopServiceInformation", "page", request);
	// }
	//
	// @RequestMapping(value = "/api/shop/information/message")
	// public ModelAndView page(@RequestBody Map<String, HashMap<String,
	// Object>> request) {
	// return YdWeb.MAV("_ydShopServiceInformation", "messagePage", request);
	// }
	//
	// @RequestMapping(value = "/api/shop/information/mesreply")
	// public ModelAndView msgreply(@RequestBody Map<String, HashMap<String,
	// Object>> request) {
	// return YdWeb.MAV("_ydShopServiceInformation", "msgReply", request);
	// }
	//
	// @RequestMapping(value = "/api/shop/information/reply")
	// public ModelAndView reply(@RequestBody Map<String, HashMap<String,
	// Object>> request) {
	// return YdWeb.MAV("_ydShopServiceInformation", "Reply", request);
	// }

	// @ResponseBody
	// @RequestMapping(value = "/classifytest")
	// public Object classifytest() {
	// return YdUtilTree.toList(ydShopMapperClassify.get_many("goods"));
	// }
	//
	// @ResponseBody
	// @RequestMapping(value = "/floortest2")
	// public Object floortest2() {
	// return YdUtilTree.toList(ydShopMapperAds.get_many("c2c_group"));
	// }
	//
	// @ResponseBody
	// @RequestMapping(value = "/floortest")
	// public Object floortest() {
	// return YdUtilTree.toList(ydShopMapperAds.get_many("auction_floor"));
	// }
	//
	// @Autowired
	// private YdShopServiceStore ydServiceStore;
	//
	// @ResponseBody
	// @RequestMapping(value = "/store")
	// public Object store() {
	// return ydServiceStore.recommend_goods(1);
	// }
	//
	// @Autowired
	// private YdShopServiceGoods ydServiceGoods;
	//
	// @Autowired
	// private YdShopServiceComment ydServiceComment;
	//
	// @ResponseBody
	// @RequestMapping(value = "/goods_auction", method = RequestMethod.GET)
	// public HashMap<String, Object> get_only_groupon() {
	// return ydServiceGoods.get_only_auction(1, 1);
	// }
	//
	// @ResponseBody
	// @RequestMapping(value = "/goods_seckill", method = RequestMethod.GET)
	// public HashMap<String, Object> get_only_seckill() {
	// return ydServiceGoods.get_only_seckill(1, 1);
	// }
	//

	//
	// @RequestMapping(value = "/upload", method = RequestMethod.GET)
	// public String upload() {
	// return "upload";
	// }
	//
	// @Autowired
	// YdShopServiceShoppingcart ydShopServiceShoppingcart;
	//
	// @RequestMapping(value = "/ck3", method = RequestMethod.GET)
	// public String ck3() {
	// return "ck3";
	// }
	//
	// 购物车
	// @RequestMapping(value = "/cart", method = RequestMethod.GET)
	// public Object cart() {
	// return "/shop/cart/index";
	// }
	//
	// 添加购物车
	// @RequestMapping(value = "/api/cart/update")
	// public ModelAndView add_cart(@RequestBody Map<String, HashMap<String,
	// Object>> request) {
	// return YdMain.MAV("_Cart", "update", request);
	// }

	// 删除购物车商品
	// @RequestMapping(value = "/api/cart/delete")
	// public ModelAndView delete_cart(@RequestBody Map<String, HashMap<String,
	// Object>> request) {
	// return YdMain.MAV("_Cart", "delete", request);
	// }
	//
	// // 核对订单
	// @RequestMapping(value = "/settlement/{id}", method = RequestMethod.GET)
	// public Object settlement(@PathVariable("id") Integer id) {
	// return "/shop/pay/order_confirm";
	// }
	//
	// // 支付订单
	// @RequestMapping(value = "/pay", method = RequestMethod.GET)
	// public Object pay() {
	// return "/shop/cart/pay";
	// }
	//
	// @Autowired
	// YdShopServiceDict ydShopServiceDict;
	//
	// @RequestMapping(value = "/dict", method = RequestMethod.GET)
	// @ResponseBody
	// public Object dict() {
	// return ydShopServiceDict.list("maintenanc");
	// }
	//
	// @RequestMapping(value = "/goods_create", method = RequestMethod.GET)
	// public String goods_create() {
	// return "goods_create";
	// }
	//
	// @Autowired
	// YdShopServiceData ydShopServiceData;
	// @Autowired
	// YdShopMapperData ydShopMapperData;
	//
	// @RequestMapping(value = "/test2/data/{type}", method = RequestMethod.GET)
	// @ResponseBody
	// public Object datatest2(@PathVariable("type") String type) {
	// return ydShopMapperData.get_many(type);
	// }
	//
	// @RequestMapping(value = "/test/data/{type}", method = RequestMethod.GET)
	// @ResponseBody
	// public Object datatest(@PathVariable("type") String type) {
	// return ydShopServiceData.get(type);
	// }
	//
	// @Autowired
	// YdShopServiceComment ydShopServiceComment;
	// @Autowired
	// YdShopServiceGoods ydShopServiceGoods;
	//
	// @RequestMapping(value = "/comment/get_count", method = RequestMethod.GET)
	// @ResponseBody
	// public Object comment_get_count() {
	// return ydShopServiceComment.get_count(1, 1);
	// }
	//
	// @RequestMapping(value = "/comment/get_list_only_img", method =
	// RequestMethod.GET)
	// @ResponseBody
	// public Object comment_get_list_only_img() {
	// return ydShopServiceComment.get_list_only_img(1);
	// }
	//
	// @RequestMapping(value = "/comment/get_list", method = RequestMethod.GET)
	// @ResponseBody
	// public Object get_list() {
	// return ydShopServiceComment.get_list(1, 1, 1);
	// }
	//
	// @RequestMapping(value = "/goods/goods", method = RequestMethod.GET)
	// @ResponseBody
	// public Object goodsgoods() {
	// return ydShopServiceGoods.get_only_normal(1, 1);
	// }
	//
	// @Autowired
	// YdShopServiceAuction ydShopServiceAuction;
	//
	// @RequestMapping(value = "/Store/Store", method = RequestMethod.GET)
	// @ResponseBody
	// public Object StoreStore() {
	// return ydShopServiceAuction.check_is_bail(null);
	// }
	//
	// @RequestMapping(value = "/seckill/seckill", method = RequestMethod.GET)
	// @ResponseBody
	// public Object StoreStore1() {
	// return ydShopServiceGoods.get_only_seckill(1, 1);
	// }
	//
	// // 供货商城失败测试
	// @RequestMapping(value = "/ghsc/post1")
	// public ModelAndView ghsc_post1(@RequestBody Map<String, HashMap<String,
	// Object>> request) {
	// return YdWeb.MAV("ghsc", "post1", request);
	// }
	//
	// // 供货商城成功测试
	// @RequestMapping(value = "/ghsc/post2")
	// public ModelAndView ghsc_post2(@RequestBody Map<String, HashMap<String,
	// Object>> request) {
	// return YdWeb.MAV("ghsc", "post2", request);
	// }

	// @RequestMapping(value = "/{type}/{id}", method = RequestMethod.GET)
	// public ModelAndView get_details_maintenance(@PathVariable("type") String
	// type, @PathVariable("id") Integer id) {
	// Integer user_id = 1;
	// ModelAndView mav;
	// HashMap<String, Object> output = new HashMap<String, Object>();
	//
	// switch (type) {
	// case "goods":
	// output.put("comment", ydServiceComment.get_many(1, 1));
	// output.put("goods", ydServiceGoods.get_only_normal(user_id, id));
	// // mav = new ModelAndView("/normal/details");
	// mav = new ModelAndView(new MappingJackson2JsonView());
	// mav.addAllObjects(output);
	// return mav;
	// case "maintenance":
	// output.put("comment", ydServiceComment.get_many(1, 1));
	// output.put("goods", ydServiceGoods.get_only_maintenance(user_id, id));
	// mav = new ModelAndView("/maintenance/details");
	// mav.addAllObjects(output);
	// return mav;
	// case "auction":
	// output.put("comment", ydServiceComment.get_many(1, 1));
	// output.put("goods", ydServiceGoods.get_only_auction(user_id, id));
	// mav = new ModelAndView("/auction/details");
	// mav.addAllObjects(output);
	// return mav;
	// case "groupon":
	// output.put("comment", ydServiceComment.get_many(1, 1));
	// output.put("goods", ydServiceGoods.get_only_groupon(user_id, id));
	// mav = new ModelAndView("/groupon/details");
	// mav.addAllObjects(output);
	// return mav;
	// case "seckill":
	// output.put("comment", ydServiceComment.get_many(1, 1));
	// output.put("goods", ydServiceGoods.get_only_seckill(user_id, id));
	// mav = new ModelAndView("/seckill/details");
	// mav.addAllObjects(output);
	// return mav;
	// default:
	// mav = new ModelAndView("/error/404");
	// return mav;
	// }
	// }
	@RequestMapping(value = "/g/{type}/{id}", method = RequestMethod.GET)
	public String get_details_maintenance(Model model, @PathVariable("type") String type, @PathVariable("id") Integer id) {
		switch (type) {
		case "goods":
			// HashMap<String, Object> dataMap =
			// ydShopServiceGoods.get_only_normal((Integer)session.getAttribute("user_id"),
			// id);
			Integer goodsId = id;
			Integer totalPics = ydShopServiceComment.total("1", goodsId);
			Integer total1 = ydShopServiceComment.total("2", goodsId);
			Integer total2 = ydShopServiceComment.total("3", goodsId);
			Integer total3 = ydShopServiceComment.total("4", goodsId);
			Integer total = ydShopServiceComment.total(null, goodsId);

			Integer commentNum1 = total1 * total;

			model.addAttribute("totalPics", totalPics);
			model.addAttribute("total", total);
			model.addAttribute("total1", total1);
			model.addAttribute("total2", total2);
			model.addAttribute("total3", total3);
			return "shop/details/goods";
		case "maintenance":
			return "shop/details/maintenance";
		case "auction":
			return "shop/details/auction";
		case "groupon":
			return "shop/details/groupon";
		case "seckill":
			return "shop/details/seckill";
		case "c2c":
			return "shop/details/c2c";
		default:
			return "shop/error/404";
		}
	}
}
