package com.yd.gcj.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.gcj.entity.YdMangerCount;
import com.yd.gcj.system.service.YdMangerServiceSystemCount;

/**
 * description(项目任务所对应的合同信息管理)
 * 
 * @author Administrator
 * @param <HttpServletRequest>
 */
@Controller
@RequestMapping("/system")
public class YdMangerControllerSystemCount<HttpServletRequest> {

	@Autowired
	private YdMangerServiceSystemCount ydMangerServiceSystemCount;

	/**
	 * description(查询当天的会员新增数)
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllPerson")
	public String queryAllInfomation(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		// 获取当前月份
		int month = cal_1.get(Calendar.MONTH) + 1;
		int year = cal_1.get(Calendar.YEAR);
		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = sdf.format(cal_1.getTime());
		Date monthFirstDay = null;
		try {
			monthFirstDay = sdf.parse(firstDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = sdf.format(ca.getTime());
		Date monthLastDay = null;
		try {
			monthLastDay = sdf.parse(last);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<YdMangerCount> countList = ydMangerServiceSystemCount.queryAllInfomation(monthFirstDay, monthLastDay);
		model.addAttribute("countList", countList);
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		return "system/datastatistics/personchange";
	}

	/**
	 * description(查询当天的会员充值总金额)
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllCapital")
	public String queryAllCapital(Model model) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		int month = cal_1.get(Calendar.MONTH) + 1;
		int year = cal_1.get(Calendar.YEAR);
		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = sdf.format(cal_1.getTime());
		Date monthFirstDay = null;
		try {
			monthFirstDay = sdf.parse(firstDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = sdf.format(ca.getTime());
		Date monthLastDay = null;
		try {
			monthLastDay = sdf.parse(last);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<YdMangerCount> countList = ydMangerServiceSystemCount.queryAllInfomation(monthFirstDay, monthLastDay);
		model.addAttribute("countList", countList);
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		return "system/datastatistics/capital";
	}

	/**
	 * description(根据count_date查询当前月的数据金额统计)
	 * 
	 * @param
	 * @param month
	 * @return
	 */
	@RequestMapping("/queryCountByCountDate")
	public String queryCountByCountDate(Integer month, Integer year, Model model) {
		// 用来表示本月的第一天
		String firstDay = "01";
		// Calendar calendar = Calendar.getInstance();
		// int year = calendar.get(Calendar.YEAR);
		// 获取当前年份
		// int nowYear= calendar.get(Calendar.YEAR);
		// 获取传入年份
		int nowYear = year;
		String firstTime = null;
		if (month >= 10) {
			firstTime = nowYear + "" + month + "" + firstDay;
		} else {
			firstTime = nowYear + "" + "0" + month + "" + firstDay;
		}
		int parseFirstTime = Integer.parseInt(firstTime);
		Integer endDay = null;
		// 设置传递过来月份的最后一天
		switch (month) {
		case 1:
			endDay = 31;
			break;
		case 2:
			if (nowYear % 4 == 0 && nowYear % 100 != 0 || nowYear % 400 == 0) {
				// 是闰年
				endDay = 29;
			} else {
				endDay = 28;
			}
			break;
		case 3:
			endDay = 31;
			break;
		case 4:
			endDay = 30;
			break;
		case 5:
			endDay = 31;
			break;
		case 6:
			endDay = 30;
			break;
		case 7:
			endDay = 31;
			break;
		case 8:
			endDay = 31;
			break;
		case 9:
			endDay = 30;
			break;
		case 10:
			endDay = 31;
			break;
		case 11:
			endDay = 30;
			break;
		case 12:
			endDay = 31;
			break;

		default:
			break;
		}
		String lastTime = null;
		if (month >= 10 && endDay >= 10) {
			lastTime = nowYear + "" + month + "" + endDay;
		} else if (month < 10 && endDay < 10) {
			lastTime = nowYear + "" + "0" + month + "" + "0" + endDay;
		} else if (month >= 10 && endDay < 10) {
			lastTime = nowYear + "" + month + "" + "0" + endDay;
		} else if (month < 10 && endDay >= 10) {
			lastTime = nowYear + "" + "0" + month + "" + endDay;
		}
		int parseLastTime = Integer.parseInt(lastTime);
		List<YdMangerCount> countList = ydMangerServiceSystemCount.queryAllInfomationByCountNum(parseFirstTime,
				parseLastTime);
		model.addAttribute("countList", countList);
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		return "system/datastatistics/capital";
	}

	/**
	 * description(根据count_date查询当前月的数据人数统计)
	 * 
	 * @param
	 * @param month
	 * @return
	 */
	@RequestMapping("/queryPersonByCountDate")
	public String queryPersonByCountDate(Integer month, Integer year, Model model) {
		// 用来表示本月的第一天
		String firstDay = "01";
		// Calendar calendar = Calendar.getInstance();
		// int year = calendar.get(Calendar.YEAR);
		// 获取当前年份
		// int nowYear= calendar.get(Calendar.YEAR);
		// 获取传入年份
		int nowYear = year;
		String firstTime = null;
		if (month >= 10) {
			firstTime = nowYear + "" + month + "" + firstDay;
		} else {
			firstTime = nowYear + "" + "0" + month + "" + firstDay;
		}
		int parseFirstTime = Integer.parseInt(firstTime);
		/*
		 * Date parseFirstTime=null; try { parseFirstTime =
		 * sdf.parse(firstTime); } catch (ParseException e) {
		 * e.printStackTrace(); }
		 */

		Integer endDay = null;
		// 设置传递过来月份的最后一天
		switch (month) {
		case 1:
			endDay = 31;
			break;
		case 2:
			if (nowYear % 4 == 0 && nowYear % 100 != 0 || nowYear % 400 == 0) {
				// 是闰年
				endDay = 29;
			} else {
				endDay = 28;
			}
			break;
		case 3:
			endDay = 31;
			break;
		case 4:
			endDay = 30;
			break;
		case 5:
			endDay = 31;
			break;
		case 6:
			endDay = 30;
			break;
		case 7:
			endDay = 31;
			break;
		case 8:
			endDay = 31;
			break;
		case 9:
			endDay = 30;
			break;
		case 10:
			endDay = 31;
			break;
		case 11:
			endDay = 30;
			break;
		case 12:
			endDay = 31;
			break;

		default:
			break;
		}
		String lastTime = null;
		if (month >= 10 && endDay >= 10) {
			lastTime = nowYear + "" + month + "" + endDay;
		} else if (month < 10 && endDay < 10) {
			lastTime = nowYear + "" + "0" + month + "" + "0" + endDay;
		} else if (month >= 10 && endDay < 10) {
			lastTime = nowYear + "" + month + "" + "0" + endDay;
		} else if (month < 10 && endDay >= 10) {
			lastTime = nowYear + "" + "0" + month + "" + endDay;
		}
		int parseLastTime = Integer.parseInt(lastTime);
		/*
		 * Date parseLastTime=null; try { parseLastTime = sdf.parse(lastTime); }
		 * catch (ParseException e) { e.printStackTrace(); }
		 */
		List<YdMangerCount> countList = ydMangerServiceSystemCount.queryAllInfomationByCountNum(parseFirstTime,
				parseLastTime);
		model.addAttribute("countList", countList);
		model.addAttribute("month", month);
		model.addAttribute("year", year);
		return "system/datastatistics/personchange";
	}
}
