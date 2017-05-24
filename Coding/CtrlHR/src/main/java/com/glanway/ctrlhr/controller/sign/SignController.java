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

import com.glanway.ctrlhr.entity.para.SignPara;
import com.glanway.ctrlhr.entity.vo.SignVo;
import com.glanway.ctrlhr.service.sign.SignService;
import com.glanway.ctrlhr.util.TimeUtil;

@Controller
@RequestMapping("/api/sign")
public class SignController {

	@Autowired
	private SignService signService;

	/**
	 * 说明 : 考勤记录的导出
	 * 
	 * @param para
	 * @param response
	 * @author 付其浩
	 * @dateTime 2017年4月24日 上午11:59:27
	 */
	@RequestMapping(value = "export")
	public void list(SignPara para, HttpServletResponse response) {

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
			List<SignVo> list = signService.findMany(para);

			response.setHeader("Content-Disposition", "attachment;filename=SignRecordDetail.csv");

			// response.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("GB2312");
			bw = new BufferedWriter(response.getWriter());
			if (null != list && list.size() > 0) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("员工姓名").append(",");
				buffer.append("员工编码").append(",");
				buffer.append("部门名称").append(",");
				buffer.append("职位名称").append(",");
				buffer.append("考勤时间").append(",");
				// bw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,
				// (byte) 0xBF }));
				bw.write(buffer.deleteCharAt(buffer.length() - 1).toString());
				bw.newLine();
				for (SignVo signVo : list) {
					writeLine(getCvsList(signVo), bw);
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

	private List<String> getCvsList(SignVo signVo) {
		List<String> cvsList = new ArrayList<>();
		cvsList.add(signVo.getEmployeeName());
		cvsList.add(signVo.getEmployeeCode());
		cvsList.add(signVo.getDeptName());
		cvsList.add(signVo.getJobName());
		if (null == signVo.getTime()) {
			cvsList.add(null);
		} else {
			cvsList.add(TimeUtil.format(signVo.getTime()));
		}

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
