package com.lingang.common.ad;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;  
  
/** 
 * HttpURLConnection 模拟浏览器http请求处理测试 
 *  
 * 整体操作流程(注意顺序) 
 *   1.根据url地址创建URL对象 
 *   2.url.openConnection() 得到HttpURLConnection 
 *   3.设置HttpURLConnection的配置(会根据配置生成http请求头信息) 
 *   4.httpConn.connect()建立与服务器的TCP连接 
 *   5.通过httpConn得到输出流,并写入参数 
 *   6.通过httpConn得到输入流,并读取服务器返回信息(这时才真正产生http请求发送给服务器,但可以根据配置来改变;一般返回可能是html字符串) 
 *  
 * httpRequestPost/httpRequestGet两种方式测试通过，返回的是html字符串(后台是jsp) 
 * 
 */  
public class HttpURLConnectionResult {  
	
	private final static String URL = "http://116.228.182.237:8000/UserService";
  
//    public static void main(String[] strs) throws Exception{  
////        String url = "http://116.228.182.237:8000/UserService/lgl";  
////        httpUrlConnection(url);  
//    	
////		System.out.println(resultStrLogin("lijh", "Qwer123"));
//		System.out.println(resultStrCheckUser("lgl"));
//    	
//    }  
    
	//查询AD域用户
	public static String resultStrCheckUser (String str) {
		String resultStr ="";
		if(str==null || str.equals("")){
			return resultStr;
		}
		try {
			str =URLEncoder.encode(str, "UTF-8");
			String urlStr =URL+"/"+str;
			String result=httpUrlConnection(urlStr);
//			System.out.println(result);
			Integer beginIndex=result.indexOf(">");
			Integer endIndex=result.lastIndexOf("<");
			String  subStr = result.substring(beginIndex+1, endIndex);
			if(!subStr.equals("{'data': ''}")){
				resultStr = result.substring(beginIndex+1, endIndex);
//				System.out.println(resultStr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
		return resultStr;
	}
	
	//登录
	public static String resultStrLogin (String account,String pwd) {
		String resultStr ="";
		if(account==null || account.equals("") || pwd==null || pwd.equals("")){
			return resultStr;
		}
		try {
			account =URLEncoder.encode(account, "UTF-8");
			pwd =URLEncoder.encode(pwd, "UTF-8");
			String urlStr =URL+"/"+account+"/"+pwd;
			String result=httpUrlConnection(urlStr);
//			System.out.println(result);
			Integer beginIndex=result.indexOf(">");
			Integer endIndex=result.lastIndexOf("<");
			if(endIndex>0){
				resultStr = result.substring(beginIndex+1, endIndex);
//				 System.out.println(resultStr);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultStr;
	}
      
    private static String httpUrlConnection(String urlStr) throws Exception{  
//        httpRequestGet(urlStr);  
        return httpRequestPost(urlStr);  
    }  
      
    /** 
     * http post请求 
     * @param urlStr URL地址 
     * @throws Exception 
     */  
    private static String httpRequestPost(String urlStr) throws Exception{  
    	String resultStr="";
        URL url = new URL(urlStr);  
          
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();  
          
        //设置是否向connection输出，因为是post请求，参数要放在 http正文内，因此需要设为true  
        httpConn.setDoOutput(true);  
          
        //是否向connection输入，默认为true  
        httpConn.setDoInput(true);  
          
        //POST请求方式  
        httpConn.setRequestMethod("POST");  
          
        //post请求不能使用缓存  
        httpConn.setUseCaches(false);  
          
        //是否自动执行重定向,默认为true  
        httpConn.setInstanceFollowRedirects(true);  
          
        //配置本次连接的Content-type，配置为application/x-www-form-urlencoded的意思是正文是urlencoded编码过的form参数  
        //下面我们可以看到我们对正文内容使用URLEncoder.encode 进行编码  
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
          
        //连接，从openConnection()至此的配置必须要在connect之前完成， 注意的是connection.getOutputStream会隐含的进行connect。  
        httpConn.connect();  
          
        DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());  
          
        //发送正文，正文内容其实跟get的URL中'?'后的参数字符串一致 word=wait&tn=news&from=news  
        String content = "filePath=" + URLEncoder.encode("设计资料/设计", "utf-8");  
          
        //DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面  
        dos.writeBytes(content);   
          
        //刷新流  
        dos.flush();  
          
        //关闭流  
        dos.close();  
          
        // 取得输入流，并使用Reader读取  
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));  
        
//        System.out.println("=========post request接收数据内容开始============");  
        String lines;  
        while ((lines = reader.readLine()) != null) {  
            System.out.println(lines); 
//        	CodeTest.testCode(lines);
            resultStr+=lines;
        }  
        reader.close();  
//        System.out.println("=========post request接收数据内容结束============");  
        httpConn.disconnect(); 
        
        return resultStr;
    }  
  
    /** 
     * http get 请求 
     * @param urlStr 请求URL地址 
     * @throws Exception 
     */  
	/*
	 * private void httpRequestGet(String urlStr) throws Exception{
	 * //URL拼接,如："http://www.baidu.com?name=HI,中国",这里对特殊字符进行了编码，不然会产生乱码 URL url
	 * = new URL(urlStr + "?filePath=" + URLEncoder.encode("设计资料/设计", "utf-8"));
	 * 
	 * //openConnection函数会根据URL的协议返回不同的URLConnection子类的对象
	 * //这里URL是一个http,因此实际返回的是HttpURLConnection HttpURLConnection httpConn =
	 * (HttpURLConnection)url.openConnection();
	 * 
	 * //进行连接,实际上request要在下一句的connection.getInputStream()函数中才会真正发到 服务器****待验证
	 * httpConn.connect();
	 * 
	 * // 取得输入流，并使用Reader读取 BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(httpConn.getInputStream()));
	 * 
	 * System.out.println("=========get request接收数据内容开始============"); String
	 * lines; while ((lines = reader.readLine()) != null) {
	 * System.out.println(lines); } reader.close(); System.out.println(
	 * "=========get request接收数据内容结束============"); httpConn.disconnect(); }
	 */
  
      
    /** 
     * http post 请求(未测试) 
     *  
     * @param urlStr 
     * @throws Exception 
     */  
    public static void httpRequestPostBig(String urlStr) throws Exception {  
            URL postUrl = new URL(urlStr);  
            HttpURLConnection connection = (HttpURLConnection) postUrl  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setRequestMethod("POST");  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
             //与httpRequestPost()的不同，设置了块大小为5字节  
            connection.setChunkedStreamingMode(5);  
            connection.connect();  
            /* 
             * 注意，下面的getOutputStream函数工作方式于在httpRequestPost()里面的不同 
             * 在httpRequestPost()里面该函数仍在准备http request，没有向服务器发送任何数据 
             * 而在这里由于设置了ChunkedStreamingMode，getOutputStream函数会根据connect之前的配置 
             * 生成http request头，先发送到服务器。 
             */  
            DataOutputStream out = new DataOutputStream(connection  
                    .getOutputStream());  
            String content = "name=" + URLEncoder.encode("发送到服务器                                                                             " +  
                    "                                          " +  
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "utf-8");  
            out.writeBytes(content);   
  
            out.flush();  
            out.close(); //到此时服务器已经收到了完整的http request了，而在httpRequestPost()函数里，要等到下一句getInputStream()服务器才能收到http请求。  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream()));  
              
            out.flush();  
            out.close();  
            String line;  
            System.out.println("==========post request开始==========");  
            while ((line = reader.readLine()) != null) {  
                System.out.println(line);  
            }  
            System.out.println("==========post request结束==========");  
            reader.close();  
            connection.disconnect();  
        }  
}  
