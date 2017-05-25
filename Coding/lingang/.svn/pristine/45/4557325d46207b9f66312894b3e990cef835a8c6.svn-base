package com.lingang.platform.controller.seek;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysIndustry;
import com.lingang.api.domain.entity.SysIndustryUmanager;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysParkIndustry;
import com.lingang.api.domain.entity.SysStationIndustry;
import com.lingang.api.domain.para.SysIndustryPara;
import com.lingang.api.domain.para.SysUmanagerPara;
import com.lingang.api.domain.pfvo.SysIndustryPfVo;
import com.lingang.api.domain.vo.SysIndustryVo;
import com.lingang.api.service.SysImagesService;
import com.lingang.api.service.SysIndustryService;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysStationService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/sysIndustry")
public class SysIndustryController {

	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysIndustryService sysIndustryService;

	@Resource
	private SysImagesService sysImagesService;

	@Resource
	private SysStationService sysStationService;

	/**
	 * @Description: (查询产业集群列表_后台 针对权限)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午6:39:43
	 */
	@ResponseBody
	@RequestMapping(value = "/sysIndustryPageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysIndustryPageListP(@RequestBody SysIndustryPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysIndustryPfVo>> result = sysIndustryService.selectIndustryPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询产业集群列表_后台)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午6:39:43
	 */
	@ResponseBody
	@RequestMapping(value = "/sysIndustryPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysIndustryPageList(@RequestBody SysIndustryPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysIndustryPfVo>> result = sysIndustryService.selectIndustryPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询产业集群详情_后台)
	 * @param industryId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午2:23:38
	 */
	@ResponseBody
	@RequestMapping(value = "/industryDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysIndustryDetails(@RequestParam(value = "industryId", required = true) Integer industryId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysIndustryVo> result = sysIndustryService.selectByPrimaryKey(industryId);

		// 补充缩略图
		Map<String, Object> imgMap = new HashMap<String, Object>();
		imgMap.put("minImgPath", "");
		if (result.getData() != null && result.getData().getIminImgId() != null) {
			SysImages images = sysImagesService.selectSysImagesByImgId(result.getData().getIminImgId());
			imgMap.put("minImgPath", images.getImgPath());
		}
		jsonResult.setDataMap(imgMap);

		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (更新产业集群详情_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午2:31:36
	 */
	@ResponseBody
	@RequestMapping(value = "/updateIndustryDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updateIndustryDetails(@RequestBody SysIndustryPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysIndustry industry = new SysIndustry();
		industry.setIndustryId(para.getIndustryId());
		industry.setIndustryTitle(para.getIndustryTitle());
		industry.setIndustryLink(para.getIndustryLink());
		industry.setIndustryState(para.getIndustryState());
		industry.setIndustrySimple(para.getIndustrySimple());
		industry.setIndustryContent(para.getIndustryContent());
		industry.setUpdateTime(newDate);
		industry.setMaxImgId(para.getMaxImgId());
		industry.setMinImgId(para.getMinImgId());

		ServiceResult<Object> result = sysIndustryService.updateIndustryDetails(industry);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改产业集群信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加产业集群_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午2:40:39
	 */
	@ResponseBody
	@RequestMapping(value = "/addIndustryDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult addIndustryDetails(@RequestBody SysIndustryPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysIndustry industry = new SysIndustry();
		industry.setIndustryId(null);
		industry.setIndustryTitle(para.getIndustryTitle());
		industry.setIndustryLink(para.getIndustryLink());
		industry.setIndustryState(para.getIndustryState());
		industry.setIndustrySimple(para.getIndustrySimple());
		industry.setIndustryContent(para.getIndustryContent());
		industry.setCreateTime(newDate);
		industry.setUpdateTime(industry.getCreateTime());
		industry.setMaxImgId(para.getMaxImgId());
		industry.setMinImgId(para.getMinImgId());
		ServiceResult<Object> result = sysIndustryService.addIndustryDetails(industry);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加产业集群信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加级联产业)
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午6:38:42
	 */
	@RequestMapping(value = "/addIndustryCascade.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult addIndustryCascade(@RequestBody SysIndustryPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		if (para.getIndustryType() == 1) {
			SysParkIndustry parkIndustry = new SysParkIndustry();
			parkIndustry.setIndustryId(para.getIndustryId());
			parkIndustry.setParkId(para.getObjId());
			ServiceResult<Object> result = sysIndustryService.addParkIndustryCascade(parkIndustry);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		} else if (para.getIndustryType() == 2) {
			SysStationIndustry stationIndustry = new SysStationIndustry();
			stationIndustry.setIndustryId(para.getIndustryId());
			stationIndustry.setStationId(para.getObjId());
			ServiceResult<Object> result = sysStationService.addStationIndustryCascade(stationIndustry);
			jsonResult.setStateCode(result.getStateCode());
			jsonResult.setMessage(result.getMessage());
		}

		// 记录日志
		if (jsonResult.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加级联产业");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除标签)
	 * @param parkId
	 * @param labId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午3:32:27
	 */
	@RequestMapping(value = "/delLab.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delLab(@RequestParam(value = "industryId", required = true) Integer industryId,
			@RequestParam(value = "labelId", required = true) Integer labelId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysIndustryService.delLab(industryId, labelId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"删除级联标签");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除客户经理)
	 * @param partnerUmanagerId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月12日 下午2:08:07
	 */
	@RequestMapping(value = "/delUman.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delUman(@RequestParam(value = "industryUmanagerId", required = true) Integer industryUmanagerId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysIndustryService.delUman(industryUmanagerId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		
		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"删除客户经理");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加客户经理)
	 * @param para
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月12日 下午2:37:06
	 */
	@RequestMapping(value = "/addUManager.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult addUManager(@RequestBody SysUmanagerPara para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysIndustryUmanager industryUmanager = new SysIndustryUmanager();
		industryUmanager.setIndustryId(para.getObjId());
		industryUmanager.setUserAccount(para.getUserAccount());
		ServiceResult<Object> result = sysIndustryService.addUManager(industryUmanager);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加客户经理");
		}
		return jsonResult;
	}
}
