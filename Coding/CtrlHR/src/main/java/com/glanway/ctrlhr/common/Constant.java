/**
 ***********************************************
 * function: 常量类
 * date: 2014年8月7日 下午03:00:30 <br/>
 * @author huahaitao
 * @version 1.2
 * Log:delete useless Code modify by chenguang 20160127
 *************************************************
 **/
package com.glanway.ctrlhr.common;

public interface Constant {

	/***************************************
	 * COMMON 通用
	 ***********************************************/

	
	
	/**
	 * @Fields DEFAULT_HEAD_IMAGE : head图标
	 */
	String DEFAULT_HEAD_IMAGE = "storage/images/head.png";
	
	/**
	 * 删除
	 */
	boolean DELETED = Boolean.TRUE;

	/**
	 * 未删除
	 */
	boolean NOT_DELETED = Boolean.FALSE;
	/**
	 * 激活
	 */
	boolean ACTIVE = Boolean.TRUE;

	/**
	 * 未激活
	 */
	boolean NOT_ACTIVE = Boolean.FALSE;

	/**
	 * 待审核(新增的业务单状态为待审核，当前暂定采购单、入库单、调拨单、盘点单使用此规则)
	 */
	Integer AUDIT_STATUS_PENDING_AUDIT = 7;

	/**
	 * 已审核(审核完成的单子为已审核，当前暂定采购单、入库单、调拨单、盘点单使用此规则)
	 */
	Integer AUDIT_STATUS_AUDITED = 8;

	/**
	 * 已驳回(驳回状态下的单据，重新编辑后状态变更为待审核，无编辑功能的单据直接作废。目前作用范围为：采购单，入库单，调拨单，盘点单)
	 */
	Integer AUDIT_STATUS_REJECT = 9;

	/**
	 * 采购 财务审核 已付款审核
	 */
	Integer AUDIT_STATUS_FINANCE_PAID = 10;

	/**
	 * 采购 财务审核 未付款审核
	 */
	Integer AUDIT_STATUS_FINANCE_UNPAID = 11;

	/**
	 * 调拨 库管 已审核
	 */
	Integer AUDIT_STATUS_STORE_AUDIT = 12;

	/******************************************* 订单状态 **********************************************/

	/**
	 * ORDERS_STATUS_SUBMIT:订单已提交待支付. 支付失败也为1
	 */
	Integer ORDERS_STATUS_SUBMIT = 1;

	/**
	 * ORDERS_STATUS_PAID:订单已支付待拣货. 2 4 5 6 为待发货
	 */
	Integer ORDERS_STATUS_PAID = 2;

	/**
	 * ORDERS_STATUS_YET_SHIPPED:待发货.
	 */
	Integer ORDERS_STATUS_YET_SHIPPED = 4;

	/**
	 * ORDERS_STATUS_PICKING:已生成拣货单.
	 */
	Integer ORDERS_STATUS_PICKING = 5;

	/**
	 * ORDERS_STATUS_PICKING_COMPLETE:已拣货完毕待打包.
	 */
	Integer ORDERS_STATUS_PICKING_COMPLETE = 6;

	/**
	 * ORDERS_STATUS_COMPLETE_PACKAGE:打包完毕,. 待收货
	 */
	Integer ORDERS_STATUS_COMPLETE_PACKAGE = 7;

	/**
	 * ORDERS_STATUS_SHIPPED:已确认收货. 待评价
	 */
	Integer ORDERS_STATUS_SHIPPED = 8;

	/**
	 * ORDERS_STATUS_RETURN:订单退换货.
	 */
	Integer ORDERS_STATUS_RETURN = 9;

	/**
	 * ORDERS_STATUS_CANCELLED:订单取消.
	 */
	Integer ORDERS_STATUS_CANCELLED = 10;

	/**
	 * ORDERS_STATUS_COMMENTED:已评论.
	 */
	Integer ORDERS_STATUS_COMMENTED = 11;

	/**
	 * ORDERS_STATUS_COMMENTED:问题/缺货的订单.
	 */
	Integer ORDERS_STATUS_ISSUE = 12;

	/**
	 * ORDERS_STATUS_COMMENTED:问题/缺货的订单（继续发货）.
	 */
	Integer ORDERS_STATUS_ISSUE_DELIVERY = 13;







	/***************************************** 单品状态 **********************************************/

	/**
	 * 单品状态：无库存
	 */
	String GOODS_STATUS_NO_STOCK = "0";

	/**
	 * 单品状态：不存在
	 */
	String GOODS_STATUS_NO_EXISTS = "-1";

	/**
	 * 单品状态：正常出售
	 */
	String GOODS_STATUS_NORMAL = "1";

	/**
	 * 单品状态：已下架
	 */
	String GOODS_STATUS_NO_PUTAWAY = "2";

	/**
	 * 单品状态：库存不足
	 */
	String GOODS_STATUS_STOCK_SHORTAGE = "3";

	/****************************************** 用户类型 ****************************************************************/

	/**
	 * 普通用户
	 */
	public final String USER_TYPE_NORMAL = "1";

	/**
	 * 商家用户
	 */
	public final String USER_TYPE_MERCHANT = "2";

	//后台路径统一路径
//	String ADMIN_PREFIX = "admin";
	String ADMIN_PREFIX = "";
}
