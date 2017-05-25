package com.lingang.common.jiami;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.dubbo.common.json.JSON;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */
public class DESCoderTest {
 
    public static void test(int vipId,String pwd,int time) throws Exception {
    	Map<String,Object> map = new HashMap<>();
    	map.put("vipId", vipId);map.put("pwd", pwd);map.put("time", time);
    	String inputStr = JSON.json(map);
    	System.out.println(inputStr);
      //  String key = DESCoder.initKey();
        System.err.println("原文:\t" + inputStr);
    //    System.err.println("密钥:\t" + key);
        byte[] inputData = inputStr.getBytes();
        inputData = DESCoder.encrypt(inputData, "Wsad123456.");
        System.out.println(inputData);
        System.err.println("加密后:\t" + DESCoder.encryptBASE64(inputData));
        byte[] outputData = DESCoder.decrypt(inputData, "Wsad123456.");
        String outputStr = new String(outputData);
        System.err.println("解密后:\t" + outputStr);
      //  assertEquals(inputStr, outputStr);
    }
    
    public static void main(String[] args) {
    	try {
			test(222,"2112",1456714890);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}