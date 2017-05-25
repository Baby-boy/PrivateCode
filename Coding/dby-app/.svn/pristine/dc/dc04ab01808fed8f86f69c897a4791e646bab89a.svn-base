package com.yd.dby.app.controller.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.controller.BaseController;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.StorePara;
import com.yd.dby.app.entity.vo.GoodsListVo;
import com.yd.dby.app.entity.vo.StoreClassifyVo;
import com.yd.dby.app.entity.vo.StoreVo;
import com.yd.dby.app.service.YdStoreService;

@RestController
@RequestMapping("store")
public class StoreController extends BaseController {

	@Autowired
	private YdStoreService ydStoreService;

	/**
	 * 说明: 查询品牌店列表
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月7日 下午1:14:52
	 */
	@RequestMapping(value = "storePageList", method = RequestMethod.POST)
	public JsonResult queryStorePageList(@RequestBody StorePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 判断请求参数是否正确
		if (null == para.getLongitude() || null == para.getLatitude()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		para.setUserId(user.getUserId());

		ServiceResult<Page<StoreVo>> result = ydStoreService.selectStorePageList(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 查询品牌店详情
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @throws Exception
	 * @date: 2017年2月7日 下午4:01:53
	 */
	@RequestMapping(value = "storeDetails", method = RequestMethod.POST)
	public JsonResult queryStoreDetails(@RequestBody StorePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 判断请求参数是否正确
		if (null == para.getStoreId()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		para.setUserId(user.getUserId());

		ServiceResult<StoreVo> result = ydStoreService.selectStoreDetails(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

	/**
	 * 说明: 查询品牌店所有商品(店铺首页)
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月8日 上午10:19:38
	 */
	@RequestMapping(value = "goodsPageList", method = RequestMethod.POST)
	public JsonResult queryGoodsPageList(@RequestBody StorePara para) {
		JsonResult jsonResult = new JsonResult();

		// 判断请求参数是否正确
		if (null == para.getStoreId()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		ServiceResult<Page<GoodsListVo>> result = ydStoreService.selectStoreGoodsPageList(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

	/**
	 * 说明: 店铺分类筛选
	 * 
	 * @param scoreId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月8日 下午9:08:29
	 */
	@RequestMapping(value = "storeClassifyList", method = RequestMethod.GET)
	public JsonResult queryStoreClassifyList(@RequestParam(value = "storeId", required = true) Integer storeId) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<List<StoreClassifyVo>> result = ydStoreService.selectStoreClassifyList(storeId);

		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}
}
