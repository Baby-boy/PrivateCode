package com.yd.dby.app.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.pingplusplus.model.Charge;
import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdCtc;
import com.yd.dby.app.entity.YdCtcOrderWithBLOBs;
import com.yd.dby.app.entity.para.CtcOrderAddMsgPara;
import com.yd.dby.app.entity.para.CtcOrderPara;
import com.yd.dby.app.entity.para.PayPara;
import com.yd.dby.app.entity.vo.ChargeVo;
import com.yd.dby.app.entity.vo.CtcOrderDetailsVo;
import com.yd.dby.app.entity.vo.CtcOrderListVo;
import com.yd.dby.app.mapper.YdCtcMapper;
import com.yd.dby.app.mapper.YdCtcOrderMapper;
import com.yd.dby.app.service.YdCtcOrderService;
import com.yd.dby.app.util.YdMd5Util;
import com.yd.dby.app.util.YdOrdersn;
import com.yd.dby.app.util.pingxx.ChargeCreate;

@Service("ydCtcOrderService")
public class YdCtcOrderServiceImpl implements YdCtcOrderService {

	@Autowired
	private YdCtcOrderMapper ydCtcOrderMapper;

	@Autowired
	private YdCtcMapper ydCtcMapper;

	@Autowired
	private StringRedisTemplate redis;

