package com.glanway.iclock.common;
import java.util.HashMap;
import java.util.Map;

public class APIResponseWrapper
    {

    	private void setResult(Boolean result, String message)
		{
			setResult(result,message,2);
    	}

        public void setResult(Boolean result, String message, int isLogin) {
			if(this.response ==null)
			{
				this.response =new HashMap<String, Object>();
			}

			this.response.put("success",result);
			this.response.put("msg",message);
			this.response.put("isLogin",isLogin);
		}
    	
    	public Map<String, Object> getResponse() {
			return response;
		}

		public void setData(Map<String, Object> data) {
			if(this.response ==null)
			{
				this.response =new HashMap<String, Object>();
			}

			if(this.response.containsKey("data")){
				this.response.remove("data");
			}

			this.response.put("data",data);
		}
		public void putData(Map<String, Object> data) {
			if(this.response ==null)
			{
				this.response =new HashMap<String, Object>();
			}

			if(!this.response.containsKey("data")){
				this.response.put("data",new HashMap<String, Object>());
			}

			HashMap<String, Object> currentData = (HashMap<String, Object>)response.get("data");

			currentData.putAll(data);
		}


		public void setResponse(Map<String, Object> response) {
			this.response = response;
		}
		
		public void putResponse(Map<String, Object> response) {
			if(this.response ==null)
    		{
				this.response =new HashMap<String, Object>();
    		}
			
			this.response.putAll(response);
		}

		private Map<String, Object> response;

		public static APIResponseWrapper CreateInstance(Boolean result)
		{
			return APIResponseWrapper.CreateInstance(result,"",2);
		}

		public static APIResponseWrapper CreateInstance(Boolean result, String message)
		{
			return APIResponseWrapper.CreateInstance(result,message,2);
		}

		public static APIResponseWrapper CreateInstance(Boolean result, String message,int isLogin)
		{
			APIResponseWrapper responseWrapper =new APIResponseWrapper();
			responseWrapper.setResult(result, message,isLogin);
			return responseWrapper;
		}

		public static Map<String, Object> BuildResponse(Boolean result)
		{
			return BuildResponse(result, "", 2);
		}


		public static Map<String, Object> BuildResponse(Boolean result, String message)
    	{
	        return BuildResponse(result, message, 2);
    	}

		public static Map<String, Object> BuildResponse(Boolean result, String message,int isLogin)
		{
			APIResponseWrapper responseWrapper =new APIResponseWrapper();
			responseWrapper.setResult(result, message,isLogin);
			return responseWrapper.getResponse();
		}
    }