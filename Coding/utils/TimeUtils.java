package com.glanway.util;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类.
 *
 * @author fuqihao
 * @version 1.0
 * @date 2018年1月1日
 */
public abstract class TimeUtils {

	/** 日期格式 yyyy(年) */
	public static final String YYYY = "yyyy";
	/** 日期格式 MM(月) */
	public static final String MM = "MM";
	/** 日期格式 MM(日) */
	public static final String DD = "dd";
	/** 日期格式yyyy-MM(年月) */
	public static final String YYYY_MM = "yyyy-MM";
	/** 日期格式MM-dd(月日) */
	public static final String MM_DD = "MM-dd";
	/** 日期格式 yyyy-MM-dd(年月日) */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	/** 日期格式 HH(时) */
	public static final String TIME_HH = "HH";
	/** 日期格式 mm(分) */
	public static final String TIME_MM = "mm";
	/** 日期格式 ss(秒) */
	public static final String TIME_SS = "ss";
	/** 日期格式 HH:mm(时分) */
	public static final String TIME_HH_MM = "HH:mm";
	/** 日期格式 HH:mm:ss(时分秒) */
	public static final String TIME_HH_MM_SS = "HH:mm:ss";
	/** 日期格式 yyyy-MM-dd HH:mm */
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	/** 日期格式 yyyy-MM-dd HH:mm:ss */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/** 每天开始时间字符串 */
	public static final String START_TIME = " 00:00:00";
	/** 每天结束时间字符串 */
	public static final String END_TIME = " 23:59:59";
	/** 星期一 */
	public static final String MONDAY = "星期一";
	/** 星期二 */
	public static final String TUESDAY = "星期二";
	/** 星期三 */
	public static final String WEDNESDAY = "星期三";
	/** 星期四 */
	public static final String THURSDAY = "星期四";
	/** 星期五 */
	public static final String FRIDAY = "星期五";
	/** 星期六 */
	public static final String SATURDAY = "星期六";
	/** 星期日 */
	public static final String SUNDAY = "星期日";

