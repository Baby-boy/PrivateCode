package com.glanway.iclock.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIRequestWrapper {

    	private Map<String, Object> request;

		public Map<String, Object> getRequest() {
			return request;
		}

		public void setRequest(Map<String, Object> request) {
			this.request = request;
		}

		public static APIRequestWrapper CreateInstance(Map<String, Object> _request)
		{
			APIRequestWrapper requestWrapper = new APIRequestWrapper();
			requestWrapper.request = _request;
			return requestWrapper;
		}


		public ArrayList<String> getArrayParam(String key)
		{
			if(!checkRequestKey(key))
			{
				return new ArrayList<String>();
			}

			return (ArrayList<String>)request.get(key);
		}
    public ArrayList<Map<String,String>> getHashMapParam(String key)
    {
        if(!checkRequestKey(key))
        {
            return new ArrayList<Map<String,String>>();
        }

        return (ArrayList<Map<String,String>>)request.get(key);
    }

		public List<Integer> getIntegerArrayParam(String key)
		{
			if(!checkRequestKey(key))
			{
				return new ArrayList<Integer>();
			}

			return (List<Integer>)request.get(key);
		}

		public String getStringParam(String key)
		{
			if(!checkRequestKey(key))
			{
				return "";
			}
			
			return request.get(key).toString();
		}
    public Long getLongParam(String key)
    {
        if(!checkRequestKey(key))
        {
            return null;
        }

        return (Long)request.get(key);
    }
		
		public double getDoubleParam(String key)
		{
			if(!checkRequestKey(key))
			{
				return 0;
			}
			
			try
			{
				return Double.parseDouble(request.get(key).toString());
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				return 0;
			}
		}
    	
		public int getIntegerParam(String key)
		{
			if(!checkRequestKey(key))
			{
				return 0;
			}
			
			try
			{
				return Integer.parseInt(request.get(key).toString());
			}catch(NumberFormatException ex){
				ex.printStackTrace();
				return 0;
			}
		}
		
		private Boolean checkRequestKey(String key)
		{
			if(request == null || !request.containsKey(key) || request.get(key) == null)
			{
				return false;
			}
			
			return true;
		}
    }