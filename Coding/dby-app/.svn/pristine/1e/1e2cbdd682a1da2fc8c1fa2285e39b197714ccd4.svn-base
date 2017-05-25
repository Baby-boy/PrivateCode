package com.yd.dby.app.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pingplusplus.model.Charge;
import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.controller.BaseController;
import com.yd.dby.app.entity.YdComment;
import com.yd.dby.app.entity.YdOrderWithBLOBs;
import com.yd.dby.app.entity.YdRefundReason;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.CouponPara;
import com.yd.dby.app.entity.para.OrderAddMsgJsonPara;
import com.yd.dby.app.entity.para.OrderAddMsgPara;
import com.yd.dby.app.entity.para.OrderPara;
import com.yd.dby.app.entity.para.OrderRefundPara;
import com.yd.dby.app.entity.para.PayPara;
import com.yd.dby.app.entity.vo.OrderDetailsVo;
import com.yd.dby.app.entity.vo.OrderListVo;
import com.yd.dby.app.entity.vo.UserCoupon;
import com.yd.dby.app.service.YdCommentService;
import com.yd.dby.app.service.YdCouponService;
import com.yd.dby.app.service.YdOrderService;

import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping(value = "/order", produces = { "application/json;charset=UTF-8" })
public class OrderController extends BaseController {

	@Autowired
	private YdOrderService ydOrderService;

	@Autowired
	private YdCommentService ydCommentService;

	@Autowired
	private YdCouponService ydCouponService;

	/**
	 * 说明: 订单列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月10日 上午11:50:33
	 */
	@RequestMapping(value = "/orderList", method = RequestMethod.POST)
	public JsonResult queryOrderList(@RequestBody OrderPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		try {
			// 获取当前用户
			YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

			// 查询订单
			para.setUserId(user.getUserId());
			ServiceResult<Page<OrderListVo>> result = ydOrderService.selectOrderPageList(para);

			// 返回信息
			jsonResult.setStatusCode(result.getStatusCode());
			jsonResult.setMsg(result.getMsg());
			jsonResult.setData(result.getData());
		} catch (Exception e) {
			// 未知异常
			jsonResult.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("系统繁忙");
			return jsonResult;
		}

		return jsonResult;
	}

	/**
	 * 说明: 订单详情
	 * 
	 * @param ogId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月10日 上午10:25:38
	 */
	@RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
	public JsonResult queryOrderDetails(@RequestParam(value = "ogId", required = true) Integer ogId) {
		JsonResult jsonResult = new JsonResult();

		// 查询订单
		ServiceResult<OrderDetailsVo> result = ydOrderService.selectOrderDetails(ogId);

		// 返回信息
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明: 取消订单
	 * 
	 * @param orderId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月1日 下午5:20:25
	 */
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public JsonResult updateCancelOrder(@RequestParam(value = "orderId", required = true) Integer orderId) {
		JsonResult jsonResult = new JsonResult();

		YdOrderWithBLOBs order = new YdOrderWithBLOBs();
		order.setOrderId(orderId);
		order.setState((byte) 9);
		ServiceResult<Object> result = ydOrderService.updateOrderMsg(order);

		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明: 添加订单评论(评价晒单)
	 * 
	 * @param para
	 * @param request
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月12日 下午2:52:42
	 */
	@RequestMapping(value = "/commentOrder", method = RequestMethod.POST)
	public JsonResult addCommentOrder(@RequestBody YdComment para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		// 参数信息过滤
		if (para.getOrderId() == null || para.getOrderId() < 1) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数信息错误");
			return jsonResult;
		}
		if (para.getGoodsId() == null || para.getGoodsId() < 1) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数信息错误");
			return jsonResult;
		}
		if (para.getCommentScore() < 1 || para.getCommentScore() > 5) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数信息错误");
			return jsonResult;
		}

		try {
			// 获取用户信息
			YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

			para.setUserId(user.getUserId());
			para.setCommentTotalLike(0);
			para.setCommentTotalReply(0);
			para.setCommentCreatedTime(String.valueOf(new Date().getTime()));

			// 添加评论
			ServiceResult<YdComment> result = ydCommentService.insertCommentOrder(para);
			jsonResult.setStatusCode(result.getStatusCode());
			jsonResult.setMsg(result.getMsg());
		} catch (Exception e) {
			// 未知异常
			jsonResult.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("系统繁忙");
			return jsonResult;
		}

