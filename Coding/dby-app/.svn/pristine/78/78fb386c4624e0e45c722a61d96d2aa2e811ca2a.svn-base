package com.yd.dby.app.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.yd.dby.app.entity.YdBanner;
import com.yd.dby.app.entity.YdOnlinebooking;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.StorePara;
import com.yd.dby.app.entity.vo.StoreCirculationVo;
import com.yd.dby.app.service.YdCirculationService;
import com.yd.dby.app.service.YdStoreService;

@RestController
@RequestMapping("circulation")
public class CirculationController extends BaseController {

	@Autowired
	private YdStoreService ydStoreService;

	@Autowired
	private YdCirculationService ydCirculationService;

	/**
	 * 说明: 查询流通置换需要显示的店铺列表
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月8日 下午3:24:34
	 */
	@RequestMapping(value = "storePageList", method = RequestMethod.POST)
	public JsonResult queryStorePageList(@RequestBody StorePara para) {
		JsonResult jsonResult = new JsonResult();

		// 判断请求参数是否正确
		if (null == para.getLongitude() || null == para.getLatitude()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		ServiceResult<Page<StoreCirculationVo>> result = ydStoreService.selectCirculationStorePageList(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 流通置换线上预约
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @throws Exception
	 * @date: 2017年2月8日 下午3:27:10
	 */
	@RequestMapping(value = "onlinebooking", method = RequestMethod.POST)
	public JsonResult addOnlinebooking(@RequestBody YdOnlinebooking para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(para.getUserName()) || StringUtils.isEmpty(para.getUserPhone())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(para.getAddressPca()) || StringUtils.isEmpty(para.getDetailsAddress())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(para.getGoodsName()) || StringUtils.isEmpty(para.getGoodsSummary())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(para.getGoodsPics())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		para.setId(null);
		para.setType(3);
		para.setUserId(user.getUserId());
		para.setCreatedTime(Long.valueOf(System.currentTimeMillis()).toString());
		// 截取第一张图片作为商品的封面图
		String[] goodsPics = StringUtils.split(para.getGoodsPics(), ",");
		para.setGoodsCover(goodsPics[0]);

		ServiceResult<Object> result = ydCirculationService.insertCirculationOnlinebooking(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 流通置换首页
	 * 
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 下午2:07:25
	 */
	@RequestMapping(value = "circulationHome", method = RequestMethod.POST)
	public JsonResult queryCirculationHome() {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<YdBanner> result = ydCirculationService.selectCirculationHome();
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 我的预约列表
	 * 
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @throws Exception
	 * @date: 2017年2月17日 下午3:37:42
	 */
	@RequestMapping(value = "onlinebookingPageList", method = RequestMethod.POST)
	public JsonResult queryCirculationOnlinebookingPageList(@RequestBody BasePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Page<YdOnlinebooking>> result = ydCirculationService.selectCirculationOnlinebookingPageList(para, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 预约详情
	 * 
	 * @param id
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月20日 下午3:06:25
	 */
	@RequestMapping(value = "onlinebookingDetails", method = RequestMethod.GET)
	public JsonResult queryCirculationOnlinebookingDetails(@RequestParam(value = "id", required = true) Integer id) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<YdOnlinebooking> result = ydCirculationService.selectCirculationOnlinebookingDetails(id);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}
}
