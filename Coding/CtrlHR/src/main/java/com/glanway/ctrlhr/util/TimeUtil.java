package com.glanway.ctrlhr.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tianxuan
 * @Time 2016/4/19
 */
public class TimeUtil {

	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String FORMAT_YYMMDDHHMMSS = "yyMMddhhmmss";
	public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String START_TIME = " 00:00:00";
	public static final String END_TIME = " 23:59:59";

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 *            需要转换的时间
	 * @return Date
	 */
	public static Date StrToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
	}

	/**
	 * 将时间格式化为 yyyy-mm-dd hh:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		String rtStr = "";
		if (null != date) {
			SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
			rtStr = formatter.format(date);
		}
		return rtStr;
	}

	/**
	 * 将时间格式化为 yyyy-mm-dd hh:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date, String formatStr) {
		String rtStr = "";
		if (null != date) {
			SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
			rtStr = formatter.format(date);
		}
		return rtStr;
	}

	private static String formatToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 0);
		return format(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 说明 : 获取一天的开始时间
	 * 
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月26日 下午9:15:48
	 */
	public static String getDateStart() {
		return formatToday() + START_TIME;
	}

	/**
	 * 说明 : 获取一天的结束时间
	 * 
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月26日 下午9:16:34
	 */
	public static String getDateEnd() {
		return formatToday() + END_TIME;
	}

	/**
	 * 说明 : 当月第一天
	 * 
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月27日 下午7:52:07
	 */
	public static String getMonthStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return format(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 说明 : 当月最后一天
	 * 
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月27日 下午7:52:24
	 */
	public static String getMonthEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 说明 : 当月第一天的起始时间
	 * 
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月27日 下午8:00:30
	 */
	public static String getMonthTimeStart() {
		return getMonthStart() + START_TIME;
	}

	/**
	 * 说明 : 当月最后一天的结束时间
	 * 
	 * @return
	 * @author 付其浩
	 * @dateTime 2017年4月27日 下午8:00:30
	 */
	public static String getMonthTimeEnd() {
		return getMonthEnd() + END_TIME;
	}
}
