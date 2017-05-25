package com.yd.dby.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdOrder;
import com.yd.dby.app.entity.YdOrderWithBLOBs;
import com.yd.dby.app.entity.para.OrderPara;
import com.yd.dby.app.entity.para.PayPara;
import com.yd.dby.app.entity.vo.OrderListVo;

public interface YdOrderMapper {
	public int deleteByPrimaryKey(Integer orderId);

	public int insert(YdOrderWithBLOBs record);

	public int insertSelective(YdOrderWithBLOBs record);

	public YdOrderWithBLOBs selectByPrimaryKey(Integer orderId);

	public int updateByPrimaryKeySelective(YdOrderWithBLOBs record);

	public int updateByPrimaryKeyWithBLOBs(YdOrderWithBLOBs record);

	public int updateByPrimaryKey(YdOrder record);

	public int selectOrderPageCount(@Param("para") OrderPara para);

	public List<OrderListVo> selectOrderPageList(@Param("para") OrderPara para);

	/** 根据状态查询订单总数 */
	public int selectOrderCount(@Param("userId") Integer userId, @Param("state") Integer state);

	void updateSuccessPingPay(@Param("para") PayPara para);

	List<OrderListVo> selectTestVo(Map<String, Object> map);
}