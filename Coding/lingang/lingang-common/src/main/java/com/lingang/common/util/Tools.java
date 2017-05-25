/**
 * 2015
 * 2015年6月24日
 * author: 
 * Tools.java
 */
package com.lingang.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;

import org.apache.commons.lang3.RandomStringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import de.odysseus.staxon.json.JsonXMLConfig;
import de.odysseus.staxon.json.JsonXMLConfigBuilder;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;

/**
 * @author  工具类
 */
public class Tools {
   private static SerializeConfig mapping = new SerializeConfig();  
   
   private static Calendar fromCal=Calendar.getInstance();
	 
   private static DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
  
    static{  
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));  
    }  
    /**
      * @Title: obj2json
      * @Description: 对象转json
      * @param obj
      * @return  
      * @return 返回类型  String   
      * @throws
      * @Author:   联系方式：
      * @Since: 2016年2月5日 下午4:06:27
     */
    public static String obj2json(Object obj){  
//      return JSON.toJSONString(obj,SerializerFeature.UseSingleQuotes);//使用单引号  
//      return JSON.toJSONString(obj,true);//格式化数据，方便阅读  
        return JSON.toJSONString(obj,mapping);  
    }  
    /**
      * @Title: json2obj
      * @Description: json转对象
      * @param jsonStr
      * @param clazz
      * @return  
      * @return 返回类型  T   
      * @throws
      * @Author:   联系方式：
      * @Since: 2016年2月5日 下午4:07:16
     */
    public static <T> T json2obj(String jsonStr,Class<T> clazz){  
        return JSON.parseObject(jsonStr,clazz);  
    }  
    /**
      * @Title: json2map
      * @Description: json 转map
      * @param jsonStr
      * @return  
      * @return 返回类型  Map<String,Object>   
      * @throws
      * @Author:   联系方式：
      * @Since: 2016年2月5日 下午4:07:59
     */
    @SuppressWarnings("unchecked")
	public static <T> Map<String,Object> json2map(String jsonStr){  
        return json2obj(jsonStr, Map.class);  
    }  
    /**
      * @Title: json2map
      * @Description: TODO
      * @param jsonStr
      * @param clazz
      * @return  
      * @return 返回类型  Map<String,T>   
      * @throws
      * @Author:   联系方式：
      * @Since: 2016年2月5日 下午4:09:02
     */
    public static <T> Map<String,T> json2map(String jsonStr,Class<T> clazz){  
        Map<String,T> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, T>>() {});  
        for (Entry<String, T> entry : map.entrySet()) {  
            JSONObject obj = (JSONObject) entry.getValue();  
            map.put(entry.getKey(), JSONObject.toJavaObject(obj, clazz));  
        }  
        return map;  
    } 
    
    
   /**
     * @Title: json2xml
     * @Description: TODO
     * @param json
     * @return  
     * @return 返回类型  String   
     * @throws
     * @Author:   联系方式：
     * @Since: 2016年2月5日 下午4:12:51
    */
    public static String json2xml(String json){  
        StringReader input = new StringReader(json);  
        StringWriter output = new StringWriter();  
        JsonXMLConfig config = new JsonXMLConfigBuilder().multiplePI(false).repairingNamespaces(false).build();  
        try {  
            XMLEventReader reader = new JsonXMLInputFactory(config).createXMLEventReader(input);  
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);  
            writer = new PrettyXMLEventWriter(writer);  
            writer.add(reader);  
            reader.close();  
            writer.close();  
        } catch( Exception e){  
            e.printStackTrace();  
        } finally {  
            try {  
                output.close();  
                input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        if(output.toString().length()>=38){//remove <?xml version="1.0" encoding="UTF-8"?>  
            return output.toString().substring(39);  
        }  
        return output.toString();  
    }  
      
   /**
     * @Title: xml2json
     * @Description: TODO
     * @param xml
     * @return  
     * @return 返回类型  String   
     * @throws
     * @Author:   联系方式：
     * @Since: 2016年2月5日 下午4:13:05
    */
    public static String xml2json(String xml){  
        StringReader input = new StringReader(xml);  
        StringWriter output = new StringWriter();  
        JsonXMLConfig config = new JsonXMLConfigBuilder().autoArray(true).autoPrimitive(true).prettyPrint(true).build();  
        try {  
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);  
            XMLEventWriter writer = new JsonXMLOutputFactory(config).createXMLEventWriter(output);  
            writer.add(reader);  
            reader.close();  
            writer.close();  
        } catch( Exception e){  
            e.printStackTrace();  
        } finally {  
            try {  
                output.close();  
                input.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return output.toString();  
    }  
	/**
	 * 把对象转换成字符串
	 * 
	 * @param obj
	 * @return String 转换成字符串,若对象为null,则返回空字符串.
	 */
	public static String toString(Object obj) {
		if (obj == null)
			return "";

		return obj.toString();
	}

	/**
	 * 把对象转换为int数值.
	 * 
	 * @param obj
	 *            包含数字的对象.
	 * @return int 转换后的数值,对不能转换的对象返回0。
	 */
	public static int toInt(Object obj) {
		int a = 0;
		try {
			if (obj != null)
				a = Integer.parseInt(obj.toString());
		} catch (Exception e) {

		}
		return a;
	}
	/**
	 * 把对象转换为Double数值.
	 * 
	 * @param obj
	 *            包含数字的对象.
	 * @return int 转换后的数值,对不能转换的对象返回0。
	 */
	public static Double toDouble(Object obj) {
		Double a = 0.0;
		try {
			if (obj != null)
				a = Double.parseDouble(obj.toString());
		} catch (Exception e) {
			
		}
		return a;
	}
	
	/**
	 * 时间转换成字符串
	 * 
	 * @param date
	 *            时间
	 * @param formatType
	 *            格式化类型
	 * @return String
	 */
	public static String date2String(Date date, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return sdf.format(date)+"";
	}

 	public static Date string2Date(String datestr,String formatType){
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		try {
			return sdf.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 防止空字符串 null指针
	 * 
	 * @param str
	 * @return String
	 */
	public static String RmNull(String str) {
		if ((str == null) || (str.equals(""))) {
			str = "";
		}
		return str;
	}

	

	
	/**
	 * 减法
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double jianfa(double str1, double str2) {
		BigDecimal n0 = new BigDecimal(str1+"");
		BigDecimal n1 = new BigDecimal(str2+"");
		String str = n0.subtract(n1).toString();
		if ((str.indexOf(".") != -1)
				&& (str.substring(str.indexOf(".") + 1, str.length()).length() > 2)) {
			str = str.substring(0, str.indexOf(".") + 3);
		}
		return new BigDecimal(str).doubleValue();
	}
/**
 * 加法
 * @param str1
 * @param str2
 * @return
 */
	public static double jiafa(double str1, double str2) {
		BigDecimal n0 = new BigDecimal(str1+"");
		BigDecimal n1 = new BigDecimal(str2+"");
		String str = n0.add(n1).toString();
		if ((str.indexOf(".") != -1)
				&& (str.substring(str.indexOf(".") + 1, str.length()).length() > 2)) {
			str = str.substring(0, str.indexOf(".") + 3);
		}
		return new BigDecimal(str).doubleValue();
	}
/**
 * 乘法
 * @param str1
 * @param str2
 * @return
 */
	public static double chengfa(double str1, double str2) {
		BigDecimal n0 = new BigDecimal(str1+"");
		BigDecimal n1 = new BigDecimal(str2+"");
		String str = n0.multiply(n1).toString();
		if ((str.indexOf(".") != -1)
				&& (str.substring(str.indexOf(".") + 1, str.length()).length() > 2)) {
			str = str.substring(0, str.indexOf(".") + 3);
		}
		return new BigDecimal(str).doubleValue();
	}
	/**
	 * 比较
	 * @param str1
	 * @param str2
	 * @return 》=true 《false
	 */
	public static boolean bijiao(double str1, double str2){
		BigDecimal n0 = new BigDecimal(str1+"");
		BigDecimal n1 = new BigDecimal(str2+"");
		int i=n0.compareTo(n1);
		if(i<0){
			return false;
		}else{
			return true;
		}
	}
	
	public static int retintday(int ss,String bb){
		int cc=0;
		if(bb!=null){
			if(bb.equals("天")||bb.equals("日")){
				cc=ss;
			}else if(bb.equals("月")){
				cc=ss*30;
			}else if(bb.equals("季")){
				cc=ss*120;
			}else{
				cc=ss*360;
			}
		}
		return cc;
	}
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * 获取当前日期 yyyyMMdd
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(date);
		return strDate;
	}
	/**
	 * 获取随机数
	 * @return
	 */
	public static String getRandoms(){
		String sbb="";
		String sb="";
		Date now = new Date();
		//SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat outFormat1 = new SimpleDateFormat("yy,MM,dd,HH,mm,ss");
	//	String s = outFormat.format(now);
		String s2 = outFormat1.format(now);
		//s2=s2.substring(2,s2.length());
		String list []=s2.split(",");
		for (int i = 0; i < list.length; i++) {
			String string = list[i];
			//System.out.println(string);
			int si=Integer.parseInt(string);
			if(si<26){
				//System.out.println("字母："+(char)(si+96));
				sb +=(char)(si+96);
			}else{
				sb +=string;
			}
			
		}
	//	System.out.println(s2);
		//System.out.println(sb);
		//s=s.substring(2,s.length());
		if(sb.length()<11){
			int slen=11-sb.length();
			sbb =sb+RandomStringUtils.random(slen, "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789");
		}
		System.out.println(sbb);
		return sbb;
	}


	/**
	 * 字符串转码 
	 * @param str 所转换字符串 utf-8
	 * @param type 类型 GBK ISO-8859-1..。
	 * @return str
	 */
	public static String encodeString(String str,String otype,String ntype){
		try {
			str= new String(str.getBytes(otype),ntype);
			System.out.println("Default charsetName="+Charset.defaultCharset().name());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 元转换为分
	 * @param money
	 * @return
	 */
	public static String yuan2fen(String money){
		float sessionmoney = Float.parseFloat(money);
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		finalmoney = Integer.parseInt(finalmoney) + "";
		return finalmoney;
	}
	
	 /** 
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地 
     * @param list 
     */  
	public static void downloadPicture(List<String> list) {  
        URL url = null;  
          
        for (String urlString : list) {  
            try {  
                url = new URL(urlString);  
                DataInputStream dataInputStream = new DataInputStream(url.openStream());  
                String imageName = urlString.substring(urlString.lastIndexOf("/"),urlString.length());  
                FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/upload/"+imageName));  
  
                byte[] buffer = new byte[1024];  
                int length;  
  
                while ((length = dataInputStream.read(buffer)) > 0) {  
                    fileOutputStream.write(buffer, 0, length);  
                }  
                System.out.println("图片名称："+imageName);
                dataInputStream.close();  
                fileOutputStream.close();  
            } catch (MalformedURLException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	/**
	  * @Title: downloadfile
	  * @Description: 下载单个文件
	  * @param fileurl 文件远程地址
	  * @param downurl  下载到的地址
	  * @return 返回类型  void   
	  * @throws
	  * @Author:   联系方式：
	  * @Since: 2016年1月20日 上午11:16:01
	 */
	public static void downloadfile(String fileurl,String downurl) {  
		URL url = null;  
		
			try {  
				url = new URL(fileurl);  
				DataInputStream dataInputStream = new DataInputStream(url.openStream());  
				String fileName = fileurl.substring(fileurl.lastIndexOf("/"),fileurl.length());  
				FileOutputStream fileOutputStream = new FileOutputStream(new File(downurl));  
				
				byte[] buffer = new byte[1024];  
				int length;  
				
				while ((length = dataInputStream.read(buffer)) > 0) {  
					fileOutputStream.write(buffer, 0, length);  
				}  
				System.out.println("名称："+fileName);
				dataInputStream.close();  
				fileOutputStream.close();  
			} catch (MalformedURLException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
	}  
	/**
	 * 随机生成六位数验证码 
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date now;
	    
	    try {
	    	now = new Date();
	    	java.util.Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("发表于：");
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	/**
	 * 写txt里的单行内容
	 * @param filePath  文件路径
	 * @param content  写入的内容
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if(filePath.indexOf(":") != 1){
			filePath = File.separator + filePath;
		}
		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");      
	        BufferedWriter writer=new BufferedWriter(write);          
	        writer.write(content);      
	        writer.close(); 

	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 
	/**
	 * 读取txt里的单行内容
	 * @param filePath  文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {
			
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if(filePath.indexOf(":") != 1){
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { 		// 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);	// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件,查看此路径是否正确:"+filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}
	public static void main(String[] args) {
//		System.out.println(Tools.date2String(new Date(), "EEEE"));
//		 Calendar calendar=Calendar.getInstance();   
//		   calendar.setTime(new Date()); 
//		   System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//今天的日期 
//		   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);//让日期加1  
//		   System.out.println(calendar.get(Calendar.DATE));//加1之后的日期Top 
//		System.out.println(jianfa(10000.55,6020.00));
		//System.out.println(bijiao(0.4,0.5));
		//getRandoms();
//		for(int i=1;i<=26;i++)
//		 {
//		 System.out.println(i+"-->"+(char)(i+96));
//		 }
		/*for (int i = 0; i < 10000; i++) {
			getRandoms();
			
		}*/
//		System.out.println(yuan2fen(0.01232+""));
//		List<String> list=new ArrayList<String>();
//		for (int i = 1; i < 91; i++) {
//			list.add("http://h5.fpwap.com/go/dgmmd/resource/" + i + ".jpg");
//		}
//		downloadPicture(list);
		//bucketManager.delete(bucket, key);
		
		 Date time = new Date();
		 System.out.println(Tools.addTime(time, 30));
	}
	
	/**
	 * 确定日期后面加指定的天数变为新日期
	 *@Description:
	 *@param:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年3月24日 下午2:21:37
	 *@Version:1.0
	 */
	public static Date addTime(Date d, int day){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		 String date = format.format(d);
//		 System.out.println("现在的日期是：" + date);

		 Calendar ca = Calendar.getInstance();
		 ca.add(Calendar.DATE, day);// 增加的天数，可以改变的
		 d = ca.getTime();
//		 System.out.println("增加天数以后未转换格式的日期：" + d);
//		 String backTime = format.format(d);
//		 System.out.println("增加天数以后的日期：" + backTime);
//		 ParsePosition pos = new ParsePosition(8);
//		 Date currentTime_2 = format.parse(backTime, pos);
//		 System.out.println("转换后的日期：" + currentTime_2);
//		 d = Tools.string2Date(, formatType)
		 return d;
	 }
	
}
