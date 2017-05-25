package com.yd.dby.app.service;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdCtcOrderWithBLOBs;
import com.yd.dby.app.entity.para.CtcOrderAddMsgPara;
import com.yd.dby.app.entity.para.CtcOrderPara;
import com.yd.dby.app.entity.para.PayPara;
import com.yd.dby.app.entity.vo.CtcOrderDetailsVo;
import com.yd.dby.app.entity.vo.CtcOrderListVo;

public interface YdCtcOrderService {

	/**
	 * 说明: 订单列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月23日 上午10:38:04
	 */
	ServiceResult<Page<CtcOrderListVo>> selectOrderPageList(CtcOrderPara para);

	/**
	 * 说明: 订单详情
	 * 
	 * @param ogId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月10日 上午10:26:24
	 */
	ServiceResult<CtcOrderDetailsVo> selectOrderDetails(Integer ctcOrderId);

	/**
	 * 说明: 生成ctc订单
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月28日 下午2:18:36
	 */
	@Transactional
	ServiceResult<Object> insertOrderMsg(CtcOrderAddMsgPara para);

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
	 * 说明:修改ctc_order信息
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月23日 下午5:57:19
	 */
	@Transactional
	ServiceResult<Object> updateCtcOrderMsg(YdCtcOrderWithBLOBs ctcOrder);

}
