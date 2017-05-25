package com.lingang.consumer.controller.seek;

import java.util.HashMap;
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
import com.lingang.api.domain.vo.SysIndustryVo;
import com.lingang.api.service.SysIndustryService;

@Controller
@RequestMapping("/industry")
public class SysIndustryController {
	@Resource
	private SysIndustryService sysIndustryService;

	@ResponseBody
	@RequestMapping(value = "/industryPageList", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult industryPageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "parkId", required = false) Integer parkId, HttpServletRequest request,
			@RequestParam(value = "keywords", required = false) String keywords) {
		JsonResult jsonResult = new JsonResult();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		map.put("parkId", parkId);
		map.put("keywords", keywords);
		ServiceResult<Page<SysIndustryVo>> result = sysIndustryService.selectSysIndustryList(map);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	};

	/**
	 * @Description: (产业集群详情)
	 * @param industryId
	 * @param request
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月4日 下午5:28:46
	 */
	@ResponseBody
	@RequestMapping(value = "/industryDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult industryDetails(@RequestParam(value = "industryId", required = true) Integer industryId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysIndustryVo> result = sysIndustryService.selectByPrimaryKey(industryId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	};

}
