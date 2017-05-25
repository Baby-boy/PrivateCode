package com.lingang.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
/**
 * 
 * @Description:导入Excel数据文本规范检查
 * @Author: 
 * @Since:2016年3月10日 下午3:36:02
 * @Version:1.0
 */
public class InspectionUtils {

	/**
	 * 日期格式，精确到秒的标准格式
	 */
	public final static String YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 分隔符--斜杠(/)
	 */
	public final static String SEPARATOR_SLASH = "/";

	/**
	 * 检查不能为空的文本
	 * 
	 * @author 
	 * @param text
	 *            检查的文本
	 * @param length
	 *            检查文本限制长度
	 * @param errStr
	 *            错误提示
	 * @param errList
	 *            错误列表，用来添加错误（该参数不为空）
	 * @return 检查结果是否规范
	 */
	public static boolean checkNotEmptyText(String text, int length, String errStr, List<String> reasons) {
		boolean checkResult = true;

		if (StringUtils.isEmpty(text)) {
			String errReason = "导入失败，Excel中编号:" + errStr + "为空！";
			reasons.add(errReason);

			checkResult = false;
		} else if (text.length() > length) {
			String errReason = "导入失败，Excel中编号:" + errStr + "文本长度大于规定长度，标准文本长度为：" + length + "！";
			reasons.add(errReason);

			checkResult = false;
		}

		return checkResult;
	}

	/**
	 * 检查不可以为空的文本是否为给定的日期格式
	 * 
	 * @author 
	 * @param text
	 *            检查的文本
	 * @param dateFormat
	 *            检查的日期格式
	 * @param errStr
	 *            错误提示
	 * @param errList
	 *            错误列表，用来添加错误（该参数不为空）
	 * @return 检查结果是否规范
	 */
	public static boolean checkNotEmptyDate(String text, String dateFormat, String errStr, List<String> reasons) {
		boolean checkResult = true;
		if (StringUtils.isEmpty(text)) {
			String errReason = "导入失败，Excel中编号:" + errStr + "为空！";
			reasons.add(errReason);
			return false;
		}
		DateFormat df = new SimpleDateFormat(dateFormat);
		df.setLenient(false);
		try {
			df.parse(text);
		} catch (Exception e) {
			checkResult = false;
			String errReason = "导入失败，Excel中编号:" + errStr + "日期格式不符合规范，不是" + dateFormat + "格式！";
			reasons.add(errReason);
		}
		return checkResult;
	}

	/**
	 * 检查可以为空的文本是否为数字
	 * @author 
	 * @param text 检查的文本
	 * @param length 检查文本限制长度
	 * @param errStr 错误提示
	 * @param errList 错误列表，用来添加错误（该参数不为空）
	 * @return 检查结果是否规范
	 */
	public static boolean checkEmptyNumber(String text,String errStr, List<String> reasons){
		boolean checkResult = true;
		if(!StringUtils.isStrNull(text) && !NumberUtils.isNumber(text)){
			String errReason = "导入失败，Excel中编号:"+errStr+"数据不符合规范，不是数字类型！";
			reasons.add(errReason);
			checkResult = false;
		}
		return checkResult;
	}
	
	/**
	 * 拼接错误消息
	 * 
	 * @param titleNumber
	 *            Excel行编号
	 * @param titleName
	 *            Excel表头
	 * @return 拼接后的消息字符串
	 */
	public static String spliceErrStr(String titleNumber, String titleName) {
		return titleNumber + "的【" + titleName + "】";
	}

	public static void main(String[] args) {
		/*
		 * System.out.println("11,".lastIndexOf(",") - 1);
		 * System.out.println(ArrayUtils.contains(new String[]{"是","否"}, "天"));
		 * System.out.println(StringUtils.join(new String[]{"是","否"}, "/"));
		 * System.out.println(NumberUtils.isNumber("10.2"));
		 * System.out.println(checkNotEmptyDate("2013-2-3 131:13:16",
		 * YYYY_MM_DD_HH_mm_ss, "1[时间]", new ArrayList<String>()));
		 * System.out.println(checkMultipleSelectedText("卧室/厨房/客厅s",
		 * SEPARATOR_SLASH , new String[]{"卧室", "厨房" , "客厅" , "卫浴" ,"书房" ,"阳台"},
		 * "[适用房间*]", new ArrayList<String>()));
		 */
	/*	Set<String> set = new HashSet<String>();
		set.add("你好");
		set.add("你好");

		Set<Integer> set2 = new HashSet<Integer>();
		set2.add(1);
		set2.add(1);*/
		if(!StringUtils.isStrNull("32") && !NumberUtils.isNumber("32.0")){
			String errReason = "导入失败，数据不符合规范，不是数字类型！";
			System.err.println(errReason);
		}else{
			System.out.println("OK");
		}
	}
}
