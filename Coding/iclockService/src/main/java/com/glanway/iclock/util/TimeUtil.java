package com.glanway.iclock.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tianxuan
 * @Time 2016/4/19
 */
public class TimeUtil {

    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYMMDDHHMMSS = "yyMMddhhmmss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * @param strDate 需要转换的时间
     * @return Date
     */
    public static Date StrToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 将时间格式化为  yyyy-mm-dd hh:mm:ss
     * @param date
     * @return
     */
    public static String format(Date date){
        String rtStr = "";
        if(null != date){
            SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
            rtStr = formatter.format(date);
        }
        return rtStr;
    }

    /**
     * 将时间格式化为  yyyy-mm-dd hh:mm:ss
     * @param date
     * @return
     */
    public static String format(Date date,String formatStr){
        String rtStr = "";
        if(null != date){
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            rtStr = formatter.format(date);
        }
        return rtStr;
    }
}
