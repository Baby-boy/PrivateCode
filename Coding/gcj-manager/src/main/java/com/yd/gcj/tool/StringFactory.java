package com.yd.gcj.tool;

public class StringFactory {
	
	/***
	 * 将字符串的指定字符去掉
	 * @param str原字符串
	 * @param ss指定字符合集
	 * @return
	 */
	public static String delSymbol(String str,String ss){
		char cs[] = ss.toCharArray();
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
        	int is = 1;
        	char c = str.charAt(i);
        	for(char s : cs){
        		if(s==c){
        			is = 0;
        		}
        	}
        	if(is==1){
        		sb.append(str.charAt(i));
        	}
        }
        
        return sb.toString();
	}
	
	/***
	 * 将字符串的默认特殊字符去掉：.><!@#$%^&*()~`‘';:
	 * @param str原字符串
	 * 
	 */
	public static String delSymbol(String str){
		char cs[] = ".><!@#$%^&*()~`‘';:".toCharArray();
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
        	int is = 1;
        	char c = str.charAt(i);
        	for(char s : cs){
        		if(s==c){
        			is = 0;
        		}
        	}
        	if(is==1){
        		sb.append(str.charAt(i));
        	}
        }
        
        return sb.toString();
	}
	
	/***
	 * 提取中文字符
	 * @param str原字符串
	 * @return
	 */
	public static String getChaniese(String str){
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
        	int is = (str.charAt(i)+"").getBytes().length;
        	if(is>1){
        		sb.append(str.charAt(i));
        	}
        }
        return sb.toString();
	}
	
	/***
	 * 检测是否有指定字符
	 * @param str原字符串
	 * @param ss指定字符（如：。》《）
	 * @return
	 */
	public static boolean isSymbol(String str,String ss){
		boolean is = false;
		char cs[] = ss.toCharArray();
        in:for (int i = 0; i < str.length(); i++) {
        	char c = str.charAt(i);
        	for(char s : cs){
        		if(s==c){
        			is = true;
        			break in;
        		}
        	}
        }
		return is;
	}
	
	/***
	 * 检测是否有默认特殊字符：.><!@#$%^&*()~`‘';:
	 * @param str原字符串
	 * @return
	 */
	public static boolean isSymbol(String str){
		boolean is = false;
		char cs[] = ".><!@#$%^&*()~`‘';:".toCharArray();
        in:for (int i = 0; i < str.length(); i++) {
        	char c = str.charAt(i);
        	for(char s : cs){
        		if(s==c){
        			is = true;
        			break in;
        		}
        	}
        }
		return is;
	}
	
	/***
	 * 字符替换
	 * @param str 原字符
	 * @param chars 需替换的键值对字符集 如（需要将“(”替换成“[”） 则值为："(["
	 * @return
	 */
	public static String charReplace(String str,String chars){
		char cs[] = chars.toCharArray();
		StringBuffer sb = new StringBuffer();
		
        for (int i = 0; i < str.length(); i++) {
        	char c = str.charAt(i);
        	for(int j = 0;j<cs.length;j+=2){
        		if(cs[j]==c){
        			c = cs[j+1];
        			break;
        		}
        	}
        	sb.append(c);
        }
        
        return sb.toString();
	}
	
	/***
	 * 字符替换含去空格及换行符
	 * @param str 原字符
	 * @param chars 需替换的键值对字符集 如（需要将“(”替换成“[”） 则值为："(["
	 * @return
	 */
	public static String charReplaceAndDelEntAndSpa(String str,String chars){
		char cs[] = chars.toCharArray();
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
        	char c = str.charAt(i);
        	for(int j = 0;j<cs.length;j+=2){
        		if(cs[j]==c){
        			c = cs[j+1];
        			break;
        		}
        	}
        	sb.append(c);
        }
        str = sb.toString();
        str = str.replace("\n", "");
        str = str.replace(" ", "");
        return str;
	}
}
