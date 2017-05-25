package com.yd.dby.utils.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class YdOssEnv {

	public static String ENDPOINT;// "http://res.zwzyd.com";

	public static String ACCESS_ID;// "LTAIZ9zH34RkcCJq";

	public static String ACCESS_KEY;// "V1T2OVcn7j8LPz0DTKyFok47lApmrc";

	public static String HOST;// "http://res.zwzyd.com";

	public static String TOKEN_RequestURI;// "/oss/callback";
	
	public static String DIR;// "/oss/callback";

	public static String CALLBACK_RequestURI;// "/oss/callback";

	public static String CALLBACK_RequestURL;// "http://106.14.20.143:20000/oss/callback";

	@Value("${yd.oss.ENDPOINT}")
	public void setENDPOINT(String val) {
		YdOssEnv.ENDPOINT = val;
	}

	@Value("${yd.oss.ACCESS_ID}")
	public void setACCESS_ID(String val) {
		YdOssEnv.ACCESS_ID = val;
	}

	@Value("${yd.oss.ACCESS_KEY}")
	public void setACCESS_KEY(String val) {
		YdOssEnv.ACCESS_KEY = val;
	}

	@Value("${yd.oss.HOST}")
	public void setHOST(String val) {
		YdOssEnv.HOST = val;
	}

	@Value("${yd.oss.CALLBACK_RequestURI}")
	public void setCALLBACK_RequestURI(String val) {
		YdOssEnv.CALLBACK_RequestURI = val;
	}

	@Value("${yd.oss.CALLBACK_RequestURL}")
	public void setCALLBACK_RequestURL(String val) {
		YdOssEnv.CALLBACK_RequestURL = val;
	}
	
	@Value("${yd.oss.TOKEN_RequestURI}")
	public void setTOKEN_RequestURI(String val) {
		YdOssEnv.TOKEN_RequestURI = val;
	}
	
	@Value("${yd.oss.DIR}")
	public void setDIR(String val) {
		YdOssEnv.DIR = val;
	}
}
