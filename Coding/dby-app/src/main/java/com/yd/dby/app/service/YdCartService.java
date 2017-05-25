package com.yd.dby.app.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.CartPara;

public interface YdCartService {

	/**
	 * 说明: 添加商品到购物车
	 * 
	 * @param para
	 * @param user(当前用户)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @param user
	 * @date: 2017年2月9日 下午2:37:17
	 */
	@Transactional
	public ServiceResult<Object> insertGoodsToCart(CartPara para, YdUser user);

	/**
	 * 说明: 查询用户购物车列表
	 * 
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月9日 下午4:31:02
	 */
	public ServiceResult<Object> selectCartPageList(YdUser user);

	/**
	 * 说明: 编辑购物车购买数量
	 * 
	 * @param paraList
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 上午10:53:39
	 */
	@Transactional
	public ServiceResult<Object> updateCartGoodsNum(List<CartPara> paraList);

	/**
	 * 说明: 删除购物车中商品
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午12:44:12
	 */
	@Transactional
	public ServiceResult<Object> deleteCartGoods(CartPara para);

}
