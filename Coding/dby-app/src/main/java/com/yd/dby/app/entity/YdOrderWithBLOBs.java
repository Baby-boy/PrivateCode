package com.yd.dby.app.entity;

public class YdOrderWithBLOBs extends YdOrder {
    private String orderMessage;

    private String deliverExplain;

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage == null ? null : orderMessage.trim();
    }

    public String getDeliverExplain() {
        return deliverExplain;
    }

    public void setDeliverExplain(String deliverExplain) {
        this.deliverExplain = deliverExplain == null ? null : deliverExplain.trim();
    }
}