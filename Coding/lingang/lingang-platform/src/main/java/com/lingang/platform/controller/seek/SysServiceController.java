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
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysServiceUmanager;
import com.lingang.api.domain.entity.SysServiceWithBLOBs;
import com.lingang.api.domain.para.SysServicePara;
import com.lingang.api.domain.para.SysUmanagerPara;
import com.lingang.api.domain.pfvo.SysServicePfVo;
import com.lingang.api.domain.vo.SysServiceVo;
import com.lingang.api.service.SysImagesService;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysServiceService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/sysService")
public class SysServiceController {

	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysServiceService sysServiceService;

	@Resource
	private SysImagesService sysImagesService;

	/**
	 * @Description: (查询服务机构列表_后台 针对权限)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午10:19:15
	 */
	@ResponseBody
	@RequestMapping(value = "/sysServicePageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysServicePageListP(@RequestBody SysServicePara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysServicePfVo>> result = sysServiceService.selectServicePfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询服务机构列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午10:19:15
	 */
	@ResponseBody
	@RequestMapping(value = "/sysServicePageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysServicePageList(@RequestBody SysServicePara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysServicePfVo>> result = sysServiceService.selectServicePfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (获取服务机构详情_后台)
	 * @param serviceId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午3:53:01
	 */
	@ResponseBody
	@RequestMapping(value = "/serviceDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysServiceDetails(@RequestParam(value = "serviceId") Integer serviceId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysServiceVo> result = sysServiceService.selectServiceByServiceId(serviceId);
		// 获取缩略图
		Map<String, Object> imgMap = new HashMap<String, Object>();
		imgMap.put("minImgPath", "");
		if (result.getData() != null && result.getData().getMinImgId() != null) {
			SysImages images = sysImagesService.selectSysImagesByImgId(result.getData().getMinImgId());
			imgMap.put("minImgPath", images.getImgPath());
		}
		jsonResult.setDataMap(imgMap);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setData(result.getData());
		return jsonResult;
	}

	/**
	 * @Description: (修改服务机构_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午4:01:19
	 */
	@ResponseBody
	@RequestMapping(value = "/updateServiceDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updateServiceDetails(@RequestBody SysServicePara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysServiceWithBLOBs service = new SysServiceWithBLOBs();
		service.setServiceId(para.getServiceId());
		service.setServiceName(para.getServiceName());
		service.setServiceSimple(para.getServiceSimple());
		service.setServiceTeam(para.getServiceTeam());
		service.setServiceAddress(para.getServiceAddress());
		service.setServiceFax(para.getServiceFax());
		service.setServiceLink(para.getServiceLink());
		service.setServiceCompany(para.getServiceCompany());
		service.setServiceState(para.getServiceState());
		service.setServiceContent(para.getServiceContent());
		service.setUpdateTime(newDate);
		service.setLogoImgId(para.getLogoImgId());
		service.setMinImgId(para.getMinImgId());
		service.setBasicsId(para.getBasicsId());
		service.setRegionId(para.getRegionId());

		ServiceResult<Object> result = sysServiceService.updateServiceDetails(service);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改服务机构信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加服务机构_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午4:06:38
	 */
	@ResponseBody
	@RequestMapping(value = "/addServiceDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult addServiceDetails(@RequestBody SysServicePara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysServiceWithBLOBs service = new SysServiceWithBLOBs();
		service.setServiceId(null);
		service.setServiceName(para.getServiceName());
		service.setServiceSimple(para.getServiceSimple());
		service.setServiceTeam(para.getServiceTeam());
		service.setServiceAddress(para.getServiceAddress());
		service.setServiceFax(para.getServiceFax());
		service.setServiceLink(para.getServiceLink());
		service.setServiceCompany(para.getServiceCompany());
		service.setServiceState(para.getServiceState());
		service.setServiceContent(para.getServiceContent());
		service.setCreateTime(newDate);
		service.setUpdateTime(service.getCreateTime());
		service.setLogoImgId(para.getLogoImgId());
		service.setMinImgId(para.getMinImgId());
		service.setBasicsId(para.getBasicsId());
		service.setRegionId(para.getRegionId());

		ServiceResult<Object> result = sysServiceService.addServiceDetails(service);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改服务机构信息");
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
	public JsonResult delLab(@RequestParam(value = "serviceId", required = true) Integer serviceId,
			@RequestParam(value = "labelId", required = true) Integer labelId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysServiceService.delLab(serviceId, labelId);
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
			@RequestParam(value = "serviceId", required = true) Integer serviceId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysServiceService.delPark(parkId, serviceId);
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
	 * @Description: (删除客户经理)
	 * @param partnerUmanagerId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月12日 下午2:08:07
	 */
	@RequestMapping(value = "/delUman.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delUman(@RequestParam(value = "serviceUmanagerId", required = true) Integer serviceUmanagerId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysServiceService.delUman(serviceUmanagerId);
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
		SysServiceUmanager serviceUmanager = new SysServiceUmanager();
		serviceUmanager.setServiceId(para.getObjId());
		serviceUmanager.setUserAccount(para.getUserAccount());
		ServiceResult<Object> result = sysServiceService.addUManager(serviceUmanager);
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
