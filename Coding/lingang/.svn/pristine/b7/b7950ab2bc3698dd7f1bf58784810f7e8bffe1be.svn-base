package com.lingang.consumer.controller.statistics;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.vo.SysPartnerBasicsStatisticsVo;
import com.lingang.api.domain.vo.SysCompanyVo;
import com.lingang.api.domain.vo.SysNewAddStatisticsVo;
import com.lingang.api.domain.vo.SysPartnerTypeStatisticsVo;
import com.lingang.api.service.SysPartnerService;
import com.lingang.consumer.controller.BaseController;

/**
 * @Description: (APP合作伙伴统计接口)
 * @Author: hgx
 * @Version:1.0
 */
@Controller
@RequestMapping("/partnerstatistics")
public class PartnerStatisticsController extends BaseController {

	@Resource
	private SysPartnerService sysPartnerService;

	/**
	 * 合作伙伴-类型分布
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selectPartnerType", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult selectPartnerType(HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysPartnerTypeStatisticsVo>> result = sysPartnerService.selectPartnerType();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * 合作伙伴-合作层级分布
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selectPartnerLevel", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult selectPartnerLevel(HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysPartnerBasicsStatisticsVo>> result = sysPartnerService.selectPartnerLevel();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * 合作伙伴-新增机构
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selectNewCounts", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult selectNewCounts(HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysNewAddStatisticsVo>> result = sysPartnerService.selectNewCounts();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;
	}

	/**
	 * @Description: (合作伙伴-创建单位接口)
	 * @return
	 * @author cnk(chenningkang@adinnet.cn)
	 * @date: 2016年12月15日 下午5:20:31
	 */
	@RequestMapping(value = "/selectPartnerCompany.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult selectStationCompany() {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysCompanyVo>> result = sysPartnerService.selectPartnerCompanys();
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		jsonResult.setDataMap(result.getDataMap());
		return jsonResult;

	}

}
