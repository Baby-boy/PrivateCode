package com.lingang.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *@Description: (时间日期工具类)
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月2日 下午3:44:32
 *@Version:1.0
 */
public class DateTimeUtils {
	/**
	 * 日期时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String dateTimeString = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式 yyyy-MM-dd
	 */
	public static String dateString = "yyyy-MM-dd";
	
	public static String dateTimeString2 = "yy-MM-dd HH:mm";
	
	/**
	 * 日期格式 yyyyMMddHHmmss
	 */
	public static String dateTimeLongString = "yyyyMMddHHmmss";
	/**
	 * 日期格式 yyyyMMdd
	 */
	public static String dateLongString = "yyyyMMdd";
	
	/**
	 * 日期时间格式For 文件名 yyyy_MM_dd_HH_mm_ss
	 */
	public static String dateTimeString4FileName = "yyyy_MM_dd_HH_mm_ss";
	
	public final static String YYYY = "yyyy";
	public final static String YYYY_MM = "yyyy-MM";
	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
	public final static String YYYY_MM_DD_HH_mm = "yyyy-MM-dd HH:mm";
	public final static String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public final static String YYYY_MM_DD_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
	public final static String MM_DD_HH_mm = "MM-DD HH:mm"; 
	
	/**
	 * 日
	 */
	public final static int INTERVAL_DAY = 1;
	/**
	 * 周
	 */
	public final static int INTERVAL_WEEK = 2;
	/**
	 * 月
	 */
	public final static int INTERVAL_MONTH = 3;
	/**
	 * 年
	 */
	public final static int INTERVAL_YEAR = 4;
	/**
	 * 小时
	 */
	public final static int INTERVAL_HOUR = 5;
	/**
	 * 分钟
	 */
	public final static int INTERVAL_MINUTE = 6;
	/**
	 * 秒
	 */
	public final static int INTERVAL_SECOND = 7;
	
	private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);
	
	/**
	 * 解决日期字符串,默认日期格式为： yyyy-MM-dd HH:mm:ss，如果出现解析错误，自动尝试其他格式
	 * @param dateStr
	 * @return
	 */
	public static Date parseStr(String dateStr){
		return parseStr(dateStr, dateTimeString);
	}
	
	/**
	 * 解决日期字符串
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseStr(String dateStr , String pattern){
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date resultDate = null;
		try {
			resultDate = df.parse(dateStr);
		} catch (ParseException e) {
			for(String key : DATE_REGEX_MAP.keySet()){
				String regex = DATE_REGEX_MAP.get(key);
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(dateStr);
				if(m.matches()){
					try {
						df = new SimpleDateFormat(key);
						resultDate = df.parse(dateStr);
					} catch (ParseException e1) {
						logger.error("日期解析错误,dateStr:"+dateStr);
					}
				}
			}
		}
		return resultDate;
	}
	
	/**
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null || pattern == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		String result = df.format(date);
		if(result.equalsIgnoreCase("0001-01-01 00:00:00") || result.equalsIgnoreCase("0001-01-01")){
			result = "";
		}
		return result;
	}
	
	 /**
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date){
		   return format(date, dateTimeString);
	  }
	
	/**
	 * 
	 * @Title: noYearSecondFormat
	 * @Description: 
	 * 			获取无年和秒数的时间格式
	 * @param date
	 * @return		例如： 12-29 12:30
	 * @author 
	 */
	public static String noYearSecondFormat(Date date) {
		return format(date, MM_DD_HH_mm);
	}
	
	public static String nowFormat() {
		return format(new Date(), dateTimeString);
	}
	
	public static String nowFormat(String timeString) {
		return format(new Date(),timeString);
	}
	
	public static String nowFormat2() {
		return format(new Date(), dateTimeString2);
	}
	
	/**
	 * 两date比较
	 * 
	 * @param beforeDate 日期之前
	 * @param afterDate 日期之后
	 * @return -1 为已过 1为没过
	 */
	public static int compareDate(Date beforeDate, Date afterDate) {
		Calendar beforeCalendar = Calendar.getInstance();
		Calendar afterCalendar = Calendar.getInstance();
		beforeCalendar.setTime(beforeDate);
		afterCalendar.setTime(afterDate);
		return beforeCalendar.compareTo(afterCalendar);
	}
	
	/**
	 *@Description:比对2个时间的大小
	 *@Author: 
	 * @param  dt1 
	 * @param  dt2 
	 * @return int -1为小 1为大 0为等于
	 * @date 2016年2月24日 下午6:29:42
	 */
	public static int compareDate2(Date dt1, Date dt2) {
		 if (dt1.getTime() > dt2.getTime()) {
             return 1;
         } else if (dt1.getTime() < dt2.getTime()) {
             return -1;
         } else {
             return 0;
         }
	}
	
	/**
	 * 判断目标日期是否在时间段类
	 * @param beforeDate
	 * @param afterDate
	 * @param targetDate
	 * @return
	 */
	public static boolean isBetweenDate(Date beforeDate, Date afterDate,Date targetDate){
		if(targetDate == null){
			throw new RuntimeException("targetDate should not be null!");
		}
		if(beforeDate == null && afterDate == null){
			return false;
		}
		if(afterDate == null){
			return (compareDate(beforeDate,targetDate) <= 0);
		}
		if(beforeDate == null){
			return (compareDate(targetDate,afterDate)<= 0);
		}
		return (compareDate(beforeDate,targetDate) <= 0)&&(compareDate(targetDate,afterDate)<= 0);
	}
	
	/**
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date dateOperateByMonth(Date date,int month){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date == null ? new Date() : date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	public static Date dateOperateByDay(Date date,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date == null ? new Date() : date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	
	/**
	 * 邮箱激活，时间操作，对验证时间+24H
	 * @param date 需要操作的时间，如果为null,就去当前时间
	 * @param hour 小时，对操作时间增加或减少的量
	 * @author chj
	 */
	public static Date dateOperateByHour(Date date,int hour){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date == null ? new Date() : date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}
	
	/**
	 * 验证手机验证码过期，时间操作，对验证时间+有效时间
	 * @param date 需要操作的时间，如果为null,就去当前时间
	 * @param minute 分钟，对操作时间增加或减少的量
	 * @author chj
	 */
	public static Date dateOperateByMinute(Date date,int minute){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date == null ? new Date() : date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
	public static Date dateOperateBySecond(Date date,int second){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date == null ? new Date() : date);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}
	
	public static Date getDateWithoutTime(Date date){
		Date result = null;
		String dateStr = format(date,dateString);
		result = parseStr(dateStr, dateString);
		return result;
	}
	
	/**
	 * 增加时间
	 * 
	 * @param interval
	 *            [INTERVAL_DAY,INTERVAL_WEEK,INTERVAL_MONTH,INTERVAL_YEAR,
	 *            INTERVAL_HOUR,INTERVAL_MINUTE]
	 * @param date
	 * @param n
	 *            可以为负数
	 * @return
	 */
	public static Date dateAdd(int interval, Date date, int n) {
		long time = (date.getTime() / 1000); // 单位秒
		switch (interval) {
		case INTERVAL_DAY:
			time = time + n * 86400;// 60 * 60 * 24;
			break;
		case INTERVAL_WEEK:
			time = time + n * 604800;// 60 * 60 * 24 * 7;
			break;
		case INTERVAL_MONTH:
			time = time + n * 2678400;// 60 * 60 * 24 * 31;
			break;
		case INTERVAL_YEAR:
			time = time + n * 31536000;// 60 * 60 * 24 * 365;
			break;
		case INTERVAL_HOUR:
			time = time + n * 3600;// 60 * 60 ;
			break;
		case INTERVAL_MINUTE:
			time = time + n * 60;
			break;
		case INTERVAL_SECOND:
			time = time + n;
			break;
		default:
		}

		Date result = new Date();
		result.setTime(time * 1000);
		return result;
	}

	/**
	 * 计算两个时间间隔
	 * 
	 * @param interval
	 *            [INTERVAL_DAY,INTERVAL_WEEK,INTERVAL_MONTH,INTERVAL_YEAR,
	 *            INTERVAL_HOUR,INTERVAL_MINUTE]
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int dateDiff(int interval, Date begin, Date end) {
		long beginTime = (begin.getTime() / 1000); // 单位：秒
		long endTime = (end.getTime() / 1000); // 单位: 秒
		long tmp = 0;
		if (endTime == beginTime) {
			return 0;
		}

		// 确定endTime 大于 beginTime 结束时间秒数 大于 开始时间秒数
		if (endTime < beginTime) {
			tmp = beginTime;
			beginTime = endTime;
			endTime = tmp;
		}

		long intervalTime = endTime - beginTime;
		long result = 0;
		switch (interval) {
		case INTERVAL_DAY:
			result = intervalTime / 86400;// 60 * 60 * 24;
			break;
		case INTERVAL_WEEK:
			result = intervalTime / 604800;// 60 * 60 * 24 * 7;
			break;
		case INTERVAL_MONTH:
			result = intervalTime / 2678400;// 60 * 60 * 24 * 31;
			break;
		case INTERVAL_YEAR:
			result = intervalTime / 31536000;// 60 * 60 * 24 * 365;
			break;
		case INTERVAL_HOUR:
			result = intervalTime / 3600;// 60 * 60 ;
			break;
		case INTERVAL_MINUTE:
			result = intervalTime / 60;
			break;
		case INTERVAL_SECOND:
			result = intervalTime / 1;
			break;
		default:
		}

		// 做过交换
		if (tmp > 0) {
			result = 0 - result;
		}
		return (int) result;
	}
	
	public static int dateDiff(int interval, String dateStart, String dateStop) {
		SimpleDateFormat format = new SimpleDateFormat(dateTimeString);
		Date begin = null;
		Date end = null;
		try {
			begin = format.parse(dateStart);
			end = format.parse(dateStop);
		} catch (ParseException e) {
			logger.error("日期转换错误!", e);
		}
		long beginTime = (begin.getTime() / 1000); // 单位：秒
		long endTime = (end.getTime() / 1000); // 单位: 秒
		long tmp = 0;
		if (endTime == beginTime) {
			return 0;
		}

		// 确定endTime 大于 beginTime 结束时间秒数 大于 开始时间秒数
		if (endTime < beginTime) {
			tmp = beginTime;
			beginTime = endTime;
			endTime = tmp;
		}

		long intervalTime = endTime - beginTime;
		long result = 0;
		switch (interval) {
		case INTERVAL_DAY:
			result = intervalTime / 86400;// 60 * 60 * 24;
			break;
		case INTERVAL_WEEK:
			result = intervalTime / 604800;// 60 * 60 * 24 * 7;
			break;
		case INTERVAL_MONTH:
			result = intervalTime / 2678400;// 60 * 60 * 24 * 31;
			break;
		case INTERVAL_YEAR:
			result = intervalTime / 31536000;// 60 * 60 * 24 * 365;
			break;
		case INTERVAL_HOUR:
			result = intervalTime / 3600;// 60 * 60 ;
			break;
		case INTERVAL_MINUTE:
			result = intervalTime / 60;
			break;
		case INTERVAL_SECOND:
			result = intervalTime / 1;
			break;
		default:
		}

		// 做过交换
		if (tmp > 0) {
			result = 0 - result;
		}
		return (int) result;
	}
	/**
	 * 日期格式正则map
	 */
	public static final Map<String, String> DATE_REGEX_MAP = new HashMap<String, String>();
	static {
//		DATE_REGEX_MAP.put("yyyy-MM-dd", "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)");
		DATE_REGEX_MAP.put(dateString, "(\\d{4})-(\\d{2}|\\d{1})-(\\d{2}|\\d{1})");
		DATE_REGEX_MAP.put(dateLongString, "(\\d{4})(\\d{2})(\\d{2})");
		DATE_REGEX_MAP.put(dateTimeLongString, "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})");
		DATE_REGEX_MAP.put(dateTimeString, "(\\d{4})-(\\d{2}|\\d{1})-(\\d{2}|\\d{1}) (\\d{2}|\\d{1}):(\\d{2}|\\d{1}):(\\d{2}|\\d{1})");
	}
	
	/**
	* @Description: 秒转时分秒
	* @author 
	* @param  time
	* @return String
	* @date 2015年9月18日 下午6:22:33
	 */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "0秒";
        else {
        	minute = time / 60;
            if(time<60){
            	 timeStr = time + "秒";
            } else if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
            } else {
                hour = minute / 60;
              /*  if (hour > 99)
                    return "exceed time limit!";*/
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + "时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
            }
        }
        return timeStr;
    }
    
    public static String secToTime2(int time) {
        String timeStr = null;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "0秒";
        else {
        	if(time<60){
           	 timeStr = time + "秒";
           } else{
                minute = time / 60;
                second = time % 60;
                timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
             }
           }
        return timeStr;
    }
    
    public static int secToMinute(int time) {
    	int minute = 0;
        if (time <= 0)
            return minute;
        else {
           int sec = time / 60;
           minute = sec;
        }
        return minute;
    }
    

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
    
    @SuppressWarnings("deprecation")
	public static Date getTodate(){
    	Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
    }
    
    @SuppressWarnings("deprecation")
	public static Date getToMonth(){
    	Date date = new Date();
		date.setDate(1);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
    }
    
	public static void main(String[] args) {
	//	System.out.println(secToTime(1));
		System.out.println(format(getTodate(),"yyyy-MM-dd HH:mm:ss"));
		System.out.println(format(getToMonth(),"yyyy-MM-dd HH:mm:ss"));
		//System.out.println(dateDiff(1,parseStr("2015-9-1 16:50:00"),new Date()));
		/*String dateString1 = "1234-5-06 11:22:4";
		Pattern p = Pattern.compile(DATE_REGEX_MAP.get("yyyy-MM-dd HH:mm:ss"));
	    Matcher m = p.matcher(dateString1);
	    boolean b = m.matches();
	    System.out.println(b);
		System.out.println(format(parseStr(dateString1)));*/
	}
}