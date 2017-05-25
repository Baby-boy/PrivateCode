package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdDepot;
import com.yd.dby.app.entity.para.GroupBuyPara;
import com.yd.dby.app.entity.para.SeckillPara;
import com.yd.dby.app.entity.vo.DepotAndGoodsVo;
import com.yd.dby.app.entity.vo.GroupBuyDetailsVo;
import com.yd.dby.app.entity.vo.GroupBuyVo;
import com.yd.dby.app.entity.vo.SeckillDetailsVo;
import com.yd.dby.app.entity.vo.SeckillVo;

public interface YdDepotMapper {
	public int deleteByPrimaryKey(Integer depotId);

	public int insert(YdDepot record);

	public int insertSelective(YdDepot record);

	public YdDepot selectByPrimaryKey(Integer depotId);

	public int updateByPrimaryKeySelective(YdDepot record);

	public int updateByPrimaryKey(YdDepot record);

	public DepotAndGoodsVo selectByGoodsId(Integer goodsId);

	public int updateStockByGoodsId(@Param("depotStock") Integer depotStock, @Param("goodsId") Integer goodsId);

	/** 根据购物车ID查询商品库存 */
	public YdDepot selectDepotStockByCartId(@Param("cartId") Integer cartId);

	int selectGroupBuyPageCount(@Param("para") GroupBuyPara para);

	List<GroupBuyVo> selectGroupBuyPageList(@Param("para") GroupBuyPara para);

	GroupBuyDetailsVo selectGroupBuyDetails(@Param("depotId") Integer depotId);

	int selectSeckillPageCount(@Param("para") SeckillPara para);

	List<SeckillVo> selectSeckillPageList(@Param("para") SeckillPara para);

	SeckillDetailsVo selectSeckillDetails(@Param("depotId") Integer depotId);
	
}