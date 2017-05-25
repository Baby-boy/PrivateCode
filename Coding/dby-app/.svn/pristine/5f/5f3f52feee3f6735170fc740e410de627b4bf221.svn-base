package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdCtcOrder;
import com.yd.dby.app.entity.YdCtcOrderWithBLOBs;
import com.yd.dby.app.entity.para.CtcOrderPara;
import com.yd.dby.app.entity.vo.CtcOrderDetailsVo;
import com.yd.dby.app.entity.vo.CtcOrderListVo;

public interface YdCtcOrderMapper {
    int deleteByPrimaryKey(Integer ctcOrderId);

    int insert(YdCtcOrderWithBLOBs record);

    int insertSelective(YdCtcOrderWithBLOBs record);

    YdCtcOrderWithBLOBs selectByPrimaryKey(Integer ctcOrderId);

    int updateByPrimaryKeySelective(YdCtcOrderWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(YdCtcOrderWithBLOBs record);

    int updateByPrimaryKey(YdCtcOrder record);
    
	int selectOrderPageCount(@Param("para") CtcOrderPara para);

	List<CtcOrderListVo> selectOrderPageList(@Param("para") CtcOrderPara para);
	
	CtcOrderDetailsVo selectOrderDetails(Integer ctcOrderId);
	
	
}