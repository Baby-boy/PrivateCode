package com.yd.dby.app.controller.classify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.JsonResult;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.para.CommentPara;
import com.yd.dby.app.entity.para.GoodsPara;
import com.yd.dby.app.entity.vo.ClassifyVo;
import com.yd.dby.app.entity.vo.GoodsDetailsCommentVo;
import com.yd.dby.app.entity.vo.GoodsDetailsVo;
import com.yd.dby.app.entity.vo.GoodsListVo;
import com.yd.dby.app.service.YdClassifyService;
import com.yd.dby.app.service.YdGoodsService;

/**
 * 说明: 分类信息
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年2月8日 上午9:37:52
 * @Version:1.0
 */
@RestController
@RequestMapping(value = "/classify", produces = { "application/json;charset=UTF-8" })
public class ClassifyController {

	@Autowired
	YdClassifyService ydClassifyService;

	@Autowired
	YdGoodsService ydGoodsService;

	/**
	 * 说明: 分类列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月8日 上午10:33:11
	 */
	@RequestMapping(value = "/classifyList", method = RequestMethod.POST)
	public JsonResult queryClassifyList() {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<List<ClassifyVo>> result = ydClassifyService.selectClassifyList();

		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明:商品列表
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月8日 下午1:36:37
	 */
	@RequestMapping(value = "/goodsList", method = RequestMethod.POST)
	public JsonResult queryGoodsList(@RequestBody GoodsPara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<GoodsListVo>> result = ydGoodsService.selectClassifyGoodsPageList(para);

		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明: 商品详情
	 * 
	 * @param goodsId
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月8日 下午2:36:54
	 */
	@RequestMapping(value = "/goodsDetails", method = RequestMethod.GET)
	public JsonResult queryGoodsDetails(@RequestParam(value = "goodsId", required = true) Integer goodsId) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<GoodsDetailsVo> result = ydGoodsService.selectGoodsDetails(goodsId);

		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setData(result.getData());

		return jsonResult;
	}

	/**
	 * 说明: 全部评论
	 * 
	 * @param para
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月8日 下午3:47:21
	 */
	@RequestMapping(value = "/commentList", method = RequestMethod.POST)
	public JsonResult queryCommentList(@RequestBody CommentPara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<GoodsDetailsCommentVo>> result = ydGoodsService.selectCommentPageList(para);

		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());

		return jsonResult;
	}

}
