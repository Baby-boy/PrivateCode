package com.yd.dby.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.expression.ParseException;

public class YdUtilDate {

	public static Long currentMillis() {
		return System.currentTimeMillis();
	}
	
	public static String currentMillisString() {
		return String.valueOf( System.currentTimeMillis() );
	}

	public static Long currentMillis(Long add) {
		return System.currentTimeMillis() + add;
	}
	
	
	/* 
     * 将时间转换为时间戳 - 年-月-日
     */    
    public static String dateToStampYear(String s) throws ParseException, java.text.ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        String res = String.valueOf(ts).substring(0, 10);
        return res;
    }
    
    
    /* 
     * 将时间转换为时间戳 - 年-月-日 时：分：秒
     */    
    public static String dateToStampDay(String s) throws ParseException, java.text.ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        String res = String.valueOf(ts).substring(0, 10);
        return res;
    }
    
    
    /* 
     * 将时间戳转换为时间 年-月-日
     */
    public static String stampToDateYear(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        return res;
    }
    
    /* 
     * 将时间戳转换为时间 年-月-日 时：分：秒
     */
    public static String stampToDateDay(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        return res;
    }

    
    /**
     * 获取年月日时分秒
     */
    public static String getFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
    
}