package com.yd.dby.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdCart;
import com.yd.dby.app.entity.YdDepot;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.CartPara;
import com.yd.dby.app.entity.vo.CartVo;
import com.yd.dby.app.mapper.YdCartMapper;
import com.yd.dby.app.mapper.YdDepotMapper;
import com.yd.dby.app.mapper.YdGoodsMapper;
import com.yd.dby.app.service.YdCartService;

@Service("ydCartService")
public class YdCartServiceImpl implements YdCartService {

	@Autowired
	private YdCartMapper ydCartMapper;

	@Autowired
	private YdGoodsMapper ydGoodsMapper;

	@Autowired
	private YdDepotMapper ydDepotMapper;

	@Override
	public ServiceResult<Object> insertGoodsToCart(CartPara para, YdUser user) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 通过商品ID查询商品添加到购物车所需的数据
		YdCart ydCart = ydGoodsMapper.selectGoodsToCartDetails(para.getGoodsId());
		if (null == ydCart) {
			result.setMsg("添加商品失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			return result;
		}

		// 根据商品ID查询该商品是否已经添加到购物车
		YdCart cart = ydCartMapper.selectGoodsFromCart(ydCart.getDepotId(), user.getUserId());
		if (null != cart) {
			// 查询该商品当前库存
			YdDepot ydDepot = ydDepotMapper.selectDepotStockByCartId(cart.getCartId());
			if (ydDepot.getDepotStock() < para.getCartNum() + cart.getCartNum()) {
				result.setMsg("库存不足!");
				result.setStatusCode(HttpCode.BAD_REQUEST);
				return result;
			}

			cart.setCartNum(para.getCartNum() + cart.getCartNum());
			int info = ydCartMapper.updateByPrimaryKeySelective(cart);
			if (info == 1) {
				result.setMsg("添加商品成功!");
				result.setStatusCode(HttpCode.OK);
			} else {
				result.setMsg("添加商品失败!");
				result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
			}
			return result;
		}

		ydCart.setUserId(user.getUserId());
		ydCart.setCartNum(para.getCartNum());
		ydCart.setCreatedTime(new Date());

		int info = ydCartMapper.insert(ydCart);// 这里可能会抛 store_id为null的异常,这是因为商品过滤问题,导致添加不存在的店铺商品抛异常
		if (info == 1) {
			result.setMsg("添加商品成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("添加商品失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Object> selectCartPageList(YdUser user) {
		ServiceResult<Object> result = new ServiceResult<>();

		List<CartVo> list = ydCartMapper.selectCartPageList(user.getUserId());

		result.setData(list);
		result.setMsg("查询成功!");
		result.setStatusCode(HttpCode.OK);

		return result;
	}

	@Override
	public ServiceResult<Object> updateCartGoodsNum(List<CartPara> paraList) {
		ServiceResult<Object> result = new ServiceResult<Object>();

		for (CartPara cartPara : paraList) {
			// 查询该商品当前库存
			YdDepot ydDepot = ydDepotMapper.selectDepotStockByCartId(cartPara.getCartId());
			if (ydDepot.getDepotStock() < cartPara.getCartNum()) {
				result.setMsg("库存不足!");
				result.setStatusCode(HttpCode.BAD_REQUEST);
				return result;
			}

		}

		int info = ydCartMapper.updateCartGoodsNum(paraList);
		if (info > 0) {
			result.setMsg("编辑成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("编辑失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Object> deleteCartGoods(CartPara para) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 对需要删除的多个商品ID继续处理
		String[] str = StringUtils.split(para.getCartIds(), ",");
		List<Integer> cartIds = new ArrayList<>();
		for (String cartId : str) {
			cartIds.add(Integer.parseInt(cartId));
		}

		int info = ydCartMapper.deleteCartGoods(cartIds);
		if (info > 0) {
			result.setMsg("删除成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("删除失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

}
