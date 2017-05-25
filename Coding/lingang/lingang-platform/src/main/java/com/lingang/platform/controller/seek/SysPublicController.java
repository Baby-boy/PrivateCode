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
import com.lingang.api.domain.entity.SysPublic;
import com.lingang.api.domain.entity.SysPublicUmanager;
import com.lingang.api.domain.para.SysPublicPara;
import com.lingang.api.domain.para.SysUmanagerPara;
import com.lingang.api.domain.pfvo.SysPublicPfVo;
import com.lingang.api.domain.vo.SysPublicVo;
import com.lingang.api.service.SysImagesService;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysPublicService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/sysPublic")
public class SysPublicController {

	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysPublicService sysPublicService;

	@Resource
	private SysImagesService sysImagesService;

	/**
	 * @Description: (查询公共平台列表_后台 针对权限)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 上午2:45:59
	 */
	@ResponseBody
	@RequestMapping(value = "/sysPublicPageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysPublicPageListP(@RequestBody SysPublicPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysPublicPfVo>> result = sysPublicService.selectSysPublicPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询公共平台列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 上午2:45:59
	 */
	@ResponseBody
	@RequestMapping(value = "/sysPublicPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysPublicPageList(@RequestBody SysPublicPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysPublicPfVo>> result = sysPublicService.selectSysPublicPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (获取公共平台详情_后台)
	 * @param publicId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午3:18:05
	 */
	@ResponseBody
	@RequestMapping(value = "/publicDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysPublicDetails(@RequestParam(value = "publicId", required = true) Integer publicId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysPublicVo> result = sysPublicService.selectSysPublicDetails(publicId);
		// 补充缩略图
		Map<String, Object> imgMap = new HashMap<String, Object>();
		imgMap.put("minImgPath", "");
		if (result.getData() != null && result.getData().getMinImgId() != null) {
			SysImages images = sysImagesService.selectSysImagesByImgId(result.getData().getMinImgId());
			imgMap.put("minImgPath", images.getImgPath());
		}
		jsonResult.setDataMap(imgMap);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (更新公共平台信息_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午3:27:23
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePublicDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updatePublicDetails(@RequestBody SysPublicPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		SysPublic sysPublic = new SysPublic();
		Date newDate = new Date();
		sysPublic.setPublicId(para.getPublicId());
		sysPublic.setPublicTitle(para.getPublicTitle());
		sysPublic.setPublicAddress(para.getPublicAddress());
		sysPublic.setPublicCompany(para.getPublicCompany());
		sysPublic.setPublicFax(para.getPublicFax());
		sysPublic.setPublicLink(para.getPublicLink());
		sysPublic.setPublicState(para.getPublicState());
		sysPublic.setPublicContent(para.getPublicContent());
		sysPublic.setUpdateTime(newDate);
		sysPublic.setMaxImgId(para.getMaxImgId());
		sysPublic.setMinImgId(para.getMinImgId());
		sysPublic.setBasicsId(para.getBasicsId());
		sysPublic.setRegionId(para.getRegionId());

		ServiceResult<Object> result = sysPublicService.updatePublicDetails(sysPublic);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改公共平台信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加公共平台_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月10日 下午3:33:00
	 */
	@ResponseBody
	@RequestMapping(value = "/addPublicDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult addPublicDetails(@RequestBody SysPublicPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysPublic sysPublic = new SysPublic();
		sysPublic.setPublicId(null);
		sysPublic.setPublicTitle(para.getPublicTitle());
		sysPublic.setPublicAddress(para.getPublicAddress());
		sysPublic.setPublicCompany(para.getPublicCompany());
		sysPublic.setPublicFax(para.getPublicFax());
		sysPublic.setPublicLink(para.getPublicLink());
		sysPublic.setPublicState(para.getPublicState());
		sysPublic.setPublicContent(para.getPublicContent());
		sysPublic.setCreateTime(newDate);
		sysPublic.setUpdateTime(sysPublic.getCreateTime());
		sysPublic.setMaxImgId(para.getMaxImgId());
		sysPublic.setMinImgId(para.getMinImgId());
		sysPublic.setBasicsId(para.getBasicsId());
		sysPublic.setRegionId(para.getRegionId());

		ServiceResult<Object> result = sysPublicService.addPublicDetails(sysPublic);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加公共平台信息");
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
	public JsonResult delLab(@RequestParam(value = "publicId", required = true) Integer publicId,
			@RequestParam(value = "labelId", required = true) Integer labelId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysPublicService.delLab(publicId, labelId);
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
			@RequestParam(value = "publicId", required = true) Integer publicId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysPublicService.delPark(publicId);
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
	 * @param publicUmanagerId
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月12日 下午2:08:07
	 */
	@RequestMapping(value = "/delUman.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult delUman(@RequestParam(value = "publicUmanagerId", required = true) Integer publicUmanagerId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysPublicService.delUman(publicUmanagerId);
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
		SysPublicUmanager publicUmanager = new SysPublicUmanager();
		publicUmanager.setPublicId(para.getObjId());
		publicUmanager.setUserAccount(para.getUserAccount());
		ServiceResult<Object> result = sysPublicService.addUManager(publicUmanager);
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