	@Override
	public ServiceResult<Page<CtcOrderListVo>> selectOrderPageList(CtcOrderPara para) {
		ServiceResult<Page<CtcOrderListVo>> result = new ServiceResult<Page<CtcOrderListVo>>();

		int countRecord = ydCtcOrderMapper.selectOrderPageCount(para);
		Page<CtcOrderListVo> page = new Page<CtcOrderListVo>(para.getCurrentPage(), countRecord,
				para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<CtcOrderListVo> list = ydCtcOrderMapper.selectOrderPageList(para);
			page.setList(list);
		}

		result.setData(page);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<CtcOrderDetailsVo> selectOrderDetails(Integer ctcOrderId) {
		ServiceResult<CtcOrderDetailsVo> result = new ServiceResult<CtcOrderDetailsVo>();

		CtcOrderDetailsVo detailsVo = ydCtcOrderMapper.selectOrderDetails(ctcOrderId);

		result.setData(detailsVo);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Object> insertOrderMsg(CtcOrderAddMsgPara para) {
		ServiceResult<Object> result = new ServiceResult<Object>();

		// 信息过滤
		YdCtc ctc = ydCtcMapper.selectByPrimaryKey(para.getCtcId());
		if (ctc == null) {
			result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
			result.setMsg("商品信息异常：" + para.getCtcId());
			return result;
		}
		if (ctc.getCtcIsAvailable() == 3 || ctc.getCtcIsAvailable() == 5) {
			result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
			result.setMsg("商品信息异常：" + para.getCtcId());
			return result;
		}
		if (ctc.getCtcIsAvailable() == 2) {
			result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
			result.setMsg("该商品已下架");
			return result;
		}
		if (ctc.getCtcIsAvailable() == 4) {
			result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
			result.setMsg("手速太慢，该商品已被抢走啦");
			return result;
		}
		if (!(ctc.getCtcPrice().compareTo(para.getGoodsPrice()) == 0)) {
			result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
			result.setMsg("商品价格异常：" + para.getGoodsPrice());
			return result;
		}

		// 数据填充
		String nowDate = String.valueOf(System.currentTimeMillis());
		YdCtcOrderWithBLOBs ctcOrder = new YdCtcOrderWithBLOBs();
		ctcOrder.setCtcOrderSn(Long.parseLong(YdOrdersn.get(para.getUserId())));
		ctcOrder.setPaySn("0");
		ctcOrder.setUserId(ctc.getUserId());
		// ctcOrder.setUserName(userName);
		ctcOrder.setBuyerId(para.getUserId());
		ctcOrder.setBuyerName(para.getUserName());
		ctcOrder.setPaymentCode(para.getPaymentCode());
		ctcOrder.setPaymentTime(nowDate);
		ctcOrder.setState((byte) 1);
		ctcOrder.setDeleteState((byte) 0);
		ctcOrder.setCtcId(para.getCtcId());
		ctcOrder.setCtcCover(ctc.getCtcCover());
		ctcOrder.setGoodsName(ctc.getCtcName());
		ctcOrder.setGoodsPrice(ctc.getCtcPrice());
		ctcOrder.setTotalPrice(para.getGoodsPrice());
		ctcOrder.setRefundPrice(new BigDecimal(0));
		ctcOrder.setTransportPrice(new BigDecimal(0));
		ctcOrder.setCreatedAt(nowDate);
		ctcOrder.setOrderMessage(para.getOrderMessage());
		ctcOrder.setReceiptName(para.getReceiptName());
		ctcOrder.setReceiptMobile(para.getReceiptMobile());
		ctcOrder.setReceiptAddress(para.getReceiptAddress());

		// 创建ping++支付
		Charge charge = null;
		try {
			ChargeVo chargeVo = new ChargeVo();
			// chargeVo.setAmount(pingPrice);
			// 测试数据 支付金额统一为1分
			chargeVo.setAmount(new BigDecimal(1));
			String sbuject = ctcOrder.getGoodsName();
			if (sbuject.length() > 32) {
				sbuject = sbuject.substring(0, 30) + "..";
			}
			chargeVo.setSubject(StringUtils.isEmpty(sbuject) ? "无标题" : sbuject);
			String body = ctcOrder.getGoodsName();
			if (body.length() > 100) {
				body = body.substring(0, 100) + "..";
			}
			chargeVo.setBody(StringUtils.isEmpty(body) ? "无描述" : body);
			chargeVo.setChannel("alipay");
			chargeVo.setClientIp("127.0.0.1");
			chargeVo.setMetadataPayType(2);

			// 缓存
			redis.opsForValue().set(YdMd5Util.GetMD5Code(ctcOrder.getCtcOrderSn().toString()),
					ctcOrder.getCtcId().toString(), 3600, TimeUnit.SECONDS);

			// 创建支付
			chargeVo.setOrderNo(ctcOrder.getCtcOrderSn().toString());
			charge = ChargeCreate.createCharge(chargeVo);
		} catch (Exception e) {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("创建订单失败");
			return result;
		}

		if (charge != null) {
			// 添加订单
			ydCtcOrderMapper.insertSelective(ctcOrder);
			// 修改商品状态
			YdCtc c = new YdCtc();
			c.setCtcId(ctcOrder.getCtcId());
			c.setCtcIsAvailable(4);
			ydCtcMapper.updateByPrimaryKeySelective(c);

			// 返回信息
			result.setData(charge);
			result.setStatusCode(HttpCode.OK);
			result.setMsg("创建订单成功");
		}

		return result;
	}

	@Override
	public ServiceResult<Object> insertOrderPay(PayPara para) {
		ServiceResult<Object> result = new ServiceResult<Object>();

		YdCtcOrderWithBLOBs ydCtcOrder = ydCtcOrderMapper.selectByPrimaryKey(para.getOrderId());

		// 创建ping++支付
		Charge charge = null;
		try {
			ChargeVo chargeVo = new ChargeVo();
			// chargeVo.setAmount(pingPrice);
			// 测试数据 支付金额统一为1分
			chargeVo.setAmount(new BigDecimal(1));
			String sbuject = ydCtcOrder.getGoodsName();
			if (sbuject.length() > 32) {
				sbuject = sbuject.substring(0, 30) + "..";
			}
			chargeVo.setSubject(StringUtils.isEmpty(sbuject) ? "无标题" : sbuject);
			String body = ydCtcOrder.getGoodsName();
			if (body.length() > 100) {
				body = body.substring(0, 100) + "..";
			}
			chargeVo.setBody(StringUtils.isEmpty(body) ? "无描述" : body);
			chargeVo.setChannel("alipay");
			chargeVo.setClientIp("127.0.0.1");
			chargeVo.setMetadataPayType(2);

			// 缓存
			redis.opsForValue().set(YdMd5Util.GetMD5Code(ydCtcOrder.getCtcOrderSn().toString()),
					ydCtcOrder.getCtcId().toString(), 3600, TimeUnit.SECONDS);

			// 创建支付
			chargeVo.setOrderNo(ydCtcOrder.getCtcOrderSn().toString());
			charge = ChargeCreate.createCharge(chargeVo);
		} catch (Exception e) {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("创建订单失败");
			return result;
		}

		if (charge != null) {
			// 返回信息
			result.setData(charge);
			result.setStatusCode(HttpCode.OK);
			result.setMsg("创建订单成功");
		}

		return result;
	}

	@Override
	public ServiceResult<Object> updateCtcOrderMsg(YdCtcOrderWithBLOBs ctcOrder) {
		ServiceResult<Object> result = new ServiceResult<Object>();

		int i = ydCtcOrderMapper.updateByPrimaryKeySelective(ctcOrder);

		if (i == 1) {
			result.setStatusCode(HttpCode.OK);
			result.setMsg("修改成功");
		} else {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("修改失败");
			return result;
		}

		return result;
	}

}
