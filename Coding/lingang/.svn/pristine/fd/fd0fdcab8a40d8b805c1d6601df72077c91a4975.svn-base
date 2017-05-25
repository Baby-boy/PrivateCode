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
import com.lingang.api.domain.vo.SysFileVo;
import com.lingang.api.domain.vo.SysParkVo;
import com.lingang.api.domain.vo.SysUserVo;
import com.lingang.api.service.SysParkService;
import com.lingang.consumer.controller.BaseController;

@Controller
@RequestMapping("/park")
public class SysParkController extends BaseController {

	@Resource
	private SysParkService sysParkService;

	/**
	 * @Description: (查询产业园区列表)
	 * @param pageIndex
	 * @param pageSize
	 * @param regionId
	 * @param industryIds
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月4日 上午11:16:21
	 */
	@ResponseBody
	@RequestMapping(value = "/parkPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult parkPageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "regionId", required = false, defaultValue = "") Integer regionId,
			@RequestParam(value = "regionTypeId", required = false, defaultValue = "") Integer regionTypeId,
			@RequestParam(value = "industryIds", required = false, defaultValue = "") String industryIdStr,
			@RequestParam(value = "createYear", required = false) String createYear,
			@RequestParam(value = "createMonth", required = false) String createMonth,
			@RequestParam(value = "createQuarter", required = false) String createQuarter,
			@RequestParam(value = "keywords", required = false) String keywords) {
		if (null != regionId && regionId == -1) {
			regionTypeId = null;
			regionId = null;
		} else if (null != regionId && regionId == -2) {
			regionTypeId = 1;
			regionId = null;
		} else if (null != regionId && regionId == -3) {
			regionTypeId = 2;
			regionId = null;
		}
		JsonResult jsonResult = new JsonResult();

		List<Integer> industryIds = null;
		if (StringUtils.isNotBlank(industryIdStr)) {
			String[] str = StringUtils.split(industryIdStr, ",");
			industryIds = new ArrayList<>();
			for (String industryId : str) {
				industryIds.add(Integer.parseInt(industryId));
			}
		}

		// 统计功能查询时 时间处理
		Map<String, Object> map = disposeTime(createYear, createMonth, createQuarter);

		map.put("regionId", regionId);
		map.put("regionTypeId", regionTypeId);
		map.put("industryIds", industryIds);
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		map.put("keywords", keywords);
		ServiceResult<Page<SysParkVo>> result = sysParkService.selectSysParkVoPageListByRegionIdAndIndustryIds(map);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (查询产业园区详情)
	 * @param parkId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月5日 下午12:25:40
	 */
	@ResponseBody
	@RequestMapping(value = "/parkDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult parkDetails(@RequestParam(value = "parkId", required = true) Integer parkId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysParkVo> result = sysParkService.selectSysParkDetailsByStationId(parkId);
		// 获取当前用户
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		// 查询当前用户是否收藏对应的产业园区详情
		SysCollect sysCollect = sysParkService.selectSysCollect(sysUserVo.getUserId(), 1, parkId);
		SysParkVo sysParkVo = result.getData();
		if (null != sysCollect) {
			sysParkVo.setCollectState(1);// 收藏
			sysParkVo.setCollectId(sysCollect.getCollectId());
		} else {
			sysParkVo.setCollectState(0);// 未收藏
		}
		
		/** 这里后期如果把公司做成一对多,以下代码可直接删除 */
		List<SysCompany> companys = sysParkVo.getCompanys();
		if (null != companys && companys.size() > 0) {
			sysParkVo.setParkCompany(companys.get(0).getCompanyName());
			sysParkVo.setCompanys(null);
		}
		/** 到以上 */
		
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(sysParkVo);
		return jsonResult;
	}

	/**
	 * @Description: (宣传册/视频 点击弹窗)
	 * @param fileId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月7日 下午9:47:02
	 */
	@RequestMapping(value = "/clickPopup.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult clickPopup(@RequestParam(value = "fileId", required = true) Integer fileId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		Integer userId = sysUserVo.getUserId();
		ServiceResult<SysFileVo> result = sysParkService.selectClickPopup(fileId, userId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}
}
