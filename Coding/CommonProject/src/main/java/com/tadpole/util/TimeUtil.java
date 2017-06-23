package com.tadpole.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类.
 *
 * @author FUQIHAO
 * @version 1.0.0
 * @dateTime 2017年5月11日 上午11:36:39
 */
public abstract class TimeUtil {

	/** 日期格式 yyyy-MM-dd HH:mm:ss */
	public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式 yyyy-MM-dd */
	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	/** 日期格式 HH:mm:ss */
	public static final String FORMAT_HH_MM_SS = "HH:mm:ss";

	/** 日期格式 HH:mm */
	public static final String FORMAT_HH_MM = "HH:mm";

	/** 每天开始时间字符串 */
	public static final String START_TIME = " 00:00:00";

	/** 每天结束时间字符串 */
	public static final String END_TIME = " 23:59:59";

	/** 星期一 */
	public static final String WEEK_MONDAY = "星期一";

	/** 星期二 */
	public static final String WEEK_TUESDAY = "星期二";

	/** 星期三 */
	public static final String WEEK_WEDNESDAY = "星期三";

	/** 星期四 */
	public static final String WEEK_THURSDAY = "星期四";

	/** 星期五 */
	public static final String WEEK_FRIDAY = "星期五";

	/** 星期六 */
	public static final String WEEK_SATURDAY = "星期六";

	/** 星期日 */
	public static final String WEEK_SUNDAY = "星期日";

	/** 时间格式化线程池 */
	private static ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<>();

	/** 日期类线程池 */
	private static ThreadLocal<Calendar> calendarThreadLocal = new ThreadLocal<>();

	/**
	 * 从线程池中获取DateFormat对象,默认时间格式是 yyyy-MM-dd HH:mm:ss.
	 *
	 * @param pattern(时间格式规则)
	 * @return DateFromat对象
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:04:08
	 */
	private static DateFormat getDateFormat(String pattern) {
		DateFormat dateFormat = dateFormatThreadLocal.get();
		if (null == dateFormat) {
			if (null == pattern) {
				pattern = FORMAT_YYYY_MM_DD_HH_MM_SS;
			}

			dateFormat = new SimpleDateFormat(pattern);
			dateFormatThreadLocal.set(dateFormat);
		}

		return dateFormat;
	}

	/**
	 * 从线程池中获取Calendar对象
	 *
	 * @return Calendar对象
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:18:30
	 */
	private static Calendar getCalendar() {
		Calendar calendar = calendarThreadLocal.get();
		if (null == calendar) {
			calendar = Calendar.getInstance();
			calendarThreadLocal.set(calendar);
		}

		return calendar;
	}

	/**
	 * 将字符串日期转换成日期,日期规则为 yyyy-MM-dd HH:mm:ss.
	 *
	 * @param strDate(需要转换的日期)
	 * @return 转换后的日期
	 * @author FUQIHAO
	 * @throws ParseException
	 * @dateTime 2017年5月25日 下午1:47:46
	 */
	public static Date strToDateLong(final String strDate) {
		DateFormat dateFormat = getDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		ParsePosition pos = new ParsePosition(0);
		return dateFormat.parse(strDate, pos);
	}

	/**
	 * 将字符串日期转换成日期,日期规则为 yyyy-MM-dd.
	 *
	 * @param strDate(需要转换的日期)
	 * @return 转换后的日期
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午1:50:19
	 */
	public static Date strToDate(final String strDate) {
		DateFormat dateFormat = getDateFormat(FORMAT_YYYY_MM_DD);
		ParsePosition pos = new ParsePosition(0);
		return dateFormat.parse(strDate, pos);
	}

	/**
	 * 将字符串日期转换成日期,日期规则为自定义规则.
	 *
	 * @param strDate(需要转换的日期)
	 * @param pattern(时间格式规则)
	 * @return 转换后的日期
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:03:24
	 */
	public static Date strToDatePattern(final String strDate, final String pattern) {
		DateFormat dateFormat = getDateFormat(pattern);
		ParsePosition pos = new ParsePosition(0);
		return dateFormat.parse(strDate, pos);
	}

	/**
	 * 将日期格式化成日期字符串,日期规则为 yyyy-MM-dd HH:mm:ss.
	 *
	 * @param date(需要格式化的时间)
	 * @return 日期字符串
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:07:10
	 */
	public static String formatLong(final Date date) {
		DateFormat dateFormat = getDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(date);
	}

