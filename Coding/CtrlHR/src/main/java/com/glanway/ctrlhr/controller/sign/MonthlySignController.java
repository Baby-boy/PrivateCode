package com.glanway.ctrlhr.controller.sign;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glanway.ctrlhr.common.HttpCode;
import com.glanway.ctrlhr.common.JsonResult;
import com.glanway.ctrlhr.common.Page;
import com.glanway.ctrlhr.entity.para.MonthlySignPara;
import com.glanway.ctrlhr.entity.vo.MonthlySignVo;
import com.glanway.ctrlhr.service.sign.MonthlySignService;
import com.glanway.ctrlhr.util.TimeUtil;

/**
 * 说明 : 月考勤记录
 * 
 * @author 付其浩
 * @version 1.0.0
 * @dateTime 2017年4月18日 下午5:11:28
 */
@Controller
@RequestMapping("/api/monthlySign")
public class MonthlySignController {

	@Autowired
	private MonthlySignService monthlySignService;

	/**
	 * 说明 : 查询月考勤记录列表
	 * 
	 * @param para
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月18日 下午5:36:54
	 */
	@ResponseBody
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public JsonResult list(MonthlySignPara para) {
		JsonResult jsonResult = new JsonResult();

		if (StringUtils.isEmpty(para.getDateStart())) {
			para.setDateStart(TimeUtil.getMonthTimeStart());
		} else {
			if (13 != para.getDateStart().length()) {
				para.setDateStart(TimeUtil.getMonthTimeStart());
			}else {
				para.setDateStart(TimeUtil.format(new Date(Long.valueOf(para.getDateStart()))));
			}
		}

		if (StringUtils.isEmpty(para.getDateEnd())) {
			para.setDateEnd(TimeUtil.getMonthTimeEnd());
		} else {
			if (13 != para.getDateEnd().length()) {
				para.setDateEnd(TimeUtil.getMonthTimeEnd());
			}else {
				para.setDateEnd(TimeUtil.format(new Date(Long.valueOf(para.getDateEnd()))));
			}
		}

		try {
			Page<MonthlySignVo> page = monthlySignService.findList(para);
			jsonResult.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(HttpCode.INTERNAL_SERVER_ERROR);
			jsonResult.setMsg("操作失败!");
		}

		return jsonResult;
	}

	/**
	 * 说明 : 导出的月考勤记录
	 * 
	 * @param para
	 * @param response
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月19日 下午12:39:11
	 */
	@RequestMapping(value = "export")
	public void list(MonthlySignPara para, HttpServletResponse response) {

		if (StringUtils.isEmpty(para.getDateStart())) {
			para.setDateStart(TimeUtil.getMonthTimeStart());
		} else {
			if (13 != para.getDateStart().length()) {
				para.setDateStart(TimeUtil.getMonthTimeStart());
			}else {
				para.setDateStart(TimeUtil.format(new Date(Long.valueOf(para.getDateStart()))));
			}
		}

		if (StringUtils.isEmpty(para.getDateEnd())) {
			para.setDateEnd(TimeUtil.getMonthTimeEnd());
		} else {
			if (13 != para.getDateEnd().length()) {
				para.setDateEnd(TimeUtil.getMonthTimeEnd());
			}else {
				para.setDateEnd(TimeUtil.format(new Date(Long.valueOf(para.getDateEnd()))));
			}
		}

		BufferedWriter bw = null;
		try {
			// 获取考勤记录的列表
			List<MonthlySignVo> list = monthlySignService.findMany(para);

			response.setHeader("Content-Disposition", "attachment;filename=SignRecord.csv");

			response.setCharacterEncoding("GB2312");
			bw = new BufferedWriter(response.getWriter());
			if (null != list && list.size() > 0) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("员工姓名").append(",");
				buffer.append("员工编码").append(",");
				buffer.append("部门名称").append(",");
				buffer.append("职位名称").append(",");
				buffer.append("考勤开始时间").append(",");
				buffer.append("考勤结束时间").append(",");
				buffer.append("考勤天数").append(",");
				buffer.append("考勤总时间").append(",");
				// bw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,
				// (byte) 0xBF }));
				bw.write(buffer.deleteCharAt(buffer.length() - 1).toString());
				bw.newLine();
				for (MonthlySignVo monthlySignVo : list) {
					writeLine(getCvsList(monthlySignVo), bw);
				}
			}
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != bw) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<String> getCvsList(MonthlySignVo monthlySignVo) {
		List<String> cvsList = new ArrayList<>();
		cvsList.add(monthlySignVo.getEmployeeName());
		cvsList.add(monthlySignVo.getEmployeeCode());
		cvsList.add(monthlySignVo.getDeptName());
		cvsList.add(monthlySignVo.getJobName());
		if (null == monthlySignVo.getDateFrom()) {
			cvsList.add(null);
		} else {
			cvsList.add(TimeUtil.format(monthlySignVo.getDateFrom()));
		}
		if (null == monthlySignVo.getDateTo()) {
			cvsList.add(null);
		} else {
			cvsList.add(TimeUtil.format(monthlySignVo.getDateTo()));
		}
		cvsList.add(monthlySignVo.getDays().toString());
		cvsList.add(monthlySignVo.getHours().toString());
		return cvsList;
	}

	private void writeLine(final List<String> cvsList, BufferedWriter bw) throws IOException {
		StringBuffer buffer = new StringBuffer();
		for (String line : cvsList) {
			if (null == line) {
				line = "";
			}
			buffer.append(line).append(",");
		}

		// bw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte)
		// 0xBF }));
		bw.write(buffer.deleteCharAt(buffer.length() - 1).toString());
		bw.newLine();
	}
}
