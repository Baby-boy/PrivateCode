package com.yd.dby.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.pingplusplus.model.Charge;
import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdCoupon;
import com.yd.dby.app.entity.YdOrderGoods;
import com.yd.dby.app.entity.YdOrderRefund;
import com.yd.dby.app.entity.YdOrderWithBLOBs;
import com.yd.dby.app.entity.YdRefundReason;
import com.yd.dby.app.entity.YdStoreWithBLOBs;
import com.yd.dby.app.entity.para.OrderAddDBGoodsPara;
import com.yd.dby.app.entity.para.OrderAddDBStorePara;
import com.yd.dby.app.entity.para.OrderAddMsgGoodsPara;
import com.yd.dby.app.entity.para.OrderAddMsgPara;
import com.yd.dby.app.entity.para.OrderAddMsgStorePara;
import com.yd.dby.app.entity.para.OrderPara;
import com.yd.dby.app.entity.para.OrderRefundPara;
import com.yd.dby.app.entity.para.PayPara;
import com.yd.dby.app.entity.vo.ChargeVo;
import com.yd.dby.app.entity.vo.DepotAndGoodsVo;
import com.yd.dby.app.entity.vo.OrderDetailsVo;
import com.yd.dby.app.entity.vo.OrderListVo;
import com.yd.dby.app.mapper.YdCartMapper;
import com.yd.dby.app.mapper.YdCouponMapper;
import com.yd.dby.app.mapper.YdDepotMapper;
import com.yd.dby.app.mapper.YdOrderGoodsMapper;
import com.yd.dby.app.mapper.YdOrderMapper;
import com.yd.dby.app.mapper.YdOrderRefundMapper;
import com.yd.dby.app.mapper.YdReceiveCouponMapper;
import com.yd.dby.app.mapper.YdRefundReasonMapper;
import com.yd.dby.app.mapper.YdStoreMapper;
import com.yd.dby.app.service.YdOrderService;
import com.yd.dby.app.util.YdOrdersn;
import com.yd.dby.app.util.YdUtilUUID;
import com.yd.dby.app.util.kdniao.YdKdNiao;
import com.yd.dby.app.util.pingxx.ChargeCreate;

import net.sf.json.JSONObject;

@Service("ydOrderService")
public class YdOrderServiceImpl implements YdOrderService {

	@Autowired
	private YdOrderGoodsMapper ydOrderGoodsMapper;

	@Autowired
	private YdOrderMapper ydOrderMapper;

	@Autowired
	private YdStoreMapper ydStoreMapper;

	@Autowired
	private YdCouponMapper ydCouponMapper;

	@Autowired
	private YdDepotMapper ydDepotMapper;

	@Autowired
	private YdOrderRefundMapper ydOrderRefundMapper;

	@Autowired
	private YdReceiveCouponMapper ydReceiveCouponMapper;

	@Autowired
	private YdCartMapper ydCartMapper;

	@Autowired
	private YdRefundReasonMapper ydRefundReasonMapper;

	@Autowired
	private StringRedisTemplate redis;

