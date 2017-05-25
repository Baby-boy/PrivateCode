package com.yd.dby.app.controller.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.controller.BaseController;
import com.yd.dby.app.entity.YdFavorite;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.FavoritePara;
import com.yd.dby.app.entity.vo.FavoriteCtcGoodsVo;
import com.yd.dby.app.entity.vo.FavoriteGoodsVo;
import com.yd.dby.app.entity.vo.FavoriteStoreVo;
import com.yd.dby.app.service.YdFavoriteService;

@RestController
@RequestMapping("favorite")
public class FavoriteController extends BaseController {

	@Autowired
	private YdFavoriteService ydFavoriteService;

	/**
	 * 说明: 添加收藏
	 * 
	 * @param ydFavorite(请求参数)
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午3:51:10
	 */
	@RequestMapping(value = "addFavorite", method = RequestMethod.POST)
	public JsonResult addUserFavorite(@RequestBody YdFavorite ydFavorite, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		if (null == ydFavorite.getFavType() || null == ydFavorite.getFavFid()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ydFavorite.setFavId(null);// 防止恶意传参
		ydFavorite.setUserId(user.getUserId());
		ydFavorite.setFavCreatedTime(new Date());

		ServiceResult<Object> result = ydFavoriteService.insertUserFavorite(ydFavorite);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 取消收藏
	 * 
	 * @param ydFavorite(请求参数)
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午5:30:06
	 */
	@RequestMapping(value = "deleteFavorite", method = RequestMethod.POST)
	public JsonResult deleteUserFavorite(@RequestBody FavoritePara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Object> result = ydFavoriteService.deleteUserFavorite(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 查询收藏商品列表
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午6:33:59
	 */
	@RequestMapping(value = "favoriteGoodsList", method = RequestMethod.POST)
	public JsonResult queryFavoriteGoodsList(@RequestBody BasePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Page<FavoriteGoodsVo>> result = ydFavoriteService.selectFavoriteGoodsList(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 查询收藏店铺列表
	 * 
	 * @param para(请求参数)
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月10日 下午8:10:53
	 */
	@RequestMapping(value = "favoriteStoreList", method = RequestMethod.POST)
	public JsonResult queryFavoriteStoreList(@RequestBody BasePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Page<FavoriteStoreVo>> result = ydFavoriteService.selectFavoriteStoreList(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 查询收藏懒鱼商品列表
	 * 
	 * @param para(请求参数)
	 * @param request
	 * @return
	 * @throws Exception
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月18日 下午3:09:28
	 */
	@RequestMapping(value = "favoriteCtcGoodsList", method = RequestMethod.POST)
	public JsonResult queryFavoriteCtcGoodsList(@RequestBody BasePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Page<FavoriteCtcGoodsVo>> result = ydFavoriteService.selectFavoriteCtcGoodsList(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}
}
