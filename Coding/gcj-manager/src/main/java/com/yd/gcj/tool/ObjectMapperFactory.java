package com.yd.gcj.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {
	
	public static String doIt(Object obj){
		ObjectMapper om = new ObjectMapper();
		String result = "";
		try {
			result = om.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("ObjectMapperFactory:"+result);
		
		return result;
	}
	
}