	@Override
	public ServiceResult<Page<OrderListVo>> selectOrderPageList(OrderPara para) {
		ServiceResult<Page<OrderListVo>> result = new ServiceResult<Page<OrderListVo>>();

		int countRecord = ydOrderMapper.selectOrderPageCount(para);
		Page<OrderListVo> page = new Page<OrderListVo>(para.getCurrentPage(), countRecord, para.getOnePageCount());
		if (para.getCurrentPage() <= page.getCountPage()) {
			para.setStartIndex(page.getStartIndex());
			List<OrderListVo> list = ydOrderMapper.selectOrderPageList(para);
			page.setList(list);
		}

		result.setData(page);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceResult<OrderDetailsVo> selectOrderDetails(Integer ogId) {
		ServiceResult<OrderDetailsVo> result = new ServiceResult<OrderDetailsVo>();

		OrderDetailsVo orderDetailsVo = ydOrderGoodsMapper.selectOrderDetails(ogId);

		// 物流信息
		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) YdKdNiao.get(orderDetailsVo.getLogisCode(), orderDetailsVo.getShippingCode());

		// 提取物流状态
		String logisState = new Gson().fromJson(map.get("State").toString(), String.class);
		// 提取物流信息
		List<Object> list = new ArrayList<Object>();
		list = new Gson().fromJson(map.get("Traces").toString(), List.class);
		// 倒叙排列
		Collections.reverse(list);
		orderDetailsVo.setLogisticsMsg(list);
		orderDetailsVo.setLogisState(StringUtils.isEmpty(logisState) ? "0" : logisState);

		result.setData(orderDetailsVo);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Object> insertOrderMsg(OrderAddMsgPara para) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		Date nowDate = new Date();
		BigDecimal pingPrice = new BigDecimal(0);
		Integer metadataPayType = para.getMetadataPayType() == null ? 1 : para.getMetadataPayType();

		// 插入order表的共同数据提取
		YdOrderWithBLOBs orderDB = new YdOrderWithBLOBs();
		orderDB.setPaySn("");
		orderDB.setBuyerId(para.getUserId());
		orderDB.setBuyerName(para.getUserUsername());
		orderDB.setPaymentCode(para.getPaymentCode());
		orderDB.setType(para.getType());
		orderDB.setState((byte) 1);
		orderDB.setDeleteState((byte) 0);
		orderDB.setInvoiceType(para.getInvoiceType());
		orderDB.setInvoiceNo(para.getInvoiceNo());
		orderDB.setCreatedAt(nowDate);
		orderDB.setReceiptName(para.getReceiptName());
		orderDB.setReceiptMobile(para.getReceiptMobile());
		orderDB.setReceiptAddress(para.getReceiptAddress());
		orderDB.setIntegral(para.getIntegral());

		// 插入order表的异同数据
		List<OrderAddDBStorePara> storeDB = new ArrayList<OrderAddDBStorePara>();
		// 记录订单信息 存 redis
		List<Object> redisOrderList = new ArrayList<Object>();

		// 插入order表的异同数据提取
		for (OrderAddMsgStorePara storePara : para.getOrderStore()) {
			// 是否给自己的店铺下单
			YdStoreWithBLOBs store = ydStoreMapper.selectByPrimaryKey(storePara.getStoreId());
			if (para.getUserId() == store.getUserId()) {
				result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
				result.setMsg("不可以给自己下单:" + storePara.getStoreId());
				return result;
			}
			// 读取对应的店铺跟优惠券信息 然后与参数对比，防止非法下单
			YdCoupon coupon = ydCouponMapper.selectByPrimaryKey(storePara.getCouponId());
			if (storePara.getCouponId() != null && storePara.getCouponId() != 0 && coupon == null) {
				result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
				result.setMsg("优惠券信息异常：" + storePara.getCouponId());
				return result;
			}
			// 店铺优惠券价格过滤
			BigDecimal couponMoney = new BigDecimal(
					(coupon == null || coupon.getCouponMoney() == null) ? 0 : coupon.getCouponMoney());
			BigDecimal storeCouponPrice = storePara.getCouponPrice() == null ? new BigDecimal(0)
					: storePara.getCouponPrice();
			if (!(couponMoney.compareTo(storeCouponPrice) == 0)) {
				result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
				result.setMsg("优惠券价格异常：" + storePara.getCouponPrice());
				return result;
			}

			// order表 数据提取
			OrderAddDBStorePara orderStore = new OrderAddDBStorePara();
			orderStore.setOrderSn(Long.parseLong(YdOrdersn.get(para.getUserId())));
			orderStore.setStoreId(storePara.getStoreId());
			orderStore.setStoreName(store.getStoreName());
			orderStore.setCouponId(storePara.getCouponId());
			orderStore.setCouponPrice(storePara.getCouponPrice());
			orderStore.setRefundPrice(BigDecimal.valueOf(0.00));
			orderStore.setOrderMessage(storePara.getOrderMessage());
			orderStore.setTransportFid(storePara.getTransportFid());
			orderStore.setTransportAddress(storePara.getTransportAddress());

			// 商品价格
			BigDecimal storeGoodsPrice = new BigDecimal(0);
			// 运费
			BigDecimal transportPrie = BigDecimal.valueOf(0);
			// 每次使用优惠券的累计
			BigDecimal countVal = new BigDecimal(0);
			// 平均优惠券价格
			BigDecimal val = new BigDecimal(0);
			int index = 1;
			if (storeCouponPrice.compareTo(new BigDecimal(0)) > 0) {
				BigDecimal divisor = new BigDecimal(storePara.getGoods().size());
				val = storeCouponPrice.divide(divisor, 2, BigDecimal.ROUND_HALF_UP);
			}

			// order_goods表 数据提取
			for (OrderAddMsgGoodsPara storeGoodsPara : storePara.getGoods()) {
				// 查询商品
				DepotAndGoodsVo depot = ydDepotMapper.selectByGoodsId(storeGoodsPara.getGoodsId());
				if (depot == null) {
					result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
					result.setMsg("商品信息异常：" + storeGoodsPara.getGoodsId());
					return result;
				}
				if (depot.getDepotStock() == null || depot.getDepotStock() <= 0
						|| depot.getDepotStock() < storeGoodsPara.getGoodsNum()) {
					result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
					result.setMsg("商品库存不足：" + storeGoodsPara.getGoodsId());
					return result;
				}
				OrderAddDBGoodsPara orderGoods = new OrderAddDBGoodsPara();
				BigDecimal depotPrice = depot.getDepotPrice() == null ? new BigDecimal(0) : depot.getDepotPrice();
				BigDecimal goodsPrice = storeGoodsPara.getGoodsPrice();
				if (!(depotPrice.compareTo(goodsPrice) == 0)) {
					result.setStatusCode(HttpCode.NOT_ACCEPTABLE);
					result.setMsg("商品价格异常：" + storeGoodsPara.getGoodsPrice());
					return result;
				}
				orderGoods.setGoodsId(storeGoodsPara.getGoodsId());
				orderGoods.setGoodsName(depot.getGoodsName());
				orderGoods.setOgState(1);
				orderGoods.setGoodsNum(storeGoodsPara.getGoodsNum());
				orderGoods.setGoodsPrice(storeGoodsPara.getGoodsPrice());
				orderGoods.setStoreId(storePara.getStoreId());
				orderGoods.setBuyerId(para.getUserId());
				orderGoods.setGoodsSummary(depot.getGoodsSummary());
				orderGoods.setGoodsCover(depot.getGoodsCover());
				orderGoods.setGoodsOriginalPrice(depot.getDepotOriginalPrice());
				orderGoods.setCreatedAt(nowDate);
				orderGoods.setDepotStock(depot.getDepotStock() - storeGoodsPara.getGoodsNum());
				orderGoods.setCartId(storeGoodsPara.getCartId());

				// 优惠券平均分配 计算出实际成交价
				BigDecimal discountPrice = val;
				index++;
				if (index > storePara.getGoods().size()) {
					discountPrice = storeCouponPrice.subtract(countVal);
				}
				countVal = countVal.add(val);

				// 优惠价
				orderGoods.setDiscountPrice(discountPrice);
				// 实际支付价格=商品价格-优惠价
				orderGoods.setGoodsPayPrice(orderGoods.getGoodsPrice().subtract(orderGoods.getDiscountPrice()));

				// 填入orderGoodsDB
				orderStore.getOrderGoodsDB().add(orderGoods);

				// 店铺商品价格计算
				storeGoodsPrice = storeGoodsPrice.add(goodsPrice);
				// 店铺运费计算
				transportPrie = transportPrie.add(storeGoodsPara.getGoodsFreight());
			}

			// 支付此店铺的实际价格
			BigDecimal totalPrice = (storeGoodsPrice.add(transportPrie)).subtract(storeCouponPrice);
			pingPrice = pingPrice.add(totalPrice);

			orderStore.setGoodsPrice(storeGoodsPrice);
			orderStore.setTransportPrice(transportPrie);
			orderStore.setTotalPrice(totalPrice);

			// 填入orderDB
			storeDB.add(orderStore);

			// 记录订单信息
			HashMap<String, Object> orderMap = new HashMap<String, Object>();
			orderMap.put("order_sn", orderStore.getOrderSn());
			orderMap.put("money", orderStore.getTotalPrice());
			orderMap.put("deliver", orderStore.getTransportFid() == 0 ? "快递" : "自提");
			redisOrderList.add(JSONObject.fromObject(orderMap).toString());
		}

		// 创建ping++支付
		Charge charge = null;
		try {
			ChargeVo chargeVo = new ChargeVo();
			// chargeVo.setAmount(pingPrice);
			// 测试数据 支付金额统一为1分
			chargeVo.setAmount(new BigDecimal(1));
			String sbuject = storeDB.get(0).getOrderGoodsDB().get(0).getGoodsName();
			if (sbuject.length() > 32) {
				sbuject = sbuject.substring(0, 30) + "..";
			}
			chargeVo.setSubject(StringUtils.isEmpty(sbuject) ? "无标题" : sbuject);
			String body = storeDB.get(0).getOrderGoodsDB().get(0).getGoodsSummary();
			if (body.length() > 100) {
				body = body.substring(0, 100) + "..";
			}
			chargeVo.setBody(StringUtils.isEmpty(body) ? "无描述" : body);
			chargeVo.setChannel("alipay");
			chargeVo.setClientIp("127.0.0.1");
			chargeVo.setMetadataPayType(metadataPayType);

			// redis缓存信息 ，跟PC端保持一致，因为支付成功回调的是PC端的接口
			HashMap<String, Object> redisOrderMap = new HashMap<String, Object>();
			redisOrderMap.put("money", new BigDecimal(0.00)); // 订单总金额
			redisOrderMap.put("goods_name", chargeVo.getSubject());
			redisOrderMap.put("goods_body", chargeVo.getBody());
			redisOrderMap.put("order", redisOrderList.toString());
			// 用于记录 redis 中订单金额和订单编号的信息
			String returnUu = YdUtilUUID.generate();
			// 缓存
			redis.opsForValue().set(returnUu, JSONObject.fromObject(redisOrderMap).toString(), 3600, TimeUnit.SECONDS);

			// 创建支付
			chargeVo.setOrderNo(returnUu);
			charge = ChargeCreate.createCharge(chargeVo);
		} catch (Exception e) {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("创建订单失败");
			return result;
		}

		if (charge != null) {
			// 数据插入
			for (OrderAddDBStorePara st : storeDB) {
				YdOrderWithBLOBs order = orderDB;
				order.setOrderSn(st.getOrderSn());
				order.setStoreId(st.getStoreId());
				order.setStoreName(st.getStoreName());
				order.setCouponId(st.getCouponId());
				order.setCouponPrice(st.getCouponPrice());
				order.setGoodsPrice(st.getGoodsPrice());
				order.setTotalPrice(st.getTotalPrice());
				order.setRefundPrice(st.getRefundPrice());
				order.setOrderMessage(st.getOrderMessage());
				order.setTransportFid(st.getTransportFid());
				order.setTransportAddress(st.getTransportAddress());

				// 插入order数据
				ydOrderMapper.insertSelective(order);

				// 插入 order_goods数据
				for (OrderAddDBGoodsPara og : st.getOrderGoodsDB()) {
					og.setOrderId(order.getOrderId());
					// 添加订单商品
					ydOrderGoodsMapper.insertSelective(og);
					// 删除我的优惠券
					ydReceiveCouponMapper.deleteByUserIdAndCouponId(order.getBuyerId(), order.getCouponId());
					// 修改仓库 库存
					ydDepotMapper.updateStockByGoodsId(og.getDepotStock(), og.getGoodsId());
					// 删除购物车
					ydCartMapper.deleteByPrimaryKey(og.getCartId());
				}

				orderDB.setOrderId(null);
			}

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

		YdOrderWithBLOBs order = ydOrderMapper.selectByPrimaryKey(para.getOrderId());

		// 记录订单信息 存 redis
		List<Object> redisOrderList = new ArrayList<Object>();
		// 记录订单信息
		HashMap<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("order_sn", order.getOrderSn());
		orderMap.put("money", order.getTotalPrice());
		orderMap.put("deliver", order.getTransportFid() == 0 ? "快递" : "自提");
		redisOrderList.add(JSONObject.fromObject(orderMap).toString());

		// 创建ping++支付
		Charge charge = null;
		try {
			ChargeVo chargeVo = new ChargeVo();
			// chargeVo.setAmount(pingPrice);
			// 测试数据 支付金额统一为1分
			chargeVo.setAmount(new BigDecimal(1));
			String sbuject = order.getStoreName();
			if (sbuject.length() > 32) {
				sbuject = sbuject.substring(0, 30) + "..";
			}
			chargeVo.setSubject(StringUtils.isEmpty(sbuject) ? "无标题" : sbuject);
			String body = order.getStoreName();
			if (body.length() > 100) {
				body = body.substring(0, 100) + "..";
			}
			chargeVo.setBody(StringUtils.isEmpty(body) ? "无描述" : body);
			chargeVo.setChannel("alipay");
			chargeVo.setClientIp("127.0.0.1");
			Integer metadataPayType = 1;
			chargeVo.setMetadataPayType(metadataPayType);

			// redis缓存信息 ，跟PC端保持一致，因为支付成功回调的是PC端的接口
			HashMap<String, Object> redisOrderMap = new HashMap<String, Object>();
			redisOrderMap.put("money", new BigDecimal(0.00)); // 订单总金额
			redisOrderMap.put("goods_name", chargeVo.getSubject());
			redisOrderMap.put("goods_body", chargeVo.getBody());
			redisOrderMap.put("order", redisOrderList.toString());
			// 用于记录 redis 中订单金额和订单编号的信息
			String returnUu = YdUtilUUID.generate();
			// 缓存
			redis.opsForValue().set(returnUu, JSONObject.fromObject(redisOrderMap).toString(), 3600, TimeUnit.SECONDS);

			// 创建支付
			chargeVo.setOrderNo(returnUu);
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
	public ServiceResult<Object> updateOrderMsg(YdOrderWithBLOBs order) {
		ServiceResult<Object> result = new ServiceResult<Object>();

		int i = ydOrderMapper.updateByPrimaryKeySelective(order);

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

	@Override
	public ServiceResult<Object> addRefundOrderMsg(OrderRefundPara para) {
		ServiceResult<Object> result = new ServiceResult<Object>();

		YdOrderWithBLOBs order = ydOrderMapper.selectByPrimaryKey(para.getOrderId());
		if (order == null) {
			result.setStatusCode(HttpCode.NO_CONTENT);
			result.setMsg("订单信息错误");
			return result;
		}

		YdOrderGoods orderGoods = ydOrderGoodsMapper.selectByPrimaryKey(para.getOgId());
		if (orderGoods == null) {
			result.setStatusCode(HttpCode.NO_CONTENT);
			result.setMsg("商品信息错误");
			return result;
		}
		Integer refundNum = orderGoods.getRefundNum() + para.getGoodsNum();
		if (orderGoods.getGoodsNum() < refundNum) {
			result.setStatusCode(HttpCode.NO_CONTENT);
			result.setMsg("此商品最多还能退" + (orderGoods.getGoodsNum() - orderGoods.getRefundNum()) + "件");
			return result;
		}

		YdOrderRefund orderRefund = new YdOrderRefund();
		orderRefund.setOrderId(order.getOrderId());
		orderRefund.setOrderSn(order.getOrderSn().toString());
		orderRefund.setRefundSn(YdOrdersn.get(para.getUserId()));
		orderRefund.setStoreId(order.getStoreId());
		orderRefund.setStoreName(order.getStoreName());
		orderRefund.setBuyerId(order.getBuyerId());
		orderRefund.setBuyerName(order.getBuyerName());
		// 如果达到全部退货的标准(order_goods表里的goods_num与refund_num相等)，标记为0
		orderRefund.setGoodsId(orderGoods.getGoodsNum() - refundNum == 0 ? 0 : orderGoods.getGoodsId());
		orderRefund.setOrderGoodsId(orderGoods.getGoodsNum() - refundNum == 0 ? 0 : orderGoods.getOgId());
		orderRefund.setGoodsName(orderGoods.getGoodsName());
		orderRefund.setGoodsImage(orderGoods.getGoodsCover());
		orderRefund.setGoodsNum(para.getGoodsNum());
		orderRefund.setRefundAmount(para.getRefundAmount());
		orderRefund.setRefundType(para.getRefundType());
		orderRefund.setGoodsState(para.getGoodsState());
		orderRefund.setOrderGoodsType((byte) 1);
		orderRefund.setSellerState((byte) 1);
		orderRefund.setRefundState((byte) 1);
		orderRefund.setAddTime(String.valueOf(System.currentTimeMillis()));
		orderRefund.setPicInfo(para.getPicInfo());
		orderRefund.setBuyerMessage(para.getBuyerMessage());

		int i = ydOrderRefundMapper.insertSelective(orderRefund);

		if (i == 1) {
			result.setStatusCode(HttpCode.OK);
			result.setMsg("申请成功");
		} else {
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			result.setMsg("申请失败");
			return result;
		}

		return result;
	}

	@Override
	public ServiceResult<List<YdRefundReason>> selectRefundReasonList() {
		ServiceResult<List<YdRefundReason>> result = new ServiceResult<List<YdRefundReason>>();

		List<YdRefundReason> list = ydRefundReasonMapper.selectRefundReasonList();

		result.setData(list);
		result.setStatusCode(HttpCode.OK);

		return result;
	}

}
