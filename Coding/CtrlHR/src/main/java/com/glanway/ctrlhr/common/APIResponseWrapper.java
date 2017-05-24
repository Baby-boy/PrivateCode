package com.glanway.ctrlhr.common;
import java.util.HashMap;
import java.util.Map;

public class APIResponseWrapper
   
{

    	private void setResult(String result, String message)
		{
			setResult(result,message,"2");
    	}

        public void setResult(String result, String message, String loginState) {
			if(this.response ==null)
			{
				this.response =new HashMap<String, Object>();
			}

			this.response.put("code",result);
			this.response.put("msg",message);
			this.response.put("loginState",loginState);
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

		public static APIResponseWrapper CreateInstance(String result)
		{
			return APIResponseWrapper.CreateInstance(result,"","2");
		}

		public static APIResponseWrapper CreateInstance(String result, String message)
		{
			return APIResponseWrapper.CreateInstance(result,message,"2");
		}

		public static APIResponseWrapper CreateInstance(String result, String message,String loginState)
		{
			APIResponseWrapper responseWrapper =new APIResponseWrapper();
			responseWrapper.setResult(result, message,loginState);
			return responseWrapper;
		}

		public static Map<String, Object> BuildResponse(String result)
		{
			return BuildResponse(result, "", "2");
		}


		public static Map<String, Object> BuildResponse(String result, String message)
    	{
	        return BuildResponse(result, message, "2");
    	}

		public static Map<String, Object> BuildResponse(String result, String message,String loginState)
		{
			APIResponseWrapper responseWrapper =new APIResponseWrapper();
			responseWrapper.setResult(result, message,loginState);
			return responseWrapper.getResponse();
		}
    }