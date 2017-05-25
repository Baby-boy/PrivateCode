package com.yd.dby.app.controller.ctc;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.controller.BaseController;
import com.yd.dby.app.entity.YdCtcComment;
import com.yd.dby.app.entity.YdCtcOrderWithBLOBs;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.CtcOrderAddMsgPara;
import com.yd.dby.app.entity.para.CtcOrderPara;
import com.yd.dby.app.entity.para.PayPara;
import com.yd.dby.app.entity.vo.CtcOrderDetailsVo;
import com.yd.dby.app.entity.vo.CtcOrderListVo;
import com.yd.dby.app.service.YdCtcCommentService;
import com.yd.dby.app.service.YdCtcOrderService;

@RestController
@RequestMapping(value = "/ctcOrder", produces = "application/json;charset=UTF-8")
public class CtcOrderController extends BaseController {

	@Autowired
	private YdCtcOrderService ydCtcOrderService;

	@Autowired
	private YdCtcCommentService ydCtcCommentService;

	/**
	 * 说明: ctc订单列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月10日 上午11:50:33
	 */
	@RequestMapping(value = "/orderList", method = RequestMethod.POST)
	public JsonResult queryOrderList(@RequestBody CtcOrderPara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		para.setUserId(user.getUserId());
		ServiceResult<Page<CtcOrderListVo>> result = ydCtcOrderService.selectOrderPageList(para);

		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明: ctc订单详情
	 * 
	 * @param ogId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月10日 上午10:25:38
	 */
	@RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
	public JsonResult queryOrderDetails(@RequestParam(value = "ctcOrderId", required = true) Integer ctcOrderId) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<CtcOrderDetailsVo> result = ydCtcOrderService.selectOrderDetails(ctcOrderId);

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
	public JsonResult addCommentOrder(@RequestBody YdCtcComment para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		// 参数信息过滤
		if (para.getCtcOrderId() == null || para.getCtcOrderId() < 1) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数信息错误");
			return jsonResult;
		}
		if (para.getCtcId() == null || para.getCtcId() < 1) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数信息错误");
			return jsonResult;
		}
		if (para.getCtcCommentScore() < 1 || para.getCtcCommentScore() > 5) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数信息错误");
			return jsonResult;
		}
		if (StringUtils.isEmpty(para.getCtcCommentBuyTime())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("参数信息错误");
			return jsonResult;
		}

		try {
			// 获取用户信息
			YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

			para.setUserId(user.getUserId());
			para.setCtcCommentTotalLike(0);
			para.setCtcCommentTotalReply(0);
			para.setCtcCommentCreatedTime(String.valueOf(new Date().getTime()));

			// 添加评论
			ServiceResult<YdCtcComment> result = ydCtcCommentService.insertCommentOrder(para);
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
	public JsonResult addOrderMsg(@RequestBody CtcOrderAddMsgPara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		// 生成订单
		para.setUserId(user.getUserId());
		para.setUserName(user.getUserUsername());
		ServiceResult<Object> result = ydCtcOrderService.insertOrderMsg(para);

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

		try {
			// 获取当前用户
			YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

			// 创建ping++订单
			ServiceResult<Object> result = ydCtcOrderService.insertOrderPay(para);

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
		YdCtcOrderWithBLOBs ctcOrder = new YdCtcOrderWithBLOBs();
		ctcOrder.setCtcOrderId(para.getOrderId());
		ctcOrder.setState((byte) 4);
		ServiceResult<Object> result = ydCtcOrderService.updateCtcOrderMsg(ctcOrder);

		// 返回信息
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

}
