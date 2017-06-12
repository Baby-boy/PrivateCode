package com.glanway.iclock.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 日期转换工具.
 * 
 * @author zhangshaung
 * @version 1.0-20170405
 * @since 1.0-20170405
 */
public class DateUtils {
    private static final Logger logger = Logger.getLogger(DateUtils.class);

    public static final String DATETIME_FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 当前日期是否为当月第一天
     *
     * @author zhangshaung
     * @return
     * @since 1.0-20170405
     */
    public static boolean isMonthForFirstDay() {
        int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        return today == 1;
    }

    /**
     * 字符串类型转换成日期类型.
     *
     * @author zhangshuang
     * @param dateStr
     * @param formatStr
     * @return
     * @since 1.0-20170405
     */
    public static Date str2Date(String dateStr, String formatStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            return new SimpleDateFormat(formatStr).parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.info("Date parse exception" + e, e);
        }
        return null;
    }
}