		return jsonResult;
	}

	/**
	 * 说明: 购物车结算时 可用的优惠券
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月15日 下午5:25:31
	 */
	@RequestMapping(value = "/couponList", method = RequestMethod.POST)
	public JsonResult queryCouponList(@RequestBody CouponPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		try {
			// 获取当前用户
			YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

			// 查询可用优惠券
			para.setUserId(user.getUserId());
			ServiceResult<List<UserCoupon>> result = ydCouponService.selectCouponList(para);

			// 返回信息
			jsonResult.setStatusCode(result.getStatusCode());
			jsonResult.setData(result.getData());
			jsonResult.setDataMap(result.getDataMap());
		} catch (Exception e) {
			// 未知异常
			jsonResult.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("系统繁忙");
			return jsonResult;
		}

		return jsonResult;
	}

	/**
	 * 说明: 订单结算，生成order跟order_goods订单信息(只是生成订单，未支付)
	 * 
	 * @param para
	 * @param request
	 * @return
	 * @throws Exception
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月16日 下午2:47:47
	 */
	@RequestMapping(value = "/addOrderMsg", method = RequestMethod.POST)
	public JsonResult addOrderMsg(@RequestBody OrderAddMsgJsonPara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 参数过滤
		if (StringUtils.isEmpty(para.getOrderJson())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数格式有误");
			return jsonResult;
		}

		// // 这里的注释不要删除，因为测试参数不好拼接
		// String orderJson =
		// "{'paymentCode':'weipay','invoiceType':2,'invoiceNo':'上海云洞科技有限公司','receiptName':'小磊哥','receiptMobile':'185165.....','receiptAddress':'上海美兰湖底','integral':200,'shippingExpress':'顺丰快递',"
		// + "'orderStore':["
		// +
		// "{'storeId':1,'couponId':5,'couponPrice':20,'orderMessage':'我要个大红色的，喜庆','transportFid':0,'transportAddress':'宝山区大柏树',"
		// + "'goods':[" +
		// "{'goodsId':193,'goodsNum':2,'goodsPrice':805,'goodsFreight':2},"
		// + "{'goodsId':195,'goodsNum':1,'goodsPrice':198,'goodsFreight':9.9},"
		// + "{'goodsId':197,'goodsNum':1,'goodsPrice':198,'goodsFreight':9.9}"
		// + "]" + "},"
		// +
		// "{'storeId':2,'couponId':2,'couponPrice':4,'orderMessage':'我要个绿色的，尤其是帽子','transportFid':0,'transportAddress':'宝山区长江南路',"
		// +
		// "'goods':[{'goodsId':194,'goodsNum':2,'goodsPrice':4380,'goodsFreight':99}]"
		// + "}" + "]" + "}";

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		OrderAddMsgPara payMsg = new OrderAddMsgPara();
		try {
			// 数据转换
			payMsg = new Gson().fromJson(para.getOrderJson(), OrderAddMsgPara.class);
		} catch (Exception e) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数格式有误");
			return jsonResult;
		}

		// 生成订单
		payMsg.setUserId(user.getUserId());
		payMsg.setUserUsername(user.getUserUsername());
		ServiceResult<Object> result = ydOrderService.insertOrderMsg(payMsg);

		// 返回信息
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明: 待付款状态进行支付(只是生成订单，未支付)
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月23日 下午2:46:09
	 */
	@RequestMapping(value = "/addOrderPay", method = RequestMethod.POST)
	public JsonResult addOrderPay(@RequestBody PayPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		// 创建ping++订单
		ServiceResult<Object> result = ydOrderService.insertOrderPay(para);

		// 返回信息
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		return jsonResult;

	}

	/**
	 * 说明: 支付成功调用
	 * 
	 * @param charge
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月23日 下午7:01:06
	 */
	@RequestMapping(value = "/successOrderPay", method = RequestMethod.POST)
	public JsonResult successOrderPay(@RequestBody Charge charge) {
		JsonResult jsonResult = new JsonResult();

		try {
			// 从redis里获取数据
			Map map = (Map) redisGet(charge.getOrderNo(), Map.class);
			if (map.get("order") == null) {
				jsonResult.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
				jsonResult.setMsg("系统繁忙");
				return jsonResult;
			}

			// String listStr = map.get("order").toString();

			// System.out.println(listStr);

			List<Object> list = new Gson().fromJson(map.get("order").toString(), List.class);

			// 返回信息
			jsonResult.setStatusCode(HttpCode.OK);
			jsonResult.setData(list);
		} catch (Exception e) {
			// 未知异常
			jsonResult.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("系统繁忙");
			return jsonResult;
		}

		return jsonResult;
	}

	/**
	 * 说明: 确认收货
	 * 
	 * @param para
	 * @param request
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月23日 下午5:55:00
	 */
	@RequestMapping(value = "/confirmOrderMsg", method = RequestMethod.POST)
	public JsonResult updateConfirmOrderMsg(@RequestBody PayPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		// 参数过滤
		if (para.getOrderId() == null) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数格式有误");
			return jsonResult;
		}

		// 修改状态
		YdOrderWithBLOBs order = new YdOrderWithBLOBs();
		order.setOrderId(para.getOrderId());
		order.setState((byte) 4);
		order.setReceivingTime(String.valueOf(System.currentTimeMillis()));
		ServiceResult<Object> result = ydOrderService.updateOrderMsg(order);

		// 返回信息
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明: 申请售后原因
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月13日 上午11:47:29
	 */
	@RequestMapping(value = "/refundReasonList", method = RequestMethod.POST)
	public JsonResult queryRefundReasonList() {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<List<YdRefundReason>> result = ydOrderService.selectRefundReasonList();

		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明: 申请售后
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月24日 上午10:56:42
	 */
	@RequestMapping(value = "/refundOrderMsg", method = RequestMethod.POST)
	public JsonResult addRefundOrderMsg(@RequestBody OrderRefundPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		try {
			// 获取当前用户
			YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

			// 申请售后
			para.setUserId(user.getUserId());
			ServiceResult<Object> result = ydOrderService.addRefundOrderMsg(para);

			// 返回信息
			jsonResult.setStatusCode(result.getStatusCode());
			jsonResult.setMsg(result.getMsg());
			jsonResult.setData(result.getData());
		} catch (Exception e) {
			// 未知异常
			jsonResult.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("系统繁忙");
			return jsonResult;
		}

		return jsonResult;
	}
}
