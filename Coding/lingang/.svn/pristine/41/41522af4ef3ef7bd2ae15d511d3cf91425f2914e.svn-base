package com.lingang.consumer.controller.seek;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysCollect;
import com.lingang.api.domain.entity.SysCompany;
import com.lingang.api.domain.vo.SysPartnerVo;
import com.lingang.api.domain.vo.SysUserVo;
import com.lingang.api.service.SysPartnerService;
import com.lingang.consumer.controller.BaseController;

/**
 * @Description: (合作伙伴信息控制)
 * @Author: lgl(lgl1012dream@foxmail.com)
 * @date:2016年12月5日 下午12:17:31
 * @Version:1.0
 */
@Controller
@RequestMapping("/partner")
public class PartnerController extends BaseController {

	@Resource
	private SysPartnerService sysPartnerService;

	/**
	 * @Description: (合作伙伴列表)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 下午12:19:16
	 */
	@RequestMapping(value = "/partnerPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult partnerPageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "typeId", required = false) Integer typeId,
			@RequestParam(value = "basicsId", required = false) Integer basicsId,
			@RequestParam(value = "companyId", required = false) Integer companyId,
			@RequestParam(value = "createYear", required = false) String createYear,
			@RequestParam(value = "createMonth", required = false) String createMonth,
			@RequestParam(value = "createQuarter", required = false) String createQuarter,
			@RequestParam(value = "keywords", required = false) String keywords) {
		JsonResult jsonResult = new JsonResult();
		
		// 统计功能查询时 时间处理
		Map<String, Object> map = disposeTime(createYear, createMonth, createQuarter);
		
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		map.put("typeId", typeId);
		map.put("basicsId", basicsId);
		map.put("companyId", companyId);
		map.put("keywords", keywords);
		ServiceResult<Page<SysPartnerVo>> result = sysPartnerService.selectPartnerPageList(map);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (合作伙伴详情)
	 * @param partnerId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 下午2:45:05
	 */
	@RequestMapping(value = "/partnerDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult partnerDetails(@RequestParam(value = "partnerId", required = true) Integer partnerId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysPartnerVo> result = sysPartnerService.selectPartnerVoByPartnerId(partnerId);
		// 获取当前用户
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		// 查询当前用户是否收藏对应的合作伙伴详情
		SysCollect sysCollect = sysPartnerService.selectSysCollect(sysUserVo.getUserId(), partnerId, 2);
		SysPartnerVo sysPartnerVo = result.getData();
		if (null != sysCollect) {
			sysPartnerVo.setCollectState(1);// 收藏
			sysPartnerVo.setCollectId(sysCollect.getCollectId());
		} else {
			sysPartnerVo.setCollectState(0);// 未收藏
		}

		/** 这里后期如果把公司做成一对多,以下代码可直接删除 */
		List<SysCompany> companys = sysPartnerVo.getCompanys();
		if (null != companys && companys.size() > 0) {
			sysPartnerVo.setPartnerCompany(companys.get(0).getCompanyName());
			sysPartnerVo.setCompanys(null);
		}
		/** 到以上 */

		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(sysPartnerVo);
		return jsonResult;
	}
	
}
