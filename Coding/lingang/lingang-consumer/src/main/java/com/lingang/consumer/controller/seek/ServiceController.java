package com.lingang.consumer.controller.seek;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.lingang.api.domain.vo.SysServiceVo;
import com.lingang.api.domain.vo.SysUserVo;
import com.lingang.api.service.SysServiceService;
import com.lingang.consumer.controller.BaseController;

/**
 * @Description: (服务机构信息控制)
 * @Author: lgl(lgl1012dream@foxmail.com)
 * @date:2016年12月5日 下午6:00:22
 * @Version:1.0
 */
@Controller
@RequestMapping("/service")
public class ServiceController extends BaseController {

	@Resource
	private SysServiceService sysServiceService;

	/**
	 * @Description: (服务机构列表)
	 * @param pageIndex
	 * @param pageSize
	 * @param regionId
	 * @param basicsId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 下午6:32:04
	 */
	@RequestMapping(value = "/servicePageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult servicePageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "parkId", required = false, defaultValue = "") Integer parkId,
			@RequestParam(value = "regionTypeId", required = false) Integer regionTypeId,
			@RequestParam(value = "basicsId", required = false, defaultValue = "") Integer basicsId,
			@RequestParam(value = "createYear", required = false) String createYear,
			@RequestParam(value = "createMonth", required = false) String createMonth,
			@RequestParam(value = "createQuarter", required = false) String createQuarter,
			@RequestParam(value = "labelIds", required = false) String labelIdsStr,
			@RequestParam(value = "keywords", required = false) String keywords) {
		JsonResult jsonResult = new JsonResult();
		if (null != parkId && parkId == -1) {
			regionTypeId = null;
			parkId = null;
		} else if (null != parkId && parkId == -2) {
			regionTypeId = 1;
			parkId = null;
		} else if (null != parkId && parkId == -3) {
			regionTypeId = 2;
			parkId = null;
		}

		// 标签
		List<Integer> labelIds = null;
		if (StringUtils.isNotBlank(labelIdsStr)) {
			String[] str = StringUtils.split(labelIdsStr, ",");
			labelIds = new ArrayList<>();
			for (String labelId : str) {
				labelIds.add(Integer.parseInt(labelId));
			}
		}

		// 统计功能查询时 时间处理
		Map<String, Object> map = disposeTime(createYear, createMonth, createQuarter);

		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		map.put("regionTypeId", regionTypeId);
		map.put("parkId", parkId);
		map.put("basicsId", basicsId);
		map.put("labelIds", labelIds);
		map.put("keywords", keywords);
		ServiceResult<Page<SysServiceVo>> result = sysServiceService.selectServicePageList(map);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (服务机构详情)
	 * @param serviceId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月5日 下午9:26:54
	 */
	@RequestMapping(value = "/serviceDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult serviceDetails(@RequestParam(value = "serviceId", required = true) Integer serviceId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysServiceVo> result = sysServiceService.selectServiceByServiceId(serviceId);
		// 获取当前用户
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		// 查询当前用户是否收藏对应的服务机构详情
		SysCollect sysCollect = sysServiceService.selectSysCollect(sysUserVo.getUserId(), serviceId, 3);
		SysServiceVo sysServiceVo = result.getData();
		if (null != sysCollect) {
			sysServiceVo.setCollectState(1);// 收藏
			sysServiceVo.setCollectId(sysCollect.getCollectId());
		} else {
			sysServiceVo.setCollectState(0);// 未收藏
		}

		/** 这里后期如果把公司做成一对多,以下代码可直接删除 */
		List<SysCompany> companys = sysServiceVo.getCompanys();
		if (null != companys && companys.size() > 0) {
			sysServiceVo.setServiceCompany(companys.get(0).getCompanyName());
			sysServiceVo.setCompanys(null);
		}
		/** 到以上 */

		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(sysServiceVo);
		return jsonResult;
	}
}
