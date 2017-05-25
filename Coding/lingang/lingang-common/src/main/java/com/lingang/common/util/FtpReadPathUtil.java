package com.lingang.common.util;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

/**
 *@Description: ftp读取文件资源
 *@Author: 卢光磊  lgl1012dream@foxmail.com
 *@Since:2016年4月3日 下午12:42:59
 *@Version:1.0
 */
public class FtpReadPathUtil extends Thread{
	private static String remoteHost = "115.159.109.201";// FTP地址
	private static String port = "21";// 端口
	private static String userName = "lingang";// 用户名
	private static String password = "q1s2c3e4";// 密码
	private static FTPClient ftpClient= null; 
//	private static String fileName = "201603101650019185.jpg"; 
	
	/**
	* @Description: 登录
	* @param @return
	* @return boolean
	* @throws     
	* @author 卢光磊  lgl1012dream@foxmail.com 
	* @date: 2016年4月4日 下午7:27:29
	 */
	public static boolean connectServer() { 
		boolean isConnectSuccess=false;
		  try { 
			  ftpClient = new FTPClient(); 
				// 连接 
				ftpClient.connect(remoteHost, Integer.valueOf(port)); //设置端口号
//				ftpClient.connect(remoteHost); // 无端口号
				//登录
				isConnectSuccess = ftpClient.login(userName,password);
		  } catch (SocketException e) { 
		   e.printStackTrace(); 
		  } catch (IOException e) { 
		   e.printStackTrace(); 
		  } 
	  return isConnectSuccess;
	}

	/**
	* @Description: 获取文件内容
	* @param @param realPath
	* @param @return
	* @return Map<String,Object>
	* @throws     
	* @author 卢光磊  lgl1012dream@foxmail.com 
	* @date: 2016年4月4日 下午7:27:45
	 */
	public  static Map<String, Object> readPathLen(String realPath){
		Map<String, Object> map=new HashMap<String, Object>();
		List<String> list=new ArrayList<String>();
		String remotePath = "/"+realPath;
		try {
			if(!connectServer()){
				map.put("stateCode", "0");
				map.put("message", "FTP连接失败");
			}else{
				//设置文件 2进制传输
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.enterLocalPassiveMode();
				//设置文件流传输
				ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
				//设置缓冲
				ftpClient.setBufferSize(1024);
				//设置文件编码
				ftpClient.setControlEncoding("UTF-8");
				//SYST_NT ---对应windows系统
				FTPClientConfig ftpClientConfig = new FTPClientConfig(FTPClientConfig.SYST_NT);
				//系统编码为中文
				ftpClientConfig.setServerLanguageCode("zh");
//				fileName = new String(fileName.getBytes("GBK"),"ISO-8859-1");
				// 跳转到指定目录 
				boolean pathB=ftpClient.changeWorkingDirectory(remotePath);
				if(!pathB){
					map.put("stateCode", "2");
					map.put("message", "校验FTP图片目录失败");
				}else{
//					FTPFile[] ftpFiles = ftpClient.listFiles( remotePath + "/" + fileName);
					FTPFile[] ftpFiles = ftpClient.listFiles( remotePath);
					if(ftpFiles.length>0){
//						System.out.println("-----------ftpFiles.length:"+ftpFiles.length);
						String temp="";
						for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) { 
						   FTPFile file = ftpFiles[i]; 
						   if (file.isFile()) { 
							String fileName=file.getName();
							String suffixName=fileName.substring(fileName.indexOf("."));
//							System.out.println(fileName);
						    if(suffixName.equals(".jpg")||suffixName.equals(".bmp")||suffixName.equals(".gif")
						    		||suffixName.equals(".png")||suffixName.equals(".jpeg")||suffixName.equals(".tiff")
						    		||suffixName.equals(".svg")||suffixName.equals(".raw")||suffixName.equals(".JPG")){
						    	
						    	System.out.println(fileName+","+StringUtils.isStrInC(fileName));
						    	if(StringUtils.isStrInC(fileName)){
						    		map.put("stateCode", "4");
									map.put("message", "FTP图片命名不规范(包含中文)");
									return map;
						    	}
						    	list.add(fileName);
						    	String lastName=fileName.substring((fileName.indexOf(".")-1),fileName.indexOf("."));
						    	if(lastName.equals("F")){
						    		temp=fileName;
						    	}
						    }
						   } 
						}
						if(!temp.equals("")){
							list.set(list.indexOf(temp), list.get(0));
							list.set(0, temp);
						}
						map.put("stateCode", "1");
						map.put("message", list.size());
						map.put("imgPath", list);
					}else{
						map.put("stateCode", "3");
						map.put("message", "校验FTP图片失败");
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeServer();
		return map;
	}
	
	/**
	* @Description: 退出
	* @param 
	* @return void
	* @throws     
	* @author 卢光磊  lgl1012dream@foxmail.com 
	* @date: 2016年4月4日 下午7:28:26
	 */
	 public static void closeServer() {
		  if (ftpClient.isConnected()) {
		   try {
		    ftpClient.logout();
		    ftpClient.disconnect();
		   } catch (IOException e) {
		    e.printStackTrace();
		   }
		  }
		 }
//	public static void main(String[] args) {
//		System.out.println(readPathLen("4925-A01-01A"));
//		Map<String, Object> map=readPathLen("4925-G01-01A");
//		String[] arr=map.get("imgPath").toString().replace("[", "").replace("]", "").replace(" ", "").split(",");
//		List<String> list=Arrays.asList(arr);
//		for(String s:list){
//			System.out.println(StringUtils.isMessyCode(s));
//		}
//	}
}
