package com.lingang.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.lingang.common.constants.CommonConstants;


public class AmountUtils {
	private static final Logger logger = Logger.getLogger(AmountUtils.class);

	public static final String DEFAULT_MANTISSA_PROCESS = "DEFAULT_MANTISSA_PROCESS";
	 /**金额为分的格式 */  
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+"; 
	/**
	 * 金额格式转换
	 * 
	 * @param o
	 * @return
	 */
	public static String format(Object o){
		if(o == null){
			return null;
		}
		if("".equals(o)){
			return "";
		}
		//金额小数尾数
		int d = Integer.valueOf(CommonConstants.AMOUNT_DECIMAL);
		String temp = String.valueOf((int)Math.pow(10,d)).replace("1", "");
		DecimalFormat format = null;
		if(temp.length() == 0){
			format = new DecimalFormat("#,##0.#");
		}else{
			format = new DecimalFormat("#,##0." + temp);
		}
		format.setRoundingMode(RoundingMode.HALF_UP);
		//double amount = Double.valueOf(String.valueOf(o));
		return format.format(new BigDecimal(String.valueOf(o)));
	}
	
	/**
	 * Byte转换成int类型
	 * 为空时返回-1
	 * 
	 * @Title: byteToInt
	 * @Description: TODO
	 * @param b
	 * @return
	 * @author 周成龙
	 */
	public static int byteToInt(Byte b) {
		int i = -1;
		if(b != null)
			i = b.intValue();
		
		return i;
	}

	/**
	 * 数字保留两位小数
	 * @param o
	 * @return
	 */
	public static String numFormat(Object o){
		String str = "";
		str = format(o);
		if(str != null){
			str = str.replaceAll(",", "");
		}
		return str;
	}
	
	/**
	 * 数字保留一位小数
	 * @param o
	 * @return
	 */
	public static String numbFormat(BigDecimal number){
		String str = "";
		str = number.setScale(1,BigDecimal.ROUND_HALF_UP).toString();
		return str;
	}
	
	/**
	 * 数字保留到整数位
	 * @param o
	 * @return
	 */
	public static String numInt(Object o){
		String str = "";
		str = format(o);
		if(str != null){
			str = str.replaceAll(",", "");
			str = str.split("\\.")[0];
		}
		return str;
	}
	
	/**
	 * 金额格式转换
	 * 
	 * @param o
	 * @return
	 */
	public static Double parseToDouble(String amout){
		if(amout == null) return null;
		String s = amout.replaceAll(",", "");
		return Double.valueOf(s);
	}
	
	
	//将很长的小数格式掉
	public static double foramtDouble(Object o){
		if (o ==null || o.equals("")){
			return 0;
		}
		String amount = format(o.toString().replace(",", ""));
		return parseToDouble(amount).doubleValue();
	}

	//将很长的小数格式掉
	public static BigDecimal foramtBigDecimal(Object o){
		return BigDecimal.valueOf(foramtDouble(o));
	}

	/**
	 * 金额格式转换
	 * 
	 * @param o
	 * @return
	 */
	public static BigDecimal parseToBigDecimal(String amout){
		if(amout == null) return null;
		return BigDecimal.valueOf(parseToDouble(amout));
	}
	