	/**
	 * 将字符串日期转换成日期,日期规则为 yyyy-MM-dd HH:mm:ss.
	 *
	 * @param strDate(需要转换的日期)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date strToDateLong(final String strDate) {
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		ParsePosition pos = new ParsePosition(0);
		return dateFormat.parse(strDate, pos);
	}

	/**
	 * 将字符串日期转换成日期,日期规则为 yyyy-MM-dd.
	 *
	 * @param strDate(需要转换的日期)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date strToDate(final String strDate) {
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
		ParsePosition pos = new ParsePosition(0);
		return dateFormat.parse(strDate, pos);
	}

	/**
	 * 将字符串日期转换成日期,日期规则为自定义规则.
	 *
	 * @param strDate(需要转换的日期)
	 * @param pattern(时间格式规则)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date strToDatePattern(final String strDate, final String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		ParsePosition pos = new ParsePosition(0);
		return dateFormat.parse(strDate, pos);
	}

	/**
	 * 将日期格式化成日期字符串,日期规则为 yyyy-MM-dd HH:mm:ss.
	 *
	 * @param date(需要格式化的时间)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String formatLong(final Date date) {
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(date);
	}

	/**
	 * 将日期格式化成日期字符串,日期规则为 yyyy-MM-dd.
	 *
	 * @param date(需要格式化的时间)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String format(final Date date) {
		DateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD);
		return dateFormat.format(date);
	}

	/**
	 * 将日期格式化成日期字符串,日期规则为自定义规则.
	 *
	 * @param date(需要格式化的时间)
	 * @param pattern(时间格式规则)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String formatPattern(final Date date, final String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * 获取日历类的当前时间.
	 *
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}

	/**
	 * 获取前几天或者后几天的当前时间.
	 *
	 * @param date(传入的时间)
	 * @param day(正数代表后几天,负数代表前几天,0代表当天)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getToday(final Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getToday());
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	/**
	 * 获取传入时间的前几天或者后几天的当前时间.
	 *
	 * @param date(传入的时间)
	 * @param day(正数代表后几天,负数代表前几天,0代表当天)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getToday(final Date date, final Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	/**
	 * 获取传入时间的当前时间.
	 *
	 * @param year(传入的年)
	 * @param month(传入的月)
	 * @param day(传入的天)
	 * @return 当前时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getToday(Integer year, Integer month, Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当天日期开始时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getDayStart() {
		return format(getToday()) + START_TIME;
	}

	/**
	 * 获取前几天或者后几天的日期开始时间.
	 *
	 * @param date(传入的时间)
	 * @param day(正数代表后几天,负数代表前几天,0代表当天)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getDayStart(final Integer day) {
		return format(getToday(day)) + START_TIME;
	}

	/**
	 * 获取传入时间的前几天或者后几天的日期开始时间.
	 *
	 * @param date(传入的时间)
	 * @param day(正数代表后几天,负数代表前几天,0代表当天)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getDayStart(final Date date, final Integer day) {
		return format(getToday(date, day)) + START_TIME;
	}

	/**
	 * 获取日历类的当天日期结束时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getDayEnd() {
		return format(getToday()) + END_TIME;
	}

	/**
	 * 获取前几天或者后几天的日期结束时间.
	 *
	 * @param date(传入的时间)
	 * @param day(正数代表后几天,负数代表前几天,0代表当天)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getDayEnd(final Integer day) {
		return format(getToday(day)) + END_TIME;
	}

	/**
	 * 获取传入时间的前几天或者后几天的日期结束时间.
	 *
	 * @param date(传入的时间)
	 * @param day(正数代表后几天,负数代表前几天,0代表当天)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getDayEnd(final Date date, final Integer day) {
		return format(getToday(date, day)) + END_TIME;
	}

	/**
	 * 获取日历类的当月第一天的当前时间.
	 *
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当月的前几个月或者后几个月的第一天的当前时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getMonthFirstDay(final Integer month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当月最后一天的当前时间.
	 *
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当月的前几个月或者后几个月的最后一天的当前时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getMonthLastDay(final Integer month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取当月第一天的开始时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getMonthFirstDayStart() {
		return format(getMonthFirstDay()) + START_TIME;
	}

	/**
	 * 获取当月第一天的结束时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getMonthFirstDayEnd() {
		return format(getMonthFirstDay()) + END_TIME;
	}

	/**
	 * 获取当月的前几个月或者后几个月的第一天的开始时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getMonthFirstDayStart(final Integer month) {
		return format(getMonthFirstDay(month)) + START_TIME;
	}

	/**
	 * 获取当月的前几个月或者后几个月的第一天的结束时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getMonthFirstDayEnd(final Integer month) {
		return format(getMonthFirstDay(month)) + END_TIME;
	}

	/**
	 * 获取当月最后一天的开始时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getMonthLastDayStart() {
		return format(getMonthLastDay()) + START_TIME;
	}

	/**
	 * 获取当月最后一天的结束时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getMonthLastDayEnd() {
		return format(getMonthLastDay()) + END_TIME;
	}

	/**
	 * 获取当月的前几个月或者后几个月的最后一天的开始时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getMonthLastDayStart(final Integer month) {
		return format(getMonthLastDay(month)) + START_TIME;
	}

	/**
	 * 获取当月的前几个月或者后几个月的最后一天的结束时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getMonthLastDayEnd(final Integer month) {
		return format(getMonthLastDay(month)) + END_TIME;
	}

	/**
	 * 获取日历类的当年第一天的当前时间.
	 *
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getYearFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当年的前几个年或者后几个年的第一天的当前时间.
	 *
	 * @param month(正数代表后几年,负数代表前几年,0代表当年)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getYearFirstDay(final Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当年最后一天的当前时间.
	 *
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getYearLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当年的前几个年或者后几个年的最后一天的当前时间.
	 *
	 * @param month(正数代表后几年,负数代表前几年,0代表当年)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getYearLastDay(final Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, year + 1);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return calendar.getTime();
	}

	/**
	 * 获取当年第一天的开始时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getYearFirstDayStart() {
		return format(getYearFirstDay()) + START_TIME;
	}

	/**
	 * 获取当年第一天的结束时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getYearFirstDayEnd() {
		return format(getYearFirstDay()) + END_TIME;
	}

	/**
	 * 获取当年的前几个年或者后几个年的第一天的开始时间.
	 *
	 * @param month(正数代表后几年,负数代表前几年,0代表当年)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getYearFirstDayStart(final Integer year) {
		return format(getYearFirstDay(year)) + START_TIME;
	}

	/**
	 * 获取当月的前几个年或者后几个年的第一天的结束时间.
	 *
	 * @param month(正数代表后几年,负数代表前几年,0代表当年)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getYearFirstDayEnd(final Integer year) {
		return format(getYearFirstDay(year)) + END_TIME;
	}

	/**
	 * 获取当年最后一天的开始时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getYearLastDayStart() {
		return format(getYearLastDay()) + START_TIME;
	}

	/**
	 * 获取当年最后一天的结束时间.
	 *
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getYearLastDayEnd() {
		return format(getYearLastDay()) + END_TIME;
	}

	/**
	 * 获取当月的前几个年或者后几个年的最后一天的开始时间.
	 *
	 * @param month(正数代表后几年,负数代表前几年,0代表当年)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getYearLastDayStart(final Integer year) {
		return format(getYearLastDay(year)) + START_TIME;
	}

	/**
	 * 获取当月的前几个年或者后几个年的最后一天的结束时间.
	 *
	 * @param month(正数代表后几年,负数代表前几年,0代表当年)
	 * @return 日期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getYearLastDayEnd(final Integer year) {
		return format(getYearLastDay(year)) + END_TIME;
	}

	/**
	 * 获取前几秒或者后几秒的当前时间.
	 *
	 * @param date(传入的时间)
	 * @param second(正数代表后几秒,负数代表前几秒,0代表当前时间)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getTimeSecond(final Integer second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getToday());
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * 获取传入时间的前几秒或者后几秒时间.
	 *
	 * @param date(传入的时间)
	 * @param second(正数代表后几秒,负数代表前几秒,0代表当前时间)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getTimeSecond(final Date date, final Integer second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * 获取前几分钟或者后几分钟的当前时间.
	 *
	 * @param date(传入的时间)
	 * @param minute(正数代表后几分钟,负数代表前几分钟,0代表当前时间)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getTimeMinute(final Integer minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getToday());
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 获取传入时间的前几分钟或者后几分钟时间.
	 *
	 * @param date(传入的时间)
	 * @param minute(正数代表后几分钟,负数代表前几分钟,0代表当前时间)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getTimeMinute(final Date date, final Integer minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 获取前几小时或者后几小时的当前时间.
	 *
	 * @param date(传入的时间)
	 * @param minute(正数代表后几小时,负数代表前几小时,0代表当前时间)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getTimeHour(final Integer hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getToday());
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	/**
	 * 获取传入时间的前几小时或者后几小时时间.
	 *
	 * @param date(传入的时间)
	 * @param minute(正数代表后几小时,负数代表前几小时,0代表当前时间)
	 * @return 日期时间
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Date getTimeHour(final Date date, final Integer hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	/**
	 * 把传入的日期转换成星期.
	 *
	 * @param date(传入的日期)
	 * @return 星期字符串
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static String getWeek(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer dayForWeek = calendar.get(Calendar.DAY_OF_WEEK);

		switch (dayForWeek) {
		case Calendar.MONDAY:
			return MONDAY;
		case Calendar.TUESDAY:
			return TUESDAY;
		case Calendar.WEDNESDAY:
			return WEDNESDAY;
		case Calendar.THURSDAY:
			return THURSDAY;
		case Calendar.FRIDAY:
			return FRIDAY;
		case Calendar.SATURDAY:
			return SATURDAY;
		default:
			return SUNDAY;
		}
	}

	/**
	 * 获取当前时间的日历年.
	 *
	 * @return 日期数字
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Integer getYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取传入数据的日历年.
	 *
	 * @param year(传入年份)
	 * @return 日期数字
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Integer getYear(Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取当前时间的日历月.
	 *
	 * @return 日期数字
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Integer getMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取传入数据的日历月.
	 *
	 * @param month(传入月份)
	 * @return 日期数字
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Integer getMonth(Integer month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前时间的日历天.
	 *
	 * @return 日期数字
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Integer getDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前时间的前几个和后几个日历天.
	 *
	 * @param day(传入天份)
	 * @return 日期数字
	 * @author fuqihao
	 * @date 2018年1月1日
	 */
	public static Integer getDay(Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getToday());
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
}