	/**
	 * 将日期格式化成日期字符串,日期规则为 yyyy-MM-dd.
	 *
	 * @param date(需要格式化的时间)
	 * @return 日期字符串
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:09:00
	 */
	public static String format(final Date date) {
		DateFormat dateFormat = getDateFormat(FORMAT_YYYY_MM_DD);
		return dateFormat.format(date);
	}

	/**
	 * 将日期格式化成日期字符串,日期规则为自定义规则.
	 *
	 * @param date(需要格式化的时间)
	 * @param pattern(时间格式规则)
	 * @return 日期字符串
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:09:19
	 */
	public static String formatPattern(final Date date, final String pattern) {
		DateFormat dateFormat = getDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * 获取日历类的当前时间.
	 *
	 * @return 日历类当前时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:20:19
	 */
	public static Date getToday() {
		Calendar calendar = getCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}

	/**
	 * 获取传入时间的前几天或者后几天的当前时间.
	 *
	 * @param date(传入的时间)
	 * @param day(正数代表后几天,负数代表前几天,0代表当天)
	 * @return
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:24:12
	 */
	public static Date getDayBeforeOrDayAfter(final Date date, final Integer day) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当天日期开始时间.
	 *
	 * @return 当天日期开始时间字符串
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:30:41
	 */
	public static String getDayStart() {
		return format(getToday()) + START_TIME;
	}

	/**
	 * 获取传入时间的前几天或者后几天的日期开始时间.
	 *
	 * @param date(传入的时间)
	 * @param day(正数代表后几天,负数代表前几天,0代表当天)
	 * @return 日期开始时间字符串
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:31:37
	 */
	public static String getDayStart(final Date date, final Integer day) {
		return format(getDayBeforeOrDayAfter(date, day)) + START_TIME;
	}

	/**
	 * 获取日历类的当天日期结束时间.
	 *
	 * @return 当天日期结束时间字符串
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:33:02
	 */
	public static String getDayEnd() {
		return format(getToday()) + END_TIME;
	}

	/**
	 * 获取传入时间的前几天或者后几天的日期开始时间.
	 *
	 * @param date(传入的时间)
	 * @param day(正数代表后几天,负数代表前几天,0代表当天)
	 * @return 日期结束时间字符串
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:33:29
	 */
	public static String getDayEnd(final Date date, final Integer day) {
		return format(getDayBeforeOrDayAfter(date, day)) + END_TIME;
	}

	/**
	 * 获取日历类的当月第一天的当前时间.
	 *
	 * @return 当月第一天的当前时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:39:24
	 */
	public static Date getMonthFirstDay() {
		Calendar calendar = getCalendar();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当月的前几个月或者后几个月的第一天的当前时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 月的第一天的当前时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:40:40
	 */
	public static Date getMonthFirstDay(final Integer month) {
		Calendar calendar = getCalendar();
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当月最后一天的当前时间.
	 *
	 * @return 当月最后一天的当前时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:44:34
	 */
	public static Date getMonthLastDay() {
		Calendar calendar = getCalendar();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 获取日历类的当月的前几个月或者后几个月的最后一天的当前时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 月的最后一天的当前时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午2:45:09
	 */
	public static Date getMonthLastDay(final Integer month) {
		Calendar calendar = getCalendar();
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 获取当月第一天的开始时间.
	 *
	 * @return 当月第一天的开始时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:00:12
	 */
	public static String getMonthFirstDayStart() {
		return format(getMonthFirstDay()) + START_TIME;
	}

	/**
	 * 获取当月的前几个月或者后几个月的第一天的开始时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 前几个月或者后几个月的第一天的开始时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:01:02
	 */
	public static String getMonthFirstDayStart(final Integer month) {
		return format(getMonthFirstDay(month)) + START_TIME;
	}

	/**
	 * 获取当月第一天的结束时间.
	 *
	 * @return 第一天的结束时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:01:33
	 */
	public static String getMonthFirstDayEnd() {
		return format(getMonthFirstDay()) + END_TIME;
	}

	/**
	 * 获取当月的前几个月或者后几个月的第一天的结束时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 前几个月或者后几个月的第一天的结束时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:02:13
	 */
	public static String getMonthFirstDayEnd(final Integer month) {
		return format(getMonthFirstDay(month)) + END_TIME;
	}

	/**
	 * 获取当月最后一天的开始时间.
	 *
	 * @return 最后一天的开始时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:02:39
	 */
	public static String getMonthLastDayStart() {
		return format(getMonthLastDay()) + START_TIME;
	}

	/**
	 * 获取当月的前几个月或者后几个月的最后一天的开始时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 前几个月或者后几个月的最后一天的开始时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:02:41
	 */
	public static String getMonthLastDayStart(final Integer month) {
		return format(getMonthLastDay(month)) + START_TIME;
	}

	/**
	 * 获取当月最后一天的结束时间.
	 *
	 * @return 最后一天的结束时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:02:45
	 */
	public static String getMonthLastDayEnd() {
		return format(getMonthLastDay()) + END_TIME;
	}

	/**
	 * 获取当月的前几个月或者后几个月的最后一天的结束时间.
	 *
	 * @param month(正数代表后几月,负数代表前几月,0代表当月)
	 * @return 前几个月或者后几个月的最后一天的结束时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:02:47
	 */
	public static String getMonthLastDayEnd(final Integer month) {
		return format(getMonthLastDay(month)) + END_TIME;
	}

	/**
	 * 获取传入时间的前几分钟或者后几分钟时间.
	 *
	 * @param date(传入的时间)
	 * @param minute(正数代表后几分钟,负数代表前几分钟,0代表当前时间)
	 * @return 前几分钟或者后几分钟时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:35:12
	 */
	public static Date getTimeMinuteBeforeOrMinuteAfter(final Date date, final Integer minute) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 获取传入时间的前几小时或者后几小时时间.
	 *
	 * @param date(传入的时间)
	 * @param minute(正数代表后几小时,负数代表前几小时,0代表当前时间)
	 * @return 前几小时或者后几小时时间
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:35:12
	 */
	public static Date getTimeHourBeforeOrHourAfter(final Date date, final Integer hour) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}

	/**
	 * 把传入的日期转换成星期
	 *
	 * @param date(传入的日期)
	 * @return 星期
	 * @author FUQIHAO
	 * @dateTime 2017年5月25日 下午3:25:55
	 */
	public static String getWeek(final Date date) {
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		Integer dayForWeek = calendar.get(Calendar.DAY_OF_WEEK);

		switch (dayForWeek) {
		case Calendar.MONDAY:
			return WEEK_MONDAY;
		case Calendar.TUESDAY:
			return WEEK_TUESDAY;
		case Calendar.WEDNESDAY:
			return WEEK_WEDNESDAY;
		case Calendar.THURSDAY:
			return WEEK_THURSDAY;
		case Calendar.FRIDAY:
			return WEEK_FRIDAY;
		case Calendar.SATURDAY:
			return WEEK_SATURDAY;
		default:
			return WEEK_SUNDAY;
		}
	}

	/**
	 * 获取当前时间的日历年.
	 *
	 * @return
	 * @author FUQIHAO
	 * @dateTime 2017年6月21日 下午1:09:12
	 */
	public static Integer getYear(Integer year) {
		Calendar calendar = getCalendar();
		if (null != year) {
			calendar.set(Calendar.YEAR, year);
		}
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取当前时间的日历月.
	 *
	 * @return
	 * @author FUQIHAO
	 * @dateTime 2017年6月21日 下午1:10:16
	 */
	public static Integer getMonth(Integer month) {
		Calendar calendar = getCalendar();
		if (null != month) {
			calendar.set(Calendar.MONTH, month - 1);
		}
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前时间的日历天.
	 *
	 * @return
	 * @author FUQIHAO
	 * @dateTime 2017年6月21日 下午1:10:31
	 */
	public static Integer getDay(Integer day) {
		Calendar calendar = getCalendar();
		if (null != day) {
			calendar.set(Calendar.DAY_OF_MONTH, day);
		}
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 仅获取当前时间的月份最后一天的天份.
	 *
	 * @return
	 * @author FUQIHAO
	 * @dateTime 2017年6月22日 下午5:48:13
	 */
	public static Integer getOnlyMonthLastDay(Integer year, Integer month) {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.YEAR, getYear(year));
		calendar.set(Calendar.MONTH, getMonth(month) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
}