	/**
	 * 位数处理
	 * 
	 * @param number
	 * @param mantissaProcess
	 * 		1: 舍厘见分进角
	 * 		2: 四舍五入到分
	 * 		3: 四舍五入到角
	 * 		4: 四舍五入到圆
	 * @return
	 */
	public static BigDecimal mantissaProcessNum(BigDecimal number, String mantissaProcess){
		if(number == null)
			return null;
		BigDecimal newNumber = number;
		if(CommonConstants.MANTISSA_PROCESS_1.equals(mantissaProcess)){
			// 舍厘见分进角
			newNumber = number.setScale(2, BigDecimal.ROUND_DOWN);		// 舍去分以后的位数
			newNumber = newNumber.multiply(BigDecimal.TEN);
			newNumber = newNumber.divide(BigDecimal.TEN, 1, BigDecimal.ROUND_CEILING);
			logger.debug(Math.ceil(newNumber.doubleValue()*10)/10);
		}else if (CommonConstants.MANTISSA_PROCESS_2.equals(mantissaProcess)){
			// 四舍五入到分
			newNumber = number.setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if (CommonConstants.MANTISSA_PROCESS_3.equals(mantissaProcess)){
			// 四舍五入到角
			newNumber = number.setScale(1, BigDecimal.ROUND_HALF_UP);
		}else if (CommonConstants.MANTISSA_PROCESS_4.equals(mantissaProcess)){
			// 四舍五入到圆
			newNumber = number.setScale(0, BigDecimal.ROUND_HALF_UP);
		}else if (DEFAULT_MANTISSA_PROCESS.equals(mantissaProcess)){
			// 系统默认位数位处理
			int d = Integer.valueOf(CommonConstants.AMOUNT_DECIMAL);
			newNumber = number.setScale(d, BigDecimal.ROUND_HALF_UP);
		}
		
		return newNumber;
	}
	
	
	/**
	 * 验证是否为有效金额
	 * 
	 * @param amount
	 * @return
	 */
	public static boolean valid(String amount){
		Pattern pattern = Pattern.compile("^(-)?(\\d*)(\\.(\\d*))?$");
		Matcher matcher = pattern.matcher(amount); 
		return matcher.matches();
	}
	
	/**
	 * BigDecimal值累加
	 * 
	 * @param b
	 * @param item
	 * @return
	 */
	public static BigDecimal sumBigDecimal(BigDecimal b, BigDecimal... item){
		if(b == null) return null;
		BigDecimal r = b;
		if(item != null){
			for(BigDecimal i : item){
				r = r.add(i);
			}
		}
		return r;
	}
	
	public static BigDecimal simpleSubtractBigDecimal(BigDecimal a, BigDecimal b) {
		return subtractBigDecimal(a, b);
	}
	
	/**
	 * BigDecimal值累加 null转0
	 * 
	 * @param b
	 * @param item
	 * @return
	 */
	public static BigDecimal addBigDecimal(BigDecimal b, BigDecimal... item){
		BigDecimal r = BigDecimal.ZERO;
		if(b != null)
		{
			r = b;
		}
		
		if(item != null){
			for(BigDecimal i : item){
				if(i != null){
					r = r.add(i);
				}
			}
		}
		return r;
	}
	
	/**
	 * BigDecimal值累减
	 * 
	 * @param b
	 * @param item
	 * @return
	 */
	public static BigDecimal subtractBigDecimal(BigDecimal b, BigDecimal... item){
		if(b == null) return null;
		BigDecimal r = b;
		if(item != null){
			for(BigDecimal i : item){
				r = r.subtract(i);
			}
		}
		return r;
	}
	
	/**
	* @Title: hideData
	* @Description: 隐藏手机号部分数字
	* @param data 手机号
	* @return 隐藏手机号部分数字后的数据
	* @author 吴鹏   wu.peng914@chinaredstar.com
	*/
	public static String hidePhone(String data){
		if(data == null){
			return "";
		}else if(data.length() <= 7){
			return data;
		}
		return data.substring(0, 3)+"****"+data.substring(data.length() - 4, data.length()); 
	}
	
	/**
	 * 去除金额尾部0
	 * @param o
	 * @return
	 */
	public static String formatAmount(Object o){
		
		if (o ==null || o.equals("")){
			return "0";
		}
		
		BigDecimal result = new BigDecimal(o.toString());
		
		if(BigDecimal.ZERO.compareTo(result) == 0){
			return "0";
		}
		
		return result.stripTrailingZeros().toPlainString();
	}
	
	/**
	 * compareTo比较arg1和arg2 ，NULL做0 比较
	 * @param arg1
	 * @param arg2
	 * @return arg1>arg2 => 1
	 */
	public  static int compareTo(BigDecimal arg1,BigDecimal arg2) {
		if(arg1 == null){
			arg1 = BigDecimal.ZERO;
		}
		if (arg2 == null) {
			arg2 = BigDecimal.ZERO;
		}
		return arg1.compareTo(arg2);
	}
	
	/**
	 * arg1乘以arg2 ，NULL返回0 
	 * @param arg1
	 * @param arg2
	 * @return 
	 */
	public  static BigDecimal multiply(BigDecimal arg1,BigDecimal arg2) {
		if(arg1 == null){
			return BigDecimal.ZERO;
		}
		if (arg2 == null) {
			return BigDecimal.ZERO;
		}
		return arg1.multiply(arg2);
	}
	 /**  
     * 将分为单位的转换为元并返回金额格式的字符串 （除100） 
     *  
     * @param amount 
     * @return 
     * @throws Exception  
     */  
    public static String changeF2Y(Long amount) throws Exception{  
        if(!amount.toString().matches(CURRENCY_FEN_REGEX)) {  
            throw new Exception("金额格式有误");  
        }  
          
        int flag = 0;  
        String amString = amount.toString();  
        if(amString.charAt(0)=='-'){  
            flag = 1;  
            amString = amString.substring(1);  
        }  
        StringBuffer result = new StringBuffer();  
        if(amString.length()==1){  
            result.append("0.0").append(amString);  
        }else if(amString.length() == 2){  
            result.append("0.").append(amString);  
        }else{  
            String intString = amString.substring(0,amString.length()-2);  
            for(int i=1; i<=intString.length();i++){  
                if( (i-1)%3 == 0 && i !=1){  
                    result.append(",");  
                }  
                result.append(intString.substring(intString.length()-i,intString.length()-i+1));  
            }  
            result.reverse().append(".").append(amString.substring(amString.length()-2));  
        }  
        if(flag == 1){  
            return "-"+result.toString();  
        }else{  
            return result.toString();  
        }  
    }  
	/** 
     * 将分为单位的转换为元 （除100） 
     *  
     * @param amount 
     * @return 
     * @throws Exception  
     */  
    public static String changeF2Y(String amount) throws Exception{  
        if(!amount.matches(CURRENCY_FEN_REGEX)) {  
            throw new Exception("金额格式有误");  
        }  
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();  
    }  
      
    /**  
     * 将元为单位的转换为分 （乘100） 
     *  
     * @param amount 
     * @return 
     */  
    public static String changeY2F(Long amount){  
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();  
    }  
      
    /**  
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额 
     *  
     * @param amount 
     * @return 
     */  
    public static String changeY2F(String amount){  
    	if(amount==null||amount.equals(""))amount="0";
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额  
        int index = currency.indexOf(".");  
        int length = currency.length();  
        Long amLong = 0l;  
        if(index == -1){  
            amLong = Long.valueOf(currency+"00");  
        }else if(length - index >= 3){  
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));  
        }else if(length - index == 2){  
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);  
        }else{  
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");  
        }  
        return amLong.toString();  
    }  
}
