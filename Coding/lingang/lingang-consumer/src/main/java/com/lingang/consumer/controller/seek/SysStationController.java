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
import com.lingang.api.domain.vo.SysStationVo;
import com.lingang.api.domain.vo.SysUserVo;
import com.lingang.api.service.SysStationService;
import com.lingang.consumer.controller.BaseController;

@Controller
@RequestMapping("station")
public class SysStationController extends BaseController {
	@Resource
	private SysStationService sysStationService;

	/**
	 * @Description: (入住列表)
	 * @param pageIndex
	 * @param pageSize
	 * @param regionId
	 * @param industryIds
	 * @param label_ids
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月6日 上午1:16:24
	 */
	@ResponseBody
	@RequestMapping(value = "/stationPageList", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult stationPageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "regionTypeId", required = false) Integer regionTypeId,
			@RequestParam(value = "industryIds", required = false) String industryIdsStr,
			@RequestParam(value = "labelIds", required = false) String labelIdsStr,
			@RequestParam(value = "parkId", required = false) Integer parkId,
			@RequestParam(value = "createYear", required = false) String createYear,
			@RequestParam(value = "createMonth", required = false) String createMonth,
			@RequestParam(value = "createQuarter", required = false) String createQuarter,
			@RequestParam(value = "companyId", required = false) Integer companyId,
			@RequestParam(value = "layout", required = false) String layout,
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
		// String转换为List
		List<Integer> industryIds = null;
		if (StringUtils.isNotBlank(industryIdsStr)) {
			String[] str = StringUtils.split(industryIdsStr, ",");
			industryIds = new ArrayList<>();
			for (String industryId : str) {
				industryIds.add(Integer.parseInt(industryId));
			}
		}
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
		map.put("industryIds", industryIds);
		map.put("labelIds", labelIds);
		map.put("parkId", parkId);
		map.put("companyId", companyId);
		map.put("layout", layout);
		map.put("keywords", keywords);
		ServiceResult<Page<SysStationVo>> result = sysStationService.selectSysStationAll(map);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (入驻详情)
	 * @param stationId
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月6日 上午1:16:46
	 */
	@ResponseBody
	@RequestMapping(value = "/stationDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult stationDetails(@RequestParam(value = "stationId", required = true) Integer stationId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysStationVo> result = sysStationService.selectByPrimaryKey(stationId);
		// 获取当前用户
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		// 查询当前用户是否收藏对应的入驻企业详情
		SysCollect sysCollect = sysStationService.selectSysCollect(sysUserVo.getUserId(), stationId, 4);
		SysStationVo sysStationVo = result.getData();
		if (null != sysCollect) {
			sysStationVo.setCollectState(1);// 收藏
			sysStationVo.setCollectId(sysCollect.getCollectId());
		} else {
			sysStationVo.setCollectState(0);// 未收藏
		}

		/** 这里后期如果把公司做成一对多,以下代码可直接删除 */
		List<SysCompany> companys = sysStationVo.getCompanys();
		if (null != companys && companys.size() > 0) {
			sysStationVo.setStationCompany(companys.get(0).getCompanyName());
			sysStationVo.setCompanys(null);
		}
		/** 到以上 */

		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(sysStationVo);
		return jsonResult;
	}

}
