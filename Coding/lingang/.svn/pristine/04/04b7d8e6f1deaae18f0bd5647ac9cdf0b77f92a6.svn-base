package com.lingang.common.util;

/**
 * 				
* @ClassName: InvitationCodeUtils
* @Description: 生成邀请码(推荐码)工具类
*
 */
public class InvitationCodeUtils {
	
	private static final char[] SCALE_DIGITS = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	    
	    public static String createInvitationCode(int n){
	    	String invitationCode = ToNumberSystem35(n);
	    	return invitationCode;
	    }
	    //商家
	    public static String createInvitationCodeByShopEmploy(int n){
	    	String invitationCode = ToNumberSystem(n);
	    	return invitationCode;
	    }
	    /**
	     * 将整数转化为35进制
	     * @param n
	     * @return
	     */
	     private static String ToNumberSystem35(int n){
			  int c = SCALE_DIGITS.length;
			  String s = "";
		      while (n > 0){
		          int m = n % c;
		          if (m == 0) m = c;
		          s = SCALE_DIGITS[m-1] + s;
		          n = (n - m) / c;
		      }
		      s=StringUtils.rightPad(s, 5, '0');
		      return s;
		  } 
	    
	    /**
	     * 商户端，以0开头
	     * 将整数转化为35进制
	     */
	     private static String ToNumberSystem(int n){
			  int c = SCALE_DIGITS.length;
			  String s = "";
		      while (n > 0){
		          int m = n % c;
		          if (m == 0) m = c; 
		          s = SCALE_DIGITS[m-1] + s;
		          n = (n - m) / c;
		      }
		      s="0"+s;
		      s=StringUtils.rightPad(s, 5, '0');
		      return s;
	     } 
}
