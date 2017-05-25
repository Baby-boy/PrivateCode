package com.yd.dby.c.member.entity;

import com.yd.dby.utils.date.YdUtilDate;

public class YdOrderRefund {

	private Integer refund_id;
	private Integer order_id;
	private String order_sn;
	private String refund_sn;
	private Integer store_id;
	private String store_name;
	private Integer buyer_id;
	private String buyer_name;
	private Integer goods_id;
	private Integer order_goods_id;
	private String goods_name;
	private Integer goods_num;
	private Float refund_amount;
	private String goods_image;
	private Integer order_goods_type;
	private Integer refund_type;
	private Integer seller_state;
	private Integer refund_state;
	private Integer return_type;
	private Integer order_lock;
	private Integer goods_state;
	private long add_time;
	private long seller_time;
	private long admin_time;
	private Integer reason_id;
	private String reason_info;
	private String pic_info;
	private String buyer_message;
	private String seller_message;
	private String admin_message;
	private Integer express_id;
	private String invoice_no;
	private long ship_time;
	private long delay_time;
	private long receive_time;
	private String receive_message;
	private Integer commis_rate;
	public Integer getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(Integer refund_id) {
		this.refund_id = refund_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getRefund_sn() {
		return refund_sn;
	}
	public void setRefund_sn(String refund_sn) {
		this.refund_sn = refund_sn;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public Integer getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(Integer buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getOrder_goods_id() {
		return order_goods_id;
	}
	public void setOrder_goods_id(Integer order_goods_id) {
		this.order_goods_id = order_goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	public Float getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(Float refund_amount) {
		this.refund_amount = refund_amount;
	}
	public String getGoods_image() {
		return goods_image;
	}
	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}
	public Integer getOrder_goods_type() {
		return order_goods_type;
	}
	public void setOrder_goods_type(Integer order_goods_type) {
		this.order_goods_type = order_goods_type;
	}
	public Integer getRefund_type() {
		return refund_type;
	}
	public void setRefund_type(Integer refund_type) {
		this.refund_type = refund_type;
	}
	public Integer getSeller_state() {
		return seller_state;
	}
	public void setSeller_state(Integer seller_state) {
		this.seller_state = seller_state;
	}
	public Integer getRefund_state() {
		return refund_state;
	}
	public void setRefund_state(Integer refund_state) {
		this.refund_state = refund_state;
	}
	public Integer getReturn_type() {
		return return_type;
	}
	public void setReturn_type(Integer return_type) {
		this.return_type = return_type;
	}
	public Integer getOrder_lock() {
		return order_lock;
	}
	public void setOrder_lock(Integer order_lock) {
		this.order_lock = order_lock;
	}
	public Integer getGoods_state() {
		return goods_state;
	}
	public void setGoods_state(Integer goods_state) {
		this.goods_state = goods_state;
	}
	public long getAdd_time() {
		return add_time;
	}
	public String getAdd_time_data() {
		return YdUtilDate.stampToDateDay( String.valueOf(add_time) );
	}
	public void setAdd_time(long add_time) {
		this.add_time = add_time;
	}
	public long getSeller_time() {
		return seller_time;
	}
	public void setSeller_time(long seller_time) {
		this.seller_time = seller_time;
	}
	public long getAdmin_time() {
		return admin_time;
	}
	public void setAdmin_time(long admin_time) {
		this.admin_time = admin_time;
	}
	public Integer getReason_id() {
		return reason_id;
	}
	public void setReason_id(Integer reason_id) {
		this.reason_id = reason_id;
	}
	public String getReason_info() {
		return reason_info;
	}
	public void setReason_info(String reason_info) {
		this.reason_info = reason_info;
	}
	public String getPic_info() {
		return pic_info;
	}
	public void setPic_info(String pic_info) {
		this.pic_info = pic_info;
	}
	public String getBuyer_message() {
		return buyer_message;
	}
	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}
	public String getSeller_message() {
		return seller_message;
	}
	public void setSeller_message(String seller_message) {
		this.seller_message = seller_message;
	}
	public String getAdmin_message() {
		return admin_message;
	}
	public void setAdmin_message(String admin_message) {
		this.admin_message = admin_message;
	}
	public Integer getExpress_id() {
		return express_id;
	}
	public void setExpress_id(Integer express_id) {
		this.express_id = express_id;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public long getShip_time() {
		return ship_time;
	}
	public void setShip_time(long ship_time) {
		this.ship_time = ship_time;
	}
	public long getDelay_time() {
		return delay_time;
	}
	public void setDelay_time(long delay_time) {
		this.delay_time = delay_time;
	}
	public long getReceive_time() {
		return receive_time;
	}
	public void setReceive_time(long receive_time) {
		this.receive_time = receive_time;
	}
	public String getReceive_message() {
		return receive_message;
	}
	public void setReceive_message(String receive_message) {
		this.receive_message = receive_message;
	}
	public Integer getCommis_rate() {
		return commis_rate;
	}
	public void setCommis_rate(Integer commis_rate) {
		this.commis_rate = commis_rate;
	}
	
	
}