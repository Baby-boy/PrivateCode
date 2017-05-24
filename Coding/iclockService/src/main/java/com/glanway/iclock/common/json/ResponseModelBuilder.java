package com.glanway.iclock.common.json;

public class ResponseModelBuilder {

	public static ResponseModel ok(String message,Object responseObject){
		
		ResponseModel responseModel = new ResponseModel();
		responseModel.setMessage(message);
		responseModel.setResponseCode("200");
		responseModel.setResponseObject(responseObject);
		
		return responseModel;
	}
	
	public static ResponseModel err(String message,Object responseObject){
		
		ResponseModel responseModel = new ResponseModel();
		responseModel.setMessage(message);
		responseModel.setResponseCode("500");
		responseModel.setResponseObject(responseObject);
		
		return responseModel;
	}
	
}
