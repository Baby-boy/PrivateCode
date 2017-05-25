package com.lingang.common.jiami;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;


/**
 *@Description: (加密工具类)
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月2日 下午3:43:45
 *@Version:1.0
 */
public class DESUtil {
	
	private static Logger log = LoggerFactory.getLogger(DESUtil.class);
	
	/**
	 * 密钥 请勿随意修改!
	 */
	public static final String key = "JaFC5cpk6E";
	
	/**
	 *@Description:加密
	 *@Author: 
	 * @param  vipId 用户ID
	 * @param  pwd 用户密码
	 * @return String 返回token
	 * @date 2016年2月29日 下午12:23:44
	 */
	public static String encrypt(int vipId,String pwd) {
    	String inputStr = "{\"v\":\""+vipId+"\",\"p\":\""+pwd+"\",\"t\":\""+System.currentTimeMillis()/1000+"\"}";
    	byte[] inputData = inputStr.getBytes();
        try {
			inputData = DESCoder.encrypt(inputData,key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("加密异常!",e);
			return "";
		}
        try {
        	inputStr = DESCoder.encryptBASE64(inputData);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BASE64加密异常!",e);
			return "";
		}
		return inputStr;
	}
	
	/*
	 * 获取用户Id
	 */
	public static int getVipId(String inputStr){
		Map<String,Object> map =  decrypt(inputStr);
		if(map.size()>0){
			return Integer.parseInt(map.get("v").toString());
		}
		return 0;
	}
	
	/*
	 * 获取用户密码
	 */
	public static String getPassage(String inputStr){
		Map<String,Object> map =  decrypt(inputStr);
		if(map.size()>0){
			return map.get("p").toString();
		}
		return null;
	}
	
	/*
	 * 获取token生成时间戳
	 */
	public static int getTime(String inputStr){
		Map<String,Object> map =  decrypt(inputStr);
		if(map.size()>0){
			return Integer.parseInt(map.get("t").toString());
		}
		return 0;
	}
	
	
	/**
	 *@Description:解密
	 *@Author: 
	 * @param  inputStr
	 * @return Map<String,Object>
	 * @date 2016年2月29日 下午12:23:50
	 */
	public static Map<String,Object> decrypt(String inputStr) {
		byte[] inputData = null;
		try {
			inputData = DESCoder.decryptBASE64(inputStr);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BASE64解密异常!",e);
		}
		String outputStr = "";
        try {
        	 byte[] outputData = DESCoder.decrypt(inputData,key);
              outputStr = new String(outputData);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("解密异常!",e);
		}
        Map<String,Object> map = new HashMap<>();
        try {
			map = JSON.parseObject(outputStr, Map.class);
        } catch (Exception e) {
        	e.printStackTrace();
			log.error("Json解析异常!",e);
		}
		return map;
	}
	
	public static void main(String[] args) {
		Long a = System.currentTimeMillis()/1000;
		
	/*	for (int i = 100000; i <= 100000; i++) {
			String jiami = encrypt(i,key);
			System.out.println(jiami);
			Map<String,Object> map =  decrypt(jiami);
			System.out.println(map);
			System.out.println(getVipId(jiami));
		}*/
		String jiami = encrypt(1,"sdsadasd");
		System.out.println(jiami);
		System.out.println(getVipId(jiami));
		System.out.println(getPassage(jiami));
		Long b = System.currentTimeMillis()/1000;
		
	//	System.out.println(b-a);
	}
	

}