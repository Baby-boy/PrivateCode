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
import com.lingang.api.domain.vo.SysPublicVo;
import com.lingang.api.domain.vo.SysUserVo;
import com.lingang.api.service.SysPublicService;
import com.lingang.consumer.controller.BaseController;

@Controller
@RequestMapping("/public")
public class SysPublicController extends BaseController {

	@Resource
	private SysPublicService sysPublicService;

	/**
	 * @Description: (查询公共平台列表)
	 * @param pageIndex
	 * @param pageSize
	 * @param regionId
	 * @param basicsId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月5日 下午4:23:22
	 */
	@ResponseBody
	@RequestMapping(value = "/publicPageList", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult publicPageList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "parkId", required = false) Integer parkId,
			@RequestParam(value = "regionTypeId", required = false) Integer regionTypeId,
			@RequestParam(value = "basicsId", required = false) Integer basicsId,
			@RequestParam(value = "createYear", required = false) String createYear,
			@RequestParam(value = "createMonth", required = false) String createMonth,
			@RequestParam(value = "createQuarter", required = false) String createQuarter,
			@RequestParam(value = "companyId", required = false) Integer companyId,
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
		
		// 统计功能查询时 时间处理
		Map<String, Object> map = disposeTime(createYear, createMonth, createQuarter);

		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		map.put("parkId", parkId);
		map.put("regionTypeId", regionTypeId);
		map.put("basicsId", basicsId);
		map.put("companyId", companyId);
		map.put("keywords", keywords);
		ServiceResult<Page<SysPublicVo>> result = sysPublicService.selectSysPublicVoPageListByRegionIdAndBasicsId(map);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询公共平台详情)
	 * @param publicId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月5日 下午7:45:44
	 */
	@ResponseBody
	@RequestMapping(value = "/publicDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult publicDetails(@RequestParam(value = "publicId", required = true) Integer publicId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysPublicVo> result = sysPublicService.selectSysPublicDetailsByPublicId(publicId);
		// 获取当前用户
		SysUserVo sysUserVo = (SysUserVo) getTokenData(request);
		// 查询当前用户是否收藏对应的公共平台详情
		SysCollect sysCollect = sysPublicService.selectSysCollect(sysUserVo.getUserId(), publicId, 5);
		SysPublicVo sysPublicVo = result.getData();
		if (null != sysCollect) {
			sysPublicVo.setCollectState(1);// 收藏
			sysPublicVo.setCollectId(sysCollect.getCollectId());
		} else {
			sysPublicVo.setCollectState(0);// 未收藏
		}

		/** 这里后期如果把公司做成一对多,以下代码可直接删除 */
		List<SysCompany> companys = sysPublicVo.getCompanys();
		if (null != companys && companys.size() > 0) {
			sysPublicVo.setPublicCompany(companys.get(0).getCompanyName());
			sysPublicVo.setCompanys(null);
		}
		/** 到以上 */

		jsonResult.setData(sysPublicVo);
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}
}
