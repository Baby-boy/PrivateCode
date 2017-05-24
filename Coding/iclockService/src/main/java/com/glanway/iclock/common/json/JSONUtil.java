package com.glanway.iclock.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;

public class JSONUtil {

	private static final Logger logger=Logger.getLogger(JSONUtil.class);
	
	public static String getJSONString(Object obj){
		
		ObjectMapper ObjectMapper = new ObjectMapper();
		
		try {
			return ObjectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.info("JsonProcessingException:"+e,e);
		}
		return null;
			
	}
	public static String getPrettyJSONString(Object obj){
		
		ObjectMapper ObjectMapper = new ObjectMapper();
		
		try {
			return ObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.info("JsonProcessingException:"+e,e);
		}
		return null;
		
	}
	public static <T>T getObjectJSONString(String json,Class<T> clazz){
		
		ObjectMapper ObjectMapper = new ObjectMapper();
		
		try {
			return ObjectMapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.info("IOException:"+e,e);
		} 
		return null;
		
	}
}
