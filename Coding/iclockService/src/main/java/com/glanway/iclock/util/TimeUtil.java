package com.glanway.iclock.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间的工具类.
 * 
 * @author fuqihao
 * @version 1.0-20170405
 * @since 1.0-20170405
 */
public abstract class TimeUtil {

    /** 时间格式yyyy-MM-dd */
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /** 时间格式yyMMddhhmmss */
    public static final String FORMAT_YYMMDDHHMMSS = "yyMMddhhmmss";

    /** yyyy-MM-dd HH:mm:ss */
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /** 每天起始时间 */
    public static final String START_TIME = " 00:00:00";

    /** 每天结束时间 */
    public static final String END_TIME = " 23:59:59";

    private TimeUtil() {
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @author fuqihao
     * @param strDate(需要转换的时间)
     * @return 转换后的时间
     * @since 1.0-20170405
     */
    public static Date StrToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 将时间格式化为 yyyy-mm-dd hh:mm:ss.
     *
     * @author fuqihao
     * @param date(需要转换的时间)
     * @return 转换后的String类型时间
     * @since 1.0-20170405
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
     * 将时间格式化为 自定义.
     *
     * @author fuqihao
     * @param date(需要转换的时间)
     * @param formatStr(自定义格式化格式)
     * @return 转换后的String类型时间
     * @since 1.0-20170510
     */
    public static String format(Date date, String formatStr) {
        String rtStr = "";
        if (null != date) {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            rtStr = formatter.format(date);
        }
        return rtStr;
    }

    /**
     * 获取日历类当天格式化时间.
     *
     * @author fuqihao
     * @return 转换后的String类型时间
     * @since 1.0-20170426
     */
    private static String formatToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        return format(calendar.getTime(), FORMAT_YYYY_MM_DD);
    }

    /**
     * 获取一天的开始时间.
     *
     * @author fuqihao
     * @return 转换后的String类型时间
     * @since 1.0-20170426
     */
    public static String getDateStart() {
        return formatToday() + START_TIME;
    }

    /**
     * 获取一天的结束时间.
     *
     * @author fuqihao
     * @return 转换后的String类型时间
     * @since 1.0-20170426
     */
    public static String getDateEnd() {
        return formatToday() + END_TIME;
    }

    /**
     * 当月第一天.
     *
     * @author fuqihao
     * @return 转换后的String类型时间
     * @since 1.0-20170427
     */
    public static String getMonthStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format(calendar.getTime(), FORMAT_YYYY_MM_DD);
    }

    /**
     * 当月最后一天.
     *
     * @author fuqihao
     * @return 转换后的String类型时间
     * @since 1.0-20170427
     */
    public static String getMonthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format(calendar.getTime(), FORMAT_YYYY_MM_DD);
    }

    /**
     * 当月第一天的起始时间.
     *
     * @author fuqihao
     * @return 转换后的String类型时间
     * @since 1.0-20170427
     */
    public static String getMonthTimeStart() {
        return getMonthStart() + START_TIME;
    }

    /**
     * 当月最后一天的结束时间.
     *
     * @author fuqihao
     * @return 转换后的String类型时间
     * @since 1.0-20170427
     */
    public static String getMonthTimeEnd() {
        return getMonthEnd() + END_TIME;
    }

    /**
     * 获取传入时间的前几分钟或者后几分钟.
     *
     * @author fuqihao
     * @param date(需要转换的时间)
     * @param minute(正数代表后几分钟,负数代表前几分钟)
     * @return 转换后时间
     * @since 1.0-20170428
     */
    public static Date getTimeBeforeMinute(Date date, Integer minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
}
