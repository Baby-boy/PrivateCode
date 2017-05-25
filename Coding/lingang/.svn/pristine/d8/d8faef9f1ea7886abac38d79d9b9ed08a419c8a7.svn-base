package com.lingang.consumer.controller.home;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.vo.SysPartnerVo;
import com.lingang.api.domain.vo.SysStationVo;
import com.lingang.api.service.SysPartnerService;
import com.lingang.api.service.SysStationService;

/**
 * @Description: (最新/置顶 信息控制)
 * @Author: lgl(lgl1012dream@foxmail.com)
 * @date:2016年12月3日 下午8:30:45
 * @Version:1.0
 */
@Controller
@RequestMapping("/top")
public class TopController {
	
	@Resource
	private SysPartnerService sysPartnerService;

	@Resource
	private SysStationService sysStationServiceImpl;

	/**
	 * @Description: (最新合作列表)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月3日 下午8:29:53
	 */
	@RequestMapping(value = "/partnerPageList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult partnerPageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysPartnerVo>> result = sysPartnerService.selectTopPartnerVoPageList(pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (最新入驻列表)
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月3日 下午8:29:53
	 */
	@RequestMapping(value = "/stationPageList", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult stationPageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysStationVo>> result = sysStationServiceImpl.selectTopStationVoPageList(pageIndex, pageSize);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}
}
