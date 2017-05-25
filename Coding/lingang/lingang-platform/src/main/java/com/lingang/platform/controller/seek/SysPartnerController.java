package com.lingang.platform.controller.seek;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.alibaba.fastjson.JSONObject;
import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysPartner;
import com.lingang.api.domain.entity.SysPartnerType;
import com.lingang.api.domain.entity.SysPartnerUmanager;
import com.lingang.api.domain.para.SysPartnerPara;
import com.lingang.api.domain.para.SysUmanagerPara;
import com.lingang.api.domain.pfvo.SysPartnerPfVo;
import com.lingang.api.domain.vo.SysPartnerVo;
import com.lingang.api.domain.vo.SysUserData;
import com.lingang.api.service.SysImagesService;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysPartnerService;
import com.lingang.common.ad.HttpURLConnectionResult;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/sysPartner")
public class SysPartnerController {

	@Resource
	private SysLogService sysLogService;

	@Resource
	private SysPartnerService sysPartnerService;

	@Resource
	private SysImagesService sysImagesService;

	/**
	 * @Description: (查询合作伙伴列表_后台 合作伙伴)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午4:11:23
	 */
	@ResponseBody
	@RequestMapping(value = "/sysPartnerPageListP.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysPartnerPageListP(@RequestBody SysPartnerPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysPartnerPfVo>> result = sysPartnerService.selectSysPartnerPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询合作伙伴列表_后台)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月8日 下午4:11:23
	 */
	@ResponseBody
	@RequestMapping(value = "/sysPartnerPageList.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysPartnerPageList(@RequestBody SysPartnerPara para) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SysPartnerPfVo>> result = sysPartnerService.selectSysPartnerPfVoPageList(para);
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (查询合作伙伴详情)
	 * @param partnerId
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 下午8:11:52
	 */
	@ResponseBody
	@RequestMapping(value = "/partnerDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysPartnerDetails(@RequestParam(value = "partnerId", required = true) Integer partnerId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysPartnerVo> result = sysPartnerService.selectPartnerVoByPartnerId(partnerId);
		// 获取缩略图
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
	 * @Description: (查询所有合作类型_后台)
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月14日 上午10:07:08
	 */
	@ResponseBody
	@RequestMapping(value = "/getSysPartnerTypeAll.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult getSysPartnerTypeAll() {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysPartnerType>> result = sysPartnerService.selectSysPartnerType();
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}

	/**
	 * @Description: (修改合作伙伴)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 下午8:33:29
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePartnerDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updatePartnerDetails(@RequestBody SysPartnerPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysPartner partner = new SysPartner();
		partner.setPartnerId(para.getPartnerId());
		partner.setPartnerName(para.getPartnerName());
		partner.setPartnerCompany(para.getPartnerCompany());
		partner.setPartnerSimple(para.getPartnerSimple());
		partner.setPartnerState(para.getPartnerState());
		partner.setPartnerContent(para.getPartnerContent());
		partner.setUpdateTime(newDate);
		partner.setLogoImgId(para.getLogoImgId());
		partner.setImgId(para.getImgId());
		partner.setBasicsId(para.getBasicsId());
		partner.setTypeId(para.getTypeId());
		partner.setSignTime(para.getSignTime());

		ServiceResult<Object> result = sysPartnerService.updatePartnerDetails(partner);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改合作伙伴信息");
		}
		return jsonResult;
	}

	/**
	 * @Description: (添加合作伙伴)
	 * @param para
	 * @return 参数注释
	 * @author fqh (qihao.fu@outlook.com)
	 * @date: 2016年12月9日 下午9:19:47
	 */
	@ResponseBody
	@RequestMapping(value = "/addPartnerDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult addPartnerDetails(@RequestBody SysPartnerPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysPartner partner = new SysPartner();
		partner.setPartnerId(null);
		partner.setPartnerName(para.getPartnerName());
		partner.setPartnerCompany(para.getPartnerCompany());
		partner.setPartnerSimple(para.getPartnerSimple());
		partner.setPartnerState(para.getPartnerState());
		partner.setPartnerContent(para.getPartnerContent());
		partner.setCreateTime(newDate);
		partner.setUpdateTime(partner.getCreateTime());
		partner.setLogoImgId(para.getLogoImgId());
		partner.setImgId(para.getImgId());
		partner.setBasicsId(para.getBasicsId());
		partner.setTypeId(para.getTypeId());
		partner.setSignTime(para.getSignTime());

		ServiceResult<Object> result = sysPartnerService.addPartnerDetails(partner);
		jsonResult.setStateCode(result.getStateCode());
		jsonResult.setMessage(result.getMessage());
		jsonResult.setData(result.getData());

		// 记录日志
		if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"添加合作伙伴信息");
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
	public JsonResult delLab(@RequestParam(value = "partnerId", required = true) Integer partnerId,
			@RequestParam(value = "labelId", required = true) Integer labelId, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysPartnerService.delLab(partnerId, labelId);
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
	 * @Description: (置顶)
	 * @param para
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月19日 上午10:20:50
	 */
	@RequestMapping(value = "/partenerTop.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public JsonResult partenerTop(@RequestBody SysPartnerPara para, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysPartner sysPartner = new SysPartner();
		sysPartner.setPartnerId(para.getPartnerId());
		sysPartner.setTopState(1);
		sysPartner.setTopTime(new Date());
		int result = sysPartnerService.updateByPrimaryKeySelective(sysPartner);
		if (result < 1) {
			jsonResult.setStateCode(StateCodeConstant.ERROR_CODE);
			return jsonResult;
		} else {
			// 记录日志
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改合作伙伴信息");
		}

		return jsonResult;
	}

	/**
	 * @Description: (AD域用户查询)
	 * @param checkStr
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2017年1月11日 下午3:33:57
	 */
	@ResponseBody
	@RequestMapping(value = "/seleceAdUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult seleceAdUser(@RequestBody String checkStr) {
		JsonResult jsonResult = new JsonResult();
		checkStr = checkStr.substring(1, checkStr.length() - 1);
		String resultStr = HttpURLConnectionResult.resultStrCheckUser(checkStr);
		resultStr = resultStr.replace("CN=", "");
		SysUserData data = new SysUserData();
		if (!resultStr.equals("")) {
			data = JSONObject.parseObject(resultStr, SysUserData.class);
		}
		jsonResult.setStateCode(StateCodeConstant.SUCCESS_CODE);
		jsonResult.setData(data);
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
	public JsonResult delUman(@RequestParam(value = "partnerUmanagerId", required = true) Integer partnerUmanagerId,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		ServiceResult<Object> result = sysPartnerService.delUman(partnerUmanagerId);
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
		SysPartnerUmanager partnerUmanager = new SysPartnerUmanager();
		partnerUmanager.setPartnerId(para.getObjId());
		partnerUmanager.setUserAccount(para.getUserAccount());
		ServiceResult<Object> result = sysPartnerService.addUManager(partnerUmanager);
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
