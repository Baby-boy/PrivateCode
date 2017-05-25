package com.yd.dby.app.entity.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明: 订单详情
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年2月10日 上午10:06:50
 * @Version:1.0
 */
public class OrderDetailsVo {

	private Integer ogId;

	private Long orderSn;

	private String goodsCover;

	private String goodsName;

	// private String goodsSummary;

	private Integer goodsNum;

	private BigDecimal goodsPrice;

	private BigDecimal goodsPayPrice;

	private Integer transportFid;

	// 调用第三方 预留属性
	private List<Object> logisticsMsg = new ArrayList<Object>();

	private Byte invoiceType;

	private String invoiceNo;

	// 实际支付 goodsPayPrice X goodsNum
	private BigDecimal totalPrice;

	private Byte state;

	private String receivingTime;

	private String shippingCode;

	private String logisCode;

	private Integer logisId;

	// 物流公司名称
	private String logisTitle;

	// 物流状态（2.在途中，3.签收，5.问题件）
	private String logisState;

	/**
	 * 说明: 实际支付总额
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月10日 上午10:58:53
	 */
	private void countMoney() {
		BigDecimal price = getGoodsPayPrice();
		BigDecimal num = new BigDecimal(Double.valueOf(getGoodsNum()));
		BigDecimal countPrice = price.multiply(num);

		setTotalPrice(countPrice);
	}

	public Integer getOgId() {
		return ogId;
	}

	public void setOgId(Integer ogId) {
		this.ogId = ogId;
	}

	public Long getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(Long orderSn) {
		this.orderSn = orderSn;
	}

	public String getGoodsCover() {
		return goodsCover;
	}

	public void setGoodsCover(String goodsCover) {
		this.goodsCover = goodsCover;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getGoodsPayPrice() {
		return goodsPayPrice;
	}

	public void setGoodsPayPrice(BigDecimal goodsPayPrice) {
		this.goodsPayPrice = goodsPayPrice;
	}

	public Integer getTransportFid() {
		return transportFid;
	}

	public void setTransportFid(Integer transportFid) {
		this.transportFid = transportFid;
	}

	public List<Object> getLogisticsMsg() {
		return logisticsMsg;
	}

	public void setLogisticsMsg(List<Object> logisticsMsg) {
		this.logisticsMsg = logisticsMsg;
	}

	public Byte getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Byte invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getTotalPrice() {
		countMoney();
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}

	public String getReceivingTime() {
		return receivingTime;
	}

	public void setReceivingTime(String receivingTime) {
		this.receivingTime = receivingTime;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public String getLogisCode() {
		return logisCode;
	}

	public void setLogisCode(String logisCode) {
		this.logisCode = logisCode;
	}

	public Integer getLogisId() {
		return logisId;
	}

	public void setLogisId(Integer logisId) {
		this.logisId = logisId;
	}

	public String getLogisTitle() {
		return logisTitle;
	}

	public void setLogisTitle(String logisTitle) {
		this.logisTitle = logisTitle;
	}

	public String getLogisState() {
		return logisState;
	}

	public void setLogisState(String logisState) {
		this.logisState = logisState;
	}

}
