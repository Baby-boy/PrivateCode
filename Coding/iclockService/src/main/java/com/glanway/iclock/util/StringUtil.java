package com.glanway.iclock.util;

import oracle.sql.CHAR;

/**
 * 操作String的工具类
 * @author tianxuan
 * @Time 2016/4/19
 */
public class StringUtil {

     public static String trim(String str){
         return null == str ? str : str.trim();
     }

    public static String toUpperCase(String str){
        return null == str ? str : str.trim().toUpperCase();
    }

    /**
     * String 是否非空
     * @param str
     * @return
     */
    public static boolean notEmpty(String str){
        return (null != str) && !str.isEmpty();
    }

    /**
     * null 值 转为 空串
     * @param str
     * @return
     */
    public static String nullToEmpty(String str){
        return null == str ? "" : str;
    }
    
    /**
     * 指定数字的长度，长度不够左补零
     * @param str
     * @param strLength
     * @return
     */
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
              sb = new StringBuffer();
              sb.append("0").append(str);// 左(前)补0
           // sb.append(str).append("0");//右(后)补0
              str = sb.toString();
              strLen = str.length();
        }
        return str;
    }
    
    /**
     * 去掉数字字符串左补的零
     * @param str
     * @param index
     * @return
     */
    public static String removeZeroForNum(String str, int index) {
    	
//    	String str = "00001102";// 测试用字符串
         int len = str.length();// 取得字符串的长度
//         int index = 0;// 预定义第一个非零字符串的位置

         char strs[] = str.toCharArray();// 将字符串转化成字符数组
         for (int i = 0; i < len; i++) {
             if ('0' != strs[i]) {
                 index = i;// 找到非零字符串并跳出
                 break;
             }
         }
         String strLast = str.substring(index, len);// 截取字符串
        return strLast;
    }

    public static String subNstring(String str,int n){
        if(str.length()<n){
            return str;
        }
        return str.substring(str.length()-n,str.length());
    }
    
    /**
     * 
     * 说明 : 字符串数组转换成中间用指定间隔符隔开的字符串
     * 
     * @param array
     * @param space 间隔符
     * @return
     * @author zhangshaung
     * @dateTime 2017年4月18日 下午2:56:19
     */
    public static String stringArrToString(String[] array,String space){
    	String aString="";
        for(int i=0;i<array.length;i++){
        	if(null != array[i] && !("".equals(array[i]))){
        		aString+=array[i];
            	
            	if(i!=array.length-1){
            		aString+=space;
            	}
        	}
        }
        return "".equals(aString) ? null :aString;
    }
    
    /**
     * 
     * 说明 : 
     * 判断一个字符否为中文
     * @param c
     * @return
     * @author zhangshaung
     * @dateTime 2017年4月21日 下午7:55:36
     */
    public static boolean isChinese(char c){
		//根据字节码判断
    	return c >= 0x4E00 && c <= 0x9FA5;
    }
    /**
     * 
     * 说明 : 
     * 判断字符串中是否含有中文
     * @param str
     * @return
     * @author zhangshaung
     * @dateTime 2017年4月21日 下午7:51:09
     */
    public static boolean hasChinese(String str){
		if(null == str) return false;
		
		for(char c:str.toCharArray()){
			if(isChinese(c)) return true; //有一个中文就返回true
		}
    	
    	return false;
    	
    }
    public static void main(String[] args) {
    	/*String aString=String.format("1", null);
        System.out.println("aString="+aString);*/
    /*	String[] array={"1","2","3"};
    	 System.out.println(stringArrToString(array,""));*/
    	
       String CMD_DATA_UPDATE_USER = "C:%s:DATA UPDATE USERINFO %s";
       
       System.out.println(CMD_DATA_UPDATE_USER);
        
    	
	}
}
