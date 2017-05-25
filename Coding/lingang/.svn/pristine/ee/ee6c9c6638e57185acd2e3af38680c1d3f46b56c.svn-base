package com.lingang.consumer.controller.share;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.vo.SysNewsVo;
import com.lingang.api.domain.vo.SysParkVo;
import com.lingang.api.service.SysNewsService;
import com.lingang.api.service.SysParkService;

@Controller
@RequestMapping("/share")
public class ShareController {

	@Resource
	private SysParkService sysParkService;

	@Resource
	private SysNewsService sysNewsService;

	@RequestMapping(value = "/shareNewAndVideo.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView newsOrIndustry(@RequestParam(value = "typeId", required = true) Integer typeId,
			@RequestParam(value = "Id", required = true) Integer Id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		if (1 == typeId) {
			// 就是分享新闻,id变为newsId
			ServiceResult<SysNewsVo> result = sysNewsService.selectByPrimaryKeys(Id);
			if (result.getData() != null) {
				mv.addObject("data", result.getData());
				Date str = result.getData().getCreateTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				mv.setViewName("shareNews");
				mv.addObject("Data", sdf.format(str));
			} else {
				mv.setViewName("error");
			}

		} else if (2 == typeId) {
			// 就是分享产业园区的宣传视频,id变为parkId
			ServiceResult<SysParkVo> result = sysParkService.selectSysParkDetailsByStationId(Id);
			if (result.getData() != null) {
				mv.setViewName("sharePark");
				mv.addObject("data", result.getData());
			} else {
				mv.setViewName("error");
			}
		} else {
			mv.setViewName("error");
		}
		return mv;

	}

}
