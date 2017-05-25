package com.lingang.common.util;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.json.ParseException;


/**
 * 字符串处理工具类
 * @author Tommy
 *
 */
public class StringUtils extends org.springframework.util.StringUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);
	
	/**
	 * JSON转Map
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> json2Map(String jsonStr){
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Object> map = null;
		try {
			map = objectMapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			logger.error("JSON转Map异常", e);
		}
		return map;
	}
	
	/**
	 * 用于实体类的toString方法
	 * @param object
	 * @return
	 */
	public static String objectToString(Object object){
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			logger.error("转JSON异常", e);
		}
		return json;
	}
	/**
	 * 使字符串第一个字符小写
	 * @param target
	 * @return
	 */
	public static String firstCharToLow(String target){
		String firstChar = null;
		if(target != null && !"".equalsIgnoreCase(target)){
			firstChar = target.substring(0, 1);
			target = firstChar.toLowerCase() + target.substring(1);
		}
		return target;
	}
	
	/**
	 * 使字符串数组转为list<String>
	 * @param target
	 * @return
	 */
	public static List<String> stringArr2List(String[] strArr){
		List<String> list = new ArrayList<String>();
		if(strArr != null){
			for(String str:strArr){
				list.add(str);
			}
		}
		return list;
	}
	
	/**
	 * 判断字符串是否为NULL或""
	 * @param str
	 * @return
	 */
	public static boolean isStrNull (String str){
		if(null == str || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	/**
     * 产生随机字符串
     */
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;

	/**
	 * 产生随机字符串
	 * @param length 长度
	 * @return
	 */
	public static final String randomString(int length) {
	         if (length < 1) {
	             return null;
	         }
	         if (randGen == null) {
	                randGen = new Random();
	                numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
	                   "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	                  //numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	                 }
	         char [] randBuffer = new char[length];
	         for (int i=0; i<randBuffer.length; i++) {
	             randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
	          //randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
	         }
	         return new String(randBuffer);
	}
	
	
	  /**
	   * 左补齐一个特殊字符.
	   *
	   * Pad to a size of <code>size</code>.
	   *
	   * <pre>
	   * StringUtils.leftPad(null, *, *)     = null
	   * StringUtils.leftPad("", 3, 'z')     = "zzz"
	   * StringUtils.leftPad("bat", 3, 'z')  = "bat"
	   * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
	   * StringUtils.leftPad("bat", 1, 'z')  = "bat"
	   * StringUtils.leftPad("bat", -1, 'z') = "bat"
	   * </pre>
	   *
	   * @param str  the String to pad out, may be null
	   * @param size  the size to pad to
	   * @param padChar  the character to pad with
	   * @return left padded String or original String if no padding is necessary,
	   *  <code>null</code> if null String input
	   * @since 2.0
	   */
	  public static String leftPad(String str, int size, char padChar) {
	      if (str == null) {
	          return null;
	      }
	      int pads = size - str.length();
	      if (pads <= 0) {
	          return str; // returns original String when possible
	      }
	      return padding(pads, padChar).concat(str);
	  }

	  /**
	   * 左补齐字符串
	   *
	   * Pad to a size of <code>size</code>.
	   *
	   * <pre>
	   * StringUtils.leftPad(null, *, *)      = null
	   * StringUtils.leftPad("", 3, "z")      = "zzz"
	   * StringUtils.leftPad("bat", 3, "yz")  = "bat"
	   * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
	   * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
	   * StringUtils.leftPad("bat", 1, "yz")  = "bat"
	   * StringUtils.leftPad("bat", -1, "yz") = "bat"
	   * StringUtils.leftPad("bat", 5, null)  = "  bat"
	   * StringUtils.leftPad("bat", 5, "")    = "  bat"
	   * </pre>
	   *
	   * @param str  the String to pad out, may be null
	   * @param size  the size to pad to
	   * @param padStr  the String to pad with, null or empty treated as single space
	   * @return left padded String or original String if no padding is necessary,
	   *  <code>null</code> if null String input
	   */
	  public static String leftPad(String str, int size, String padStr) {
	      if (str == null) {
	          return null;
	      }
	      if (isStrNull(padStr)) {
	          padStr = " ";
	      }
	      int padLen = padStr.length();
	      int strLen = str.length();
	      int pads = size - strLen;
	      if (pads <= 0) {
	          return str; // returns original String when possible
	      }
	      if (padLen == 1) {
	          return leftPad(str, size, padStr.charAt(0));
	      }

	      if (pads == padLen) {
	          return padStr.concat(str);
	      } else if (pads < padLen) {
	          return padStr.substring(0, pads).concat(str);
	      } else {
	          char[] padding = new char[pads];
	          char[] padChars = padStr.toCharArray();
	          for (int i = 0; i < pads; i++) {
	              padding[i] = padChars[i % padLen];
	          }
	          return new String(padding).concat(str);
	      }
	  }
	  
	  /**
	   * 右补齐一个特殊字符.
	   *
	   * Pad to a size of <code>size</code>.
	   *
	   * <pre>
	   * StringUtils.rightPad(null, *, *)     = null
	   * StringUtils.rightPad("", 3, 'z')     = "zzz"
	   * StringUtils.rightPad("bat", 3, 'z')  = "bat"
	   * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
	   * StringUtils.rightPad("bat", 1, 'z')  = "bat"
	   * StringUtils.rightPad("bat", -1, 'z') = "bat"
	   * </pre>
	   *
	   */
	  public static String rightPad(String str, int size, char padChar) {
	      if (str == null) {
	          return null;
	      }
	      int pads = size - str.length();
	      if (pads <= 0) {
	          return str; // returns original String when possible
	      }
	      return str.concat(padding(pads, padChar));
	  }

	  /**
	   * 右补齐字符串
	   *
	   * Pad to a size of <code>size</code>.
	   *
	   * <pre>
	   * StringUtils.rightPad(null, *, *)      = null
	   * StringUtils.rightPad("", 3, "z")      = "zzz"
	   * StringUtils.rightPad("bat", 3, "yz")  = "bat"
	   * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
	   * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
	   * StringUtils.rightPad("bat", 1, "yz")  = "bat"
	   * StringUtils.rightPad("bat", -1, "yz") = "bat"
	   * StringUtils.rightPad("bat", 5, null)  = "  bat"
	   * StringUtils.rightPad("bat", 5, "")    = "  bat"
	   * </pre>
	   *
	   */
	  public static String rightPad(String str, int size, String padStr) {
	      if (str == null) {
	          return null;
	      }
	      if (isStrNull(padStr)) {
	          padStr = " ";
	      }
	      int padLen = padStr.length();
	      int strLen = str.length();
	      int pads = size - strLen;
	      if (pads <= 0) {
	          return str; // returns original String when possible
	      }
	      if (padLen == 1) {
	          return rightPad(str, size, padStr.charAt(0));
	      }

	      if (pads == padLen) {
	          return str.concat(padStr);
	      } else if (pads < padLen) {
	          return str.concat(padStr.substring(0, pads));
	      } else {
	          char[] padding = new char[pads];
	          char[] padChars = padStr.toCharArray();
	          for (int i = 0; i < pads; i++) {
	              padding[i] = padChars[i % padLen];
	          }
	          return  str.concat(new String(padding));
	      }
	  }
	  
	  /**
	   * 返回一个给定次数的重复字符串
	   * to a given length.
	   *
	   * <pre>
	   * StringUtils.padding(0, 'e')  = ""
	   * StringUtils.padding(3, 'e')  = "eee"
	   * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
	   * </pre>
	   *
	   * Note: this method doesn't not support padding with
	   * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
	   * as they require a pair of <code>char</code>s to be represented.
	   * If you are needing to support full I18N of your applications
	   * consider using {@link #repeat(String, int)} instead. 
	   * 
	   *
	   * @param repeat  number of times to repeat delim
	   * @param padChar  character to repeat
	   * @return String with repeated character
	   * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
	   * @see #repeat(String, int)
	   */
	  private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
	      if (repeat < 0) {
	          throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
	      }
	      final char[] buf = new char[repeat];
	      for (int i = 0; i < buf.length; i++) {
	          buf[i] = padChar;
	      }
	      return new String(buf);
	  }	
	
	
	/**
	 * 替换html标签特殊字符
	 * @param str
	 * @return str
	 */
	public static String replaceHtmlCh(String str){
		str = str.replace("&","&amp;");   
        str = str.replace("<","&lt;");   
        str = str.replace(">","&gt;");   
//        str = str.replace("\\","&#39;");   
//        str = str.replace("\"","&quot;");
		return str;
	}
	
	/**
	 * 将html标签特殊字符还原
	 * @param str
	 * @return str
	 */
	public static String reverseReplaceHtmlCh(String str){
		if(!StringUtils.hasText(str)){
			return "";
		}
			str = str.replace("&amp;","&");   
			str = str.replace("&lt;","<");   
			str = str.replace("&gt;",">");   
			str = str.replace("&#39;","\\");   
			str = str.replace("&quot;","\"");
			str=str.replace("&nbsp;", " ");
			//&amp;nbsp;
		return str;
	}
	/**
	 * 去除字符串中除中间空格外的特殊字符
	 * @author 吴鹏
	 * @param str 需要处理的字符串
	 * @return 处理后返回的字符串
	 */
	public static String deleteWhitespace(String str)
	 {
	      if (StringUtils.isEmpty(str)) {
	        return str;
	      }else {
	    	  str = str.trim();
	      }
	      
	      int sz = str.length();
	      char[] chs = new char[sz];
	      int count = 0;
	      for (int i = 0; i < sz; ++i) {
	      if (!(Character.isWhitespace(str.charAt(i))) || str.charAt(i) == 0x0020) {
	          chs[(count++)] = str.charAt(i);
	        }
	      }
	      
	      if (count == sz) {
	       return str;
	      }
	      return new String(chs, 0, count);
	  }
	
	public static String formatPrice(Integer places, BigDecimal bigDecimal){
		if(bigDecimal == null){
			return "";
		}
		bigDecimal = bigDecimal.setScale(places, BigDecimal.ROUND_HALF_UP);
		return bigDecimal.toString();
	}
	
	/**
	 * 保留小数点。后缀除去0。
	 * 
	 * @param places
	 * @param bigDecimal
	 * @return
	 */
	public static String formatPriceNoEndZero(Integer places, BigDecimal bigDecimal) {
		String str = formatPrice(places, bigDecimal);
		while(str.endsWith("0")) {          //判断字符串是不是以0结尾
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	/**
	 * 默认设置null的字符串为空字符串
	 * @author 吴鹏
	 * @param str 传入的字符串
	 * @return 处理过后的字符串
	 */
	public static String defaultEmpty(String str){
		return (str == null) ? "" : str;
	}
	
	/**
	 * 默认设置空对象为空字符串
	 * @author 吴鹏
	 * @param obj 传入的对象
	 * @return 处理过后的字符串
	 */
	public static String defaultEmpty(Object obj){
		return (obj == null) ? "" : obj.toString();
	}
	
	/**
	 *  截取指定字节长度的字符串，不能返回半个汉字
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String getSubString(String str, int length) {
		int count = 0;
		int offset = 0;
		char[] c = str.toCharArray();
		
		if(c.length <= length){
			return str;
		}
		
		for (int i = 0; i < c.length; i++) {
			if (c[i] > 256) {
				offset = 2;
				count += 2;
			} else {
				offset = 1;
				count++;
			}
			if (count == length) {
				return str.substring(0, i + 1);
			}
			if ((count == length + 1 && offset == 2)) {
				return str.substring(0, i);
			}
		}
		return "";
	}
	/**
	 * 判断字符是否是中文
	 *
	 * @param c 字符
	 * @return 是否是中文
	 */
	public static boolean isChinese(char c) {
	    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
	            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
	            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
	            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
	            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	        return true;
	    }
	    return false;
	}
	
	/**
	* @Description 判断是否为数字
	* @param  number
	* @return boolean
	* @author 
	* @date 2015年4月30日 上午11:42:29
	 */
	public static boolean isNumber(String number){
		return number.matches("^[0-9]*$");
	}
	
	/**
	 * 判断字符串是否有中文
	 *
	 * @param strName 字符串
	 * @return 是否有中文
	 */
	public static boolean isStrInC(String strName) {
	    Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
	    Matcher m = p.matcher(strName);
	    String after = m.replaceAll("");
	    String temp = after.replaceAll("\\p{P}", "");
	    char[] ch = temp.trim().toCharArray();
	    float chLength = ch.length;
	    float count = 0;
	    for (int i = 0; i < ch.length; i++) {
	        char c = ch[i];
	            if (isChinese(c)) {
	                count = count + 1;
	            }
	    }
	    float result = count / chLength;
	    if (result > 0) {
	        return true;
	    } else {
	        return false;
	    }
	 
	}
	 
	/**
	 * 判断字符串是否是乱码
	 *
	 * @param strName 字符串
	 * @return 是否是乱码
	 */
	public static boolean isMessyCode(String strName) {
	    Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
	    Matcher m = p.matcher(strName);
	    String after = m.replaceAll("");
	    String temp = after.replaceAll("\\p{P}", "");
	    char[] ch = temp.trim().toCharArray();
	    float chLength = ch.length;
	    float count = 0;
	    for (int i = 0; i < ch.length; i++) {
	        char c = ch[i];
	        if (!Character.isLetterOrDigit(c)) {
	            if (!isChinese(c)) {
	                count = count + 1;
	            }
	        }
	    }
	    float result = count / chLength;
	    if (result > 0) {
	        return true;
	    } else {
	        return false;
	    }
	 
	}
	
	/**
	 * 出去文字中的换行符/r/n，防止导出cvs是出现不该有的换行
	 */
	public static String deleteEnter(String str){
		Pattern CRLF = Pattern.compile("(\r\n)");
	    Matcher m = CRLF.matcher(StringUtils.defaultEmpty(str));
	    if (m.find()) {  
	      return m.replaceAll("  "); 
	   } 
		return str;
	}
	
	/**
	 * 将json字符串转换为对象。
	 * @param json
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T stringToObject(String json,Class<?> clazz){
		T t = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.getVisibilityChecker().withFieldVisibility(Visibility.ANY);
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			t = (T) mapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.warn("json parse error!--"+e.getMessage(),e);
		}
		return t;
	}
	
	/**
	 * 字符串转int，null --> 0,非数字字符串场合，返回0
	 * @param str
	 * @return
	 */
	public static int  toInt(String str) {
		if(str == null){
			return 0;
		}
		
		try{
			return Integer.parseInt(str);
		}catch(Exception ex){
			return 0;
		}
		
		
	}
	
	/**
	 * 判断字符串是否为数字串
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
	
	public static String MD5(String res) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = res.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String dd = new String(str);
            return dd;
        } catch (Exception e) {
            return null;
        }
     }
	
	/**
	* @Description: 求百分比
	* @param  total 总数
	* @param  x 
	* @return String
	 */
	public static String getPercent(int total,int x) {
		if(total==0 ){
			return  "0%";
		}
		 // 创建一个数值格式化对象   
        NumberFormat numberFormat = NumberFormat.getInstance();   
         // 设置精确到小数点后2位   
         numberFormat.setMaximumFractionDigits(2);   
        String result = numberFormat.format((float)x/(float)total*100);
		return result+ "%";
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
//			System.out.println("Default charsetName="+Charset.defaultCharset().name());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/** 
	* 计算两个日期之间相差的天数 
	* @param smdate 较小的时间 
	* @param bdate 较大的时间 
	* @return 相差天数 
	* @throws ParseException 
	*/ 
	public static int daysBetween(Date smdate,Date bdate) 
	{ 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			smdate=sdf.parse(sdf.format(smdate));
			bdate=sdf.parse(sdf.format(bdate)); 
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		} 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(smdate); 
		long time1 = cal.getTimeInMillis(); 
		cal.setTime(bdate); 
		long time2 = cal.getTimeInMillis(); 
		long between_days=(time2-time1)/(1000*3600*24); 
	
		return Integer.parseInt(String.valueOf(between_days)); 
	} 
	
	
	/*function timing() {
		var times = document.getElementsByName("times");
		var nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		var nowtime = new Date();
		for (var i = 0; i < times.length; i++) {
			var timestr = times[i].value;
			var time = new Date(timestr);
			var diff = parseInt((time.getTime() - nowtime.getTime()));
			var day = parseInt(diff / nd);
			if (day < 11) {
				$('#tdtime' + (i + 1)).css('background-color','red');
			}
		}
	}
	*/
	
	public static long time(Date startTime){
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss"); 
		//long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		//long ns = 1000;// 一秒钟的毫秒数
		long diff = new Date().getTime() - startTime.getTime();
		long minute = diff % nh / nm;
		System.out.println(minute);
		
		
		/* Calendar c1=Calendar.getInstance();
         Calendar c2=Calendar.getInstance();
         Calendar c3=Calendar.getInstance();
         //Date date=new Date();
         c1.setTime(new Date());//要判断的日期
         c2.setTime(new Date());//初始日期
         c3.setTime(new Date());//也给初始日期 把分钟加五
         c3.add(c3.MINUTE, 5);
         c2.add(c2.MINUTE,-5);//减去五分钟
         System.out.println(c1.getTime());
         System.out.println(c2.getTime());
         System.out.println(c3.getTime());
         if(c1.after(c2)&&c1.before(c3)){
             System.out.println("五分钟之内");
         }else {
             System.out.println("五分钟之外");
         }*/
		return 0;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			Date time = sdf.parse("2016-04-14 19:10:00");
			time(time);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	/*public static int time(Date startTime){
		
		
		 Calendar c1=Calendar.getInstance();
         Calendar c2=Calendar.getInstance();
         Calendar c3=Calendar.getInstance();
         //Date date=new Date();
         c1.setTime(new Date());//要判断的日期
         c2.setTime(new Date());//初始日期
         c3.setTime(new Date());//也给初始日期 把分钟加五
         c3.add(c3.MINUTE, 5);
         c2.add(c2.MINUTE,-5);//减去五分钟
         System.out.println(c1.getTime());
         System.out.println(c2.getTime());
         System.out.println(c3.getTime());
         if(c1.after(c2)&&c1.before(c3)){
             System.out.println("五分钟之内");
         }else {
             System.out.println("五分钟之外");
         }
		return 0;
	}*/
}