package com.lingang.common.ad;

import java.io.UnsupportedEncodingException;

public class CodeTest {

//	public static void main(String[] args) {
//		testCode("123升水");
//	}
	
	public static void testCode(String testStr){
		try {
			System.out.println("---------------------------------------");
			
			System.out.println(new String(testStr.getBytes("GB2312")));
			System.out.println(new String(testStr.getBytes("GBK")));
			System.out.println(new String(testStr.getBytes("UTF-8")));
			System.out.println("---------------------------------------");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
