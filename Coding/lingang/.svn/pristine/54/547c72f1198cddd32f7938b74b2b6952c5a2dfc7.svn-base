package com.lingang.platform.controller.seek;

import java.util.Date;

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
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysStation;
import com.lingang.api.domain.entity.SysStationUmanager;
import com.lingang.api.domain.para.SysStationPara;
import com.lingang.api.domain.para.SysUmanagerPara;
import com.lingang.api.domain.pfvo.SysStationPfVo;
import com.lingang.api.domain.vo.SysStationVo;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysStationService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/sysStation")
public class SysStationController {

	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysStationService sysStationService;

	/**
	 * @Description: (查询入驻企业列表_后台 针对权限)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 上午12:55:34
	 */
	@ResponseBody
	@RequestMapping(value = "/sysStationPageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysStationPageListP(@RequestBody SysStationPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysStationPfVo>> result = sysStationService.selectSysStationPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询入驻企业列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 上午12:55:34
	 */
	@ResponseBody
	@RequestMapping(value = "/sysStationPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysStationPageList(@RequestBody SysStationPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysStationPfVo>> result = sysStationService.selectSysStationPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询入驻企业详情_后台)
	 * @param stationId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午4:31:47
	 */
	@ResponseBody
	@RequestMapping(value = "/stationDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysStationDetails(@RequestParam(value = "stationId", required = true) Integer stationId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysStationVo> result = sysStationService.selectByPrimaryKey(stationId);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (修改入驻企业_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午4:40:12
	 */
	@ResponseBody
	@RequestMapping(value = "/updateStationDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updateStationDetails(@RequestBody SysStationPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysStation station = new SysStation();
		station.setStationId(para.getStationId());
		station.setStationTitle(para.getStationTitle());
		station.setStationSimple(para.getStationSimple());
		station.setRegNumber(para.getRegNumber());
		station.setCreditCode(para.getCreditCode());
		station.setStationCompany(para.getStationCompany());
		station.setIsFictitious(para.getIsFictitious());
		station.setLossState(para.getLossState());
		station.setStationState(para.getStationState());
		station.setStationContent(para.getStationContent());
		station.setUpdateTime(newDate);
		station.setImgId(para.getImgId());
		station.setRegionId(para.getRegionId());

		ServiceResult<Object> result = sysStationService.updateStationDetails(station);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改入驻企业信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加入驻企业_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午4:44:54
	 */
	@ResponseBody
	@RequestMapping(value = "/addStationDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult addStationDetails(@RequestBody SysStationPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysStation station = new SysStation();
		Date newDate = new Date();
		station.setStationId(null);
		station.setStationTitle(para.getStationTitle());
		station.setStationSimple(para.getStationSimple());
		station.setRegNumber(para.getRegNumber());
		station.setCreditCode(para.getCreditCode());
		station.setStationCompany(para.getStationCompany());
		station.setIsFictitious(para.getIsFictitious());
		station.setLossState(para.getLossState());
		station.setStationState(para.getStationState());
		station.setStationContent(para.getStationContent());
		station.setCreateTime(newDate);
		station.setUpdateTime(station.getCreateTime());
		station.setImgId(para.getImgId());
		station.setRegionId(para.getRegionId());

		ServiceResult<Object> result = sysStationService.addStationDetails(station);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加入驻企业信息");
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
	public JsonResult delLab(@RequestParam(value = "stationId", required = true) Integer stationId,
			@RequestParam(value = "labelId", required = true) Integer labelId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysStationService.delLab(stationId, labelId);
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
	 * @Description: (删除园区)
	 * @param parkId
	 * @param labelId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午4:04:56
	 */
	@RequestMapping(value = "/delPark.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delPark(@RequestParam(value = "parkId", required = true) Integer parkId,
			@RequestParam(value = "stationId", required = true) Integer stationId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysStationService.delPark(parkId, stationId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"删除级联园区");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除覆盖产业)
	 * @param parkId
	 * @param labelId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月14日 下午4:04:56
	 */
	@RequestMapping(value = "/delIndustry.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delIndustry(@RequestParam(value = "stationId", required = true) Integer stationId,
			@RequestParam(value = "industryId", required = true) Integer industryId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysStationService.delIndustry(stationId, industryId);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"删除级联覆盖产业");
		}
		return jsonResult;
	}

	/**
	 * @Description: (置顶)
	 * @param para
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月19日 上午10:54:20
	 */
	@RequestMapping(value = "/stationTop.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult stationTop(@RequestBody SysStationPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysStation sysStation = new SysStation();
		sysStation.setStationId(para.getStationId());
		;
		sysStation.setTopState(1);
		sysStation.setTopTime(new Date());
		int result = sysStationService.updateByPrimaryKeySelective(sysStation);
		if (result < 1) {
			jsonResult.setStateCode(StateCodeConstant.ERROR_CODE);
			return jsonResult;
		} else {
			// 记录日志
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改入驻企业信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (删除客户经理)
	 * @param stationUmanagerId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月12日 下午2:08:07
	 */
	@RequestMapping(value = "/delUman.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delUman(@RequestParam(value = "stationUmanagerId", required = true) Integer stationUmanagerId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysStationService.delUman(stationUmanagerId);
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
		SysStationUmanager stationUmanager = new SysStationUmanager();
		stationUmanager.setStationId(para.getObjId());
		stationUmanager.setUserAccount(para.getUserAccount());
		ServiceResult<Object> result = sysStationService.addUManager(stationUmanager);
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
