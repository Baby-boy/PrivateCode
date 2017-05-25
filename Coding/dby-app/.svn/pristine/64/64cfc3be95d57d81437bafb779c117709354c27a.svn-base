package com.yd.dby.app.controller.ctc;

import java.util.Date;
import java.util.List;

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
import com.yd.dby.app.entity.YdCtc;
import com.yd.dby.app.entity.YdDict;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.CtcHomePara;
import com.yd.dby.app.entity.para.CtcPara;
import com.yd.dby.app.entity.vo.CtcClassifyVo;
import com.yd.dby.app.entity.vo.CtcCommentVo;
import com.yd.dby.app.entity.vo.CtcGoodsDetailsVo;
import com.yd.dby.app.entity.vo.CtcGoodsListVo;
import com.yd.dby.app.service.YdCtcService;

@RestController
@RequestMapping("ctc")
public class CtcController extends BaseController {

	@Autowired
	private YdCtcService ydCtcService;

	/**
	 * 说明: 懒鱼筛选分类
	 * 
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 上午11:53:35
	 */
	@RequestMapping(value = "ctcClassifyList", method = RequestMethod.POST)
	public JsonResult queryCtcClassifyList() {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<List<CtcClassifyVo>> result = ydCtcService.selectCtcClassifyList();

		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 懒鱼商品的质量列表
	 * 
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月24日 下午1:11:41
	 */
	@RequestMapping(value = "qualityList", method = RequestMethod.POST)
	public JsonResult queryQualityList() {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<List<YdDict>> result = ydCtcService.selectQualityList();

		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 发布懒鱼商品
	 * 
	 * @param ydCtc(请求参数)
	 * @param request
	 * @return
	 * @throws Exception
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 上午11:38:49
	 */
	@RequestMapping(value = "addCtcGoods", method = RequestMethod.POST)
	public JsonResult addCtcGoods(@RequestBody YdCtc ydCtc, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(ydCtc.getCtcPca()) || StringUtils.isEmpty(ydCtc.getCtcName())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(ydCtc.getCtcSummary()) || StringUtils.isEmpty(ydCtc.getCtcPics())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (null == ydCtc.getCtcClassifyId1() || null == ydCtc.getCtcClassifyId2()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (null == ydCtc.getCtcPrice() || null == ydCtc.getCtcQualityId()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ydCtc.setCtcId(null);// 防止恶意传参
		ydCtc.setUserId(user.getUserId());
		ydCtc.setCtcIsAvailable(1);// 默认发布的商品都是处于出售中 -- 状态 1:出售中 2:下架 3:违规 4,作废
		ydCtc.setCtcCreatedTime(new Date());
		// 截取第一张图片作为商品的封面图
		String[] ctcPics = StringUtils.split(ydCtc.getCtcPics(), ",");
		ydCtc.setCtcCover(ctcPics[0]);
		ydCtc.setCtcTotalFav(0);// 设置默认值
		ydCtc.setCtcTotalMessage(0);// 设置默认值

		ServiceResult<Object> result = ydCtcService.insertCtcGoods(ydCtc);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 查询用户发布商品列表
	 * 
	 * @param para
	 * @param request
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @throws Exception
	 * @date: 2017年2月17日 下午7:00:44
	 */
	@RequestMapping(value = "ctcGoodsPageList", method = RequestMethod.POST)
	public JsonResult queryUserCtcGoodsPageList(@RequestBody BasePara para, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<Page<CtcGoodsListVo>> result = ydCtcService.selectUserCtcGoodsPageList(user, para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 用户编辑上传的懒鱼商品
	 * 
	 * @param ydCtc
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月18日 上午9:47:20
	 */
	@RequestMapping(value = "updateCtcGoods", method = RequestMethod.POST)
	public JsonResult updateUserCtcGoods(@RequestBody YdCtc ydCtc) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(ydCtc.getCtcPca()) || StringUtils.isEmpty(ydCtc.getCtcName())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (StringUtils.isEmpty(ydCtc.getCtcSummary()) || StringUtils.isEmpty(ydCtc.getCtcPics())) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (null == ydCtc.getCtcClassifyId1() || null == ydCtc.getCtcClassifyId2()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}
		if (null == ydCtc.getCtcPrice() || null == ydCtc.getCtcId() || null == ydCtc.getCtcQualityId()) {
			jsonResult.setStatusCode(HttpCode.BAD_REQUEST);
			jsonResult.setMsg("请求参数异常!");
			return jsonResult;
		}

		// 截取第一张图片作为商品的封面图
		String[] ctcPics = StringUtils.split(ydCtc.getCtcPics(), ",");
		ydCtc.setCtcCover(ctcPics[0]);

		ServiceResult<Object> result = ydCtcService.updateUserCtcGoods(ydCtc);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 删除懒鱼商品
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月18日 下午2:10:37
	 */
	@RequestMapping(value = "deleteCtcGoods", method = RequestMethod.POST)
	public JsonResult deleteUserCtcGoods(@RequestBody CtcPara para) {
		JsonResult jsonResult = new JsonResult();

		// 判断请求参数是否正确
		if (StringUtils.isEmpty(para.getCtcIds())) {
			jsonResult.setStatusCode(HttpCode.NO_CONTENT);
			jsonResult.setMsg("未删除商品!");
			return jsonResult;
		}

		ServiceResult<Object> result = ydCtcService.deleteUserCtcGoods(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 懒鱼首页
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 下午3:07:07
	 */
	@RequestMapping(value = "ctcHome", method = RequestMethod.POST)
	public JsonResult queryCtcHome(@RequestBody CtcHomePara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<CtcGoodsListVo>> result = ydCtcService.selectCtcHome(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

	/**
	 * 说明: 懒鱼商品详情
	 * 
	 * @param ctcId
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @throws Exception
	 * @date: 2017年2月16日 上午10:56:06
	 */
	@RequestMapping(value = "/ctcGoodsDetails", method = RequestMethod.GET)
	public JsonResult queryCtcGoodsDetails(@RequestParam(value = "ctcId", required = true) Integer ctcId, HttpServletRequest request) throws Exception {
		JsonResult jsonResult = new JsonResult();

		// 获取当前用户
		YdUser user = (YdUser) redisGet(request.getHeader("token") + "User", YdUser.class);

		ServiceResult<CtcGoodsDetailsVo> result = ydCtcService.selectCtcGoodsDetails(ctcId, user);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}

	/**
	 * 说明: 查询懒鱼他人主页
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月16日 下午5:08:47
	 */
	@RequestMapping(value = "/ctcOthersHomepage", method = RequestMethod.POST)
	public JsonResult queryCtcOthersHomepage(@RequestBody CtcPara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<CtcGoodsListVo>> result = ydCtcService.selectCtcOthersHomepage(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

	/**
	 * 说明: 他人主页评论列表
	 * 
	 * @param para(请求参数)
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月17日 下午1:23:31
	 */
	@RequestMapping(value = "/ctcOthersHomepageComment", method = RequestMethod.POST)
	public JsonResult queryCtcOthersHomepageCommentPageList(@RequestBody CtcPara para) {
		JsonResult jsonResult = new JsonResult();

		ServiceResult<Page<CtcCommentVo>> result = ydCtcService.selectCtcOthersHomepageCommentPageList(para);
		jsonResult.setMsg(result.getMsg());
		jsonResult.setData(result.getData());
		jsonResult.setStatusCode(result.getStatusCode());
		return jsonResult;
	}
}
