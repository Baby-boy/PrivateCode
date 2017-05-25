package com.lingang.platform.controller.home;

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

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysImages;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.domain.entity.SysPartner;
import com.lingang.api.domain.entity.SysPartnerType;
import com.lingang.api.domain.para.SysPartnerPara;
import com.lingang.api.domain.pfvo.SysPartnerPfVo;
import com.lingang.api.domain.vo.SysPartnerVo;
import com.lingang.api.service.SysImagesService;
import com.lingang.api.service.SysLogService;
import com.lingang.api.service.SysPartnerService;
import com.lingang.common.util.IpAddressUtil;

/**
 *@Description: 合作信息
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月9日 上午1:06:41
 *@Version:1.0
 */
@Controller
@RequestMapping("/sysPartner")
public class SysTopPartnerController {
	
	@Resource
	private SysLogService sysLogService;
	
	@Resource
	private SysPartnerService sysPartnerService;
	@Resource
	private SysImagesService sysImagesService;
	/**
	* @Description: (最新合作（2016-12-14   gsh改）)
	* @param map
	* @return
	* @throws Exception    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月14日 上午11:31:44
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAllByPage.do",method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult queryAllByPage(@RequestBody HashMap<String, Object> map) throws Exception{
		JsonResult jsonResult=new JsonResult(); 
		ServiceResult<Page<SysPartnerPfVo>> serviceResult = sysPartnerService.queryAllByPage(map);
		serviceResult.setStateCode("1000");
		jsonResult.setData(serviceResult);
		
		return jsonResult;
	}
	/**
	* @Description: (查看最新合作详情)
	* @param partnerId
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月14日 下午6:55:13
	 */
	@ResponseBody
	@RequestMapping(value = "/topPartnerDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult sysPartnerDetails(@RequestParam(value = "partnerId", required = true) Integer partnerId) {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<SysPartnerVo> result = sysPartnerService.selectPartnerVoByPartnerId(partnerId);
		//获取缩略图
		Map<String, Object> imgMap=new HashMap<String,Object>();
		imgMap.put("minImgPath", "");
		if(result.getData() !=null && result.getData().getMinImgId() !=null){
			SysImages images=sysImagesService.selectSysImagesByImgId(result.getData().getMinImgId());
			imgMap.put("minImgPath", images.getImgPath());
		}
		jsonResult.setDataMap(imgMap);
		
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}


	/**
	* @Description: (修改合作伙伴)
	* @param para
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月14日 下午6:56:22
	 */
	@ResponseBody
	@RequestMapping(value = "/topUpdatePartnerDetails.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult updatePartnerDetails(@RequestBody SysPartnerPara para,HttpServletRequest request) {
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
	* @Description: (查看所有合作类型)
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月15日 下午4:27:32
	 */
	@ResponseBody
	@RequestMapping(value = "/getTopPartnerTypeAll.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult getSysPartnerTypeAll() {
		JsonResult jsonResult = new JsonResult();
		ServiceResult<List<SysPartnerType>> result = sysPartnerService.selectSysPartnerType();
		jsonResult.setData(result.getData());
		jsonResult.setStateCode(result.getStateCode());
		return jsonResult;
	}
	
	/**
	* @Description: (取消置顶)
	* @param para
	* @return    
	* @author gsh(15136390655@163.com)
	* @date: 2016年12月19日 下午2:45:11
	 */
	@ResponseBody
	@RequestMapping(value = "/delPartenrTop.do", method = { RequestMethod.GET, RequestMethod.POST })
	public JsonResult delPartenrTop(@RequestBody SysPartnerPara para,HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		Date newDate = new Date();
		SysPartner sysPartner = new SysPartner();
		sysPartner.setPartnerId(para.getPartnerId());
		sysPartner.setTopState(0);
		sysPartner.setUpdateTime(newDate);
		int result = sysPartnerService.updateByPrimaryKeySelective(sysPartner);
		if(result<1){
			jsonResult.setStateCode(StateCodeConstant.ERROR_CODE);
			return jsonResult;
		}else{
			// 记录日志
			HttpSession session = request.getSession();
			SysManager manager = (SysManager) session.getAttribute("manager");
			sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), newDate,
					"修改合作伙伴信息");
		}
		
		
		return jsonResult;
	}
	
}
