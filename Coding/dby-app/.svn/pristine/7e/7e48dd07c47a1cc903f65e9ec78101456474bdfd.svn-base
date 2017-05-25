package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdCart;
import com.yd.dby.app.entity.para.CartPara;
import com.yd.dby.app.entity.vo.CartVo;

public interface YdCartMapper {
	public int deleteByPrimaryKey(Integer cartId);

	public int insert(YdCart record);

	public int insertSelective(YdCart record);

	public YdCart selectByPrimaryKey(Integer cartId);

	public int updateByPrimaryKeySelective(YdCart record);

	public int updateByPrimaryKey(YdCart record);

	/** 根据商品ID查询用户购物车 */
	public YdCart selectGoodsFromCart(@Param("depotId") Integer depotId, @Param("userId") Integer userId);

	/** 查询用户购物车列表 */
	public List<CartVo> selectCartPageList(@Param("userId") Integer userId);

	/** 编辑购物车购买数量 */
	public int updateCartGoodsNum(@Param("paraList") List<CartPara> paraList);

	/** 删除购物车中商品 */
	public int deleteCartGoods(@Param("cartIds") List<Integer> cartIds);

}