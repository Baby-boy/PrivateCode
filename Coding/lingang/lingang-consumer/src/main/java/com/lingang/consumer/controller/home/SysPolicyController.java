package com.lingang.consumer.controller.home;

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
import com.lingang.api.domain.entity.SysPolicy;
import com.lingang.api.domain.vo.SysPolicyVo;
import com.lingang.api.service.SysPolicyService;

@Controller
@RequestMapping("/policy")
public class SysPolicyController {
	@Resource
	private SysPolicyService sysPolicyService;

	@ResponseBody
	@RequestMapping(value = "/policyPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult newsPageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		ServiceResult<Page<SysPolicy>> result = sysPolicyService.selectSysPolicyAll(map);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	};

	/**
	 * @Description: (查询政策详情)
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月3日 下午3:45:46
	 */
	@ResponseBody
	@RequestMapping(value = "/policyDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult stationDetails(@RequestParam(value = "policyId", required = true) Integer policyId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysPolicyVo> result = sysPolicyService.selectByPrimaryKey(policyId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());
		return jsonResult;
	};

}
