/**
 * @author zhangshuang
 * 2017年4月19日 下午9:46:21
 */
package com.glanway.iclock.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.h2.util.New;



/**
 * 说明 : 
 * 日期转换工具
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 下午9:46:21
 */
public class DateUtils {
	private static final Logger logger=Logger.getLogger(DateUtils.class);
	
	public static final String DATETIME_FORMAT_YYYY_MM_DD_HHMMSS="yyyy-MM-dd HH:mm:ss";
	
	private static Calendar calendar=Calendar.getInstance();
	
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		
		int today=calendar.get(calendar.DAY_OF_MONTH);
		System.out.println("tian==="+today);
	}
	
	private static SimpleDateFormat formatTool =new SimpleDateFormat();
	
	/**
	 * 
	 * 说明 : 
	 * 当前日期是否为当月第一天
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 上午10:01:21
	 */
	public static boolean isMonthForFirstDay(){
		int today=calendar.get(calendar.DAY_OF_MONTH);
		
		return today==1;
	}
	
	/**
	 * 
	 * 说明 : 
	 * 字符串类型转换成日期类型
	 * @param dateStr
	 * @param formatStr
	 * @return
	 * @author zhangshaung
	 * @dateTime 2017年4月20日 上午9:56:51
	 */
	public static Date str2Date(String dateStr,String formatStr){
		if(StringUtils.isEmpty(dateStr)){
			return null;
		}
		formatTool.applyPattern(formatStr);
		try {
			return formatTool.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info("Date parse exception"+e,e);
		}
		return null;
	}
}
