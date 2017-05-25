package com.yd.dby.app.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.HomePara;
import com.yd.dby.app.entity.vo.GoodsListVo;
import com.yd.dby.app.service.YdGoodsService;

/**
 * 说明: 首页信息
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年2月7日 下午3:10:01
 * @Version:1.0
 */
@RestController
@RequestMapping(value = "/home", produces = { "application/json;charset=UTF-8" })
public class HomeController {

	@Autowired
	private YdGoodsService ydGoodsService;

	/**
	 * 说明: 首页信息
	 * 
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月7日 下午3:17:10
	 */
	@RequestMapping(value = "/homeDetails", method = RequestMethod.POST)
	public JsonResult queryHomeDetails(@RequestBody HomePara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<GoodsListVo>> result = ydGoodsService.selectHomePageList(para);

		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());

		return jsonResult;
	}

	/**
	 * 说明: 首页搜索 商品列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月9日 上午10:01:23
	 */
	@RequestMapping(value = "/searchGoodsList", method = RequestMethod.POST)
	public JsonResult queryHomeGoodsList(@RequestBody HomePara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<GoodsListVo>> result = ydGoodsService.selectHomeGoodsPageList(para);

		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setData(result.getData());

		return jsonResult;
	}
}
