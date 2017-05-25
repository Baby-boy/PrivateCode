package com.yd.dby.app.controller.cart;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.controller.BaseController;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.CartPara;
import com.yd.dby.app.service.YdCartService;

/**
 * 说明: 购物车
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月9日 下午2:16:34
 * @Version:1.0
 */
@RestController
@RequestMapping("cart")
public class CartController extends BaseController {

	@Autowired
	private YdCartService ydCartService;

	/**
	 * 说明: 添加商品到购物车
	 * 
	 * @param para(请求参数)
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月9日 下午2:33:54
	 */
	@RequestMapping(value = "addGoods", method = RequestMethod.POST)
	public JsonResult addGoods(@RequestBody CartPara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Object> result = ydCartService.insertGoodsToCart(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 查询用户购物车列表
	 * 
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月9日 下午4:27:49
	 */
	@RequestMapping(value = "cartPageList", method = RequestMethod.POST)
	public JsonResult queryCartPageList(HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Object> result = ydCartService.selectCartPageList(user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 编辑购物车购买数量
	 * 
	 * @param para(请求参数)
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 上午10:48:56
	 */
	@RequestMapping(value = "updateGoodsNum", method = RequestMethod.POST)
	public JsonResult updateCartGoodsNum(@RequestBody Map<String, List<CartPara>> paraMap) {
		JsonResult jsonResult = new JsonResult();

		// 判断请求参数是否正确
		if (null == paraMap || paraMap.size() < 1) {
			jsonResult.setStatusCode(HttpCode.NO_CONTENT);
			jsonResult.setMsg("未编辑商品!");
			return jsonResult;
		}

		ServiceResult<Object> result = ydCartService.updateCartGoodsNum(paraMap.get("cartIds"));
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 删除购物中商品
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午12:42:32
	 */
	@RequestMapping(value = "deleteCartGoods", method = RequestMethod.POST)
	public JsonResult deleteCartGoods(@RequestBody CartPara para) {
		JsonResult jsonResult = new JsonResult();

		// 判断请求参数是否正确
		if (StringUtils.isEmpty(para.getCartIds())) {
			jsonResult.setStatusCode(HttpCode.NO_CONTENT);
			jsonResult.setMsg("未删除商品!");
			return jsonResult;
		}

		ServiceResult<Object> result = ydCartService.deleteCartGoods(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}
}
