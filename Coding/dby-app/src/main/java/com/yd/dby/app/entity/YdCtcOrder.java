package com.yd.dby.app.entity;

import java.math.BigDecimal;

public class YdCtcOrder {
    private Integer ctcOrderId;

    private Long ctcOrderSn;

    private String paySn;

    private Integer userId;

    private String userName;

    private Integer buyerId;

    private String buyerName;

    private String paymentCode;

    private String paymentTime;

    private String finnshedTime;

    private Byte stateService;

    private Byte state;

    private Byte deleteState;

    private Integer ctcId;

    private String ctcCover;

    private String goodsName;

    private BigDecimal goodsPrice;

    private BigDecimal totalPrice;

    private BigDecimal refundPrice;

    private BigDecimal transportPrice;

    private String createdAt;

    private String cancelReason;

    private String receiptName;

    private String receiptMobile;

    private String receiptAddress;

    private String shippingAddress;

    private Integer shippingId;

    private String shippingExpress;

    private String shippingCode;

    private String shippingTime;

    private String evaluationTime;

    public Integer getCtcOrderId() {
        return ctcOrderId;
    }

    public void setCtcOrderId(Integer ctcOrderId) {
        this.ctcOrderId = ctcOrderId;
    }

    public Long getCtcOrderSn() {
        return ctcOrderSn;
    }

    public void setCtcOrderSn(Long ctcOrderSn) {
        this.ctcOrderSn = ctcOrderSn;
    }

    public String getPaySn() {
        return paySn;
    }

    public void setPaySn(String paySn) {
        this.paySn = paySn == null ? null : paySn.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode == null ? null : paymentCode.trim();
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime == null ? null : paymentTime.trim();
    }

    public String getFinnshedTime() {
        return finnshedTime;
    }

    public void setFinnshedTime(String finnshedTime) {
        this.finnshedTime = finnshedTime == null ? null : finnshedTime.trim();
    }

    public Byte getStateService() {
        return stateService;
    }

    public void setStateService(Byte stateService) {
        this.stateService = stateService;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Byte deleteState) {
        this.deleteState = deleteState;
    }

    public Integer getCtcId() {
        return ctcId;
    }

    public void setCtcId(Integer ctcId) {
        this.ctcId = ctcId;
    }

    public String getCtcCover() {
        return ctcCover;
    }

    public void setCtcCover(String ctcCover) {
        this.ctcCover = ctcCover == null ? null : ctcCover.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
    }

    public BigDecimal getTransportPrice() {
        return transportPrice;
    }

    public void setTransportPrice(BigDecimal transportPrice) {
        this.transportPrice = transportPrice;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt == null ? null : createdAt.trim();
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName == null ? null : receiptName.trim();
    }

    public String getReceiptMobile() {
        return receiptMobile;
    }

    public void setReceiptMobile(String receiptMobile) {
        this.receiptMobile = receiptMobile == null ? null : receiptMobile.trim();
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress == null ? null : receiptAddress.trim();
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress == null ? null : shippingAddress.trim();
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public String getShippingExpress() {
        return shippingExpress;
    }

    public void setShippingExpress(String shippingExpress) {
        this.shippingExpress = shippingExpress == null ? null : shippingExpress.trim();
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode == null ? null : shippingCode.trim();
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime == null ? null : shippingTime.trim();
    }

    public String getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(String evaluationTime) {
        this.evaluationTime = evaluationTime == null ? null : evaluationTime.trim();
    }
}