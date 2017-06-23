package com.tadpole.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;

public abstract class FestivalUtil {

	private String FILE_NAME;

	@Value("${festival.fileName}")
	private void setURL(String fileName) {
		FILE_NAME = fileName;
	}

	private List<Date> festivalList = new ArrayList<Date>();// 节假日
	private List<Date> workDayList = new ArrayList<Date>();// 工作日

	public FestivalUtil() {
		try {
			FileInputStream fin = new FileInputStream(new File(FILE_NAME));
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fin);
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			int last = sheet.getLastRowNum();
			int index = 1;
			Date dt = null;
			while (index <= last) {
				XSSFRow row = sheet.getRow(index);

				/* 读取法定节假日 */
				XSSFCell cell = row.getCell((short) 0);
				if (cell != null) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						if (dt != null && dt.getTime() > 0) {
							festivalList.add(dt);
						}
					}
				}

				/* 读取特殊工作日 */
				cell = row.getCell((short) 1);
				if (cell != null) {
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						if (dt != null && dt.getTime() > 0) {
							workDayList.add(dt);
						}
					}
				}

				index++;
			}
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断一个日期是否是法定节假日,只判断月份和天,不判断年(每年的法定节假日是变化的,需要手动导入).
	 *
	 * @param date(日期)
	 * @return true代表是法定节假日,false代表非法定节假日
	 * @author FUQIHAO
	 * @dateTime 2017年6月23日 上午10:32:14
	 */
	private boolean isFestival(Date date) {
		boolean festival = false;
		Calendar dcal = Calendar.getInstance();
		dcal.setTime(date);
		for (Date dt : this.festivalList) {
			Calendar fcal = Calendar.getInstance();
			fcal.setTime(dt);

			// 法定节假日判断
			if (fcal.get(Calendar.MONTH) == dcal.get(Calendar.MONTH)
					&& fcal.get(Calendar.DATE) == dcal.get(Calendar.DATE)) {
				festival = true;
			}
		}
		return festival;
	}

	/**
	 * 判断一个日期是否是周末.
	 *
	 * @param date(日期)
	 * @return true代表是周末,false代表非周末
	 * @author FUQIHAO
	 * @dateTime 2017年6月23日 上午10:30:46
	 */
	private boolean isWeekend(Date date) {
		boolean weekend = false;
		Calendar dcal = Calendar.getInstance();
		dcal.setTime(date);
		if (dcal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || dcal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			weekend = true;
		}

		return weekend;
	}

	/**
	 * 判断一个日期是否是工作日.
	 *
	 * @param date(日期)
	 * @return true代表工作日,false代表非工作日
	 * @author FUQIHAO
	 * @dateTime 2017年6月23日 上午10:29:00
	 */
	public boolean isWorkDay(Date date) {
		boolean workDay = true;
		if (isFestival(date) || isWeekend(date)) {
			workDay = false;
		}

		// 特殊工作日判断
		Calendar dcal = Calendar.getInstance();
		dcal.setTime(date);
		for (Date dt : this.workDayList) {
			Calendar wcal = Calendar.getInstance();
			wcal.setTime(dt);

			// 年月日相等为特殊工作日
			if (dcal.get(Calendar.YEAR) == wcal.get(Calendar.YEAR)
					&& dcal.get(Calendar.MONTH) == wcal.get(Calendar.MONTH)
					&& dcal.get(Calendar.DATE) == wcal.get(Calendar.DATE)) {
				workDay = true;
			}
		}

		return workDay;
	}

	/**
	 * 获取一个日期是类型.
	 *
	 * @param date(日期)
	 * @return 0代表工作日,1代表法定节假日,2代表周末,3代表特殊工作日
	 * @author FUQIHAO
	 * @dateTime 2017年6月23日 上午11:11:33
	 */
	public Integer workDayType(Date date) {
		Integer workDayType = 0;// 工作日
		if (isFestival(date)) {
			workDayType = 1;// 法定节假日
		}
		if (isWeekend(date)) {
			workDayType = 2;// 周末
		}

		// 特殊工作日判断
		Calendar dcal = Calendar.getInstance();
		dcal.setTime(date);
		for (Date dt : this.workDayList) {
			Calendar wcal = Calendar.getInstance();
			wcal.setTime(dt);

			// 年月日相等为特殊工作日
			if (dcal.get(Calendar.YEAR) == wcal.get(Calendar.YEAR)
					&& dcal.get(Calendar.MONTH) == wcal.get(Calendar.MONTH)
					&& dcal.get(Calendar.DATE) == wcal.get(Calendar.DATE)) {
				workDayType = 3;// 特殊工作日
			}
		}

		return workDayType;
	}

	/**
	 * 获取一个日期是类型名称.
	 *
	 * @param date(日期)
	 * @return 汉字代表的含义
	 * @author FUQIHAO
	 * @dateTime 2017年6月23日 上午11:12:29
	 */
	public String workDayName(Date date) {
		switch (workDayType(date)) {
		case 1:
			return "法定节假日";
		case 2:
			return "周末";
		case 3:
			return "特殊工作日";
		default:
			return "工作日";
		}
	}
}