package com.yd.dby.app.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdOrderWithBLOBs;
import com.yd.dby.app.entity.YdRefundReason;
import com.yd.dby.app.entity.para.OrderAddMsgPara;
import com.yd.dby.app.entity.para.OrderPara;
import com.yd.dby.app.entity.para.OrderRefundPara;
import com.yd.dby.app.entity.para.PayPara;
import com.yd.dby.app.entity.vo.OrderDetailsVo;
import com.yd.dby.app.entity.vo.OrderListVo;

public interface YdOrderService {

	/**
	 * 说明: 订单列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月10日 上午11:50:02
	 */
	ServiceResult<Page<OrderListVo>> selectOrderPageList(OrderPara para);

	/**
	 * 说明: 订单详情
	 * 
	 * @param ogId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月10日 上午10:26:24
	 */
	ServiceResult<OrderDetailsVo> selectOrderDetails(Integer ogId);

	/**
	 * 说明: 订单结算，生成order跟order_goods订单信息(只是生成订单，未支付)
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月16日 下午5:28:57
	 */
	@Transactional
	ServiceResult<Object> insertOrderMsg(OrderAddMsgPara para);

	/**
	 * 说明: 支付(待付款状态进行支付)
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月23日 下午2:48:35
	 */
	ServiceResult<Object> insertOrderPay(PayPara para);

	/**
	 * 说明:修改order信息
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月23日 下午5:57:19
	 */
	@Transactional
	ServiceResult<Object> updateOrderMsg(YdOrderWithBLOBs order);

	/**
	 * 说明: 申请售后
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月24日 上午11:02:02
	 */
	@Transactional
	ServiceResult<Object> addRefundOrderMsg(OrderRefundPara para);

	/**
	 * 说明: 申请售后原因
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年3月13日 上午11:40:41
	 */
	ServiceResult<List<YdRefundReason>> selectRefundReasonList();

}
