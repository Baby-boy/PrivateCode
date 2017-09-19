package com.glanway.hr.dms.service.schedule.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.mail.EmailException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.glanway.hr.dms.dao.schedule.ScheduleDao;
import com.glanway.hr.dms.dao.schedule.SchedulingDao;
import com.glanway.hr.dms.dao.workway.WorkWayDao;
import com.glanway.hr.dms.entity.employee.Employee;
import com.glanway.hr.dms.entity.schedule.Schedule;
import com.glanway.hr.dms.entity.schedule.Scheduling;
import com.glanway.hr.dms.entity.vo.EmployeeViewVo;
import com.glanway.hr.dms.entity.workway.WorkWay;
import com.glanway.hr.dms.service.schedule.ScheduleService;
import com.glanway.hr.dms.utils.FestivalUtil;
import com.glanway.hr.dms.utils.MailSender;
import com.glanway.hr.dms.utils.TimeUtil;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);

	@Autowired
	private ScheduleDao scheduleDao;

	@Autowired
	private SchedulingDao schedulingDao;

	@Autowired
	private WorkWayDao workWayDao;

	@Autowired
	private FestivalUtil festivalUtil;

	@Autowired
	private MailSender mailSender;

	@Override
	public void syncSchedule(Integer year, Integer month, Integer day, Integer monthNum) {
		if (null == monthNum) {
			monthNum = 0;
		}

		// 对数据进行处理
		String startDate = year + "-" + month;// 筛选DMS系统排班数据的开始时间
		Integer sameMonth = month + monthNum;
		if (sameMonth % 12 != 0) {
			year = year + sameMonth / 12;
			sameMonth = sameMonth % 12;
		} else {
			sameMonth = 12;
		}
		String endDate = year + "-" + sameMonth;// 筛选DMS系统排班数据的结束时间

		// 获取今年今天以后在HR系统中有编制的在职人员排班(编制的工作制为不定时工作制和综合工作制)
		List<Scheduling> schedulings = schedulingDao.findSchedulingList(startDate, endDate);
		for (Scheduling scheduling : schedulings) {
			Integer sameMonthDay = day;
			Integer lastDay = TimeUtil.getOnlyMonthLastDay(Integer.valueOf(scheduling.getYear()),
					Integer.valueOf(scheduling.getMonth()));

			// 如果不是传入的月份,则可能同步到下个月了,所以天数直接为第一天
			if (month != Integer.valueOf(scheduling.getMonth())) {
				sameMonthDay = 1;
			}

			// 查询当月此人排班数据不存在,则从一号重新获取数据
			List<Schedule> scheduleList = scheduleDao.findScheduleByMonth(scheduling.getEmployeeId().toString(), year,
					month);
			if (null == scheduleList || scheduleList.size() == 0) {
				sameMonthDay = 1;
			}

			Map<Integer, Long> map = getMap(scheduling);
			Set<Integer> keySet = map.keySet();
			Iterator<Integer> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				Integer key = iterator.next();
				if (key < sameMonthDay || key > lastDay) {
					iterator.remove();
				}
			}

			// 根据员工Code获取该职员是否已经存在排班信息
			List<Schedule> schedules = scheduleDao.findSchedule(scheduling.getEmployeeId().toString(),
					Integer.valueOf(scheduling.getYear()), Integer.valueOf(scheduling.getMonth()), sameMonthDay);
			for (Schedule schedule : schedules) {
				Integer key = schedule.getDay();
				if (keySet.contains(key)) {
					// 说明今天排班已经被同步
					if (schedule.getWorkWayId().longValue() != map.get(key).longValue()) {
						schedule.setWorkWayId(map.get(key));
						if (null != scheduling.getModifier()) {
							schedule.setLastModifiedBy(scheduling.getModifier().longValue());
						}
						schedule.setLastModifiedDate(scheduling.getModifyDt());
						scheduleDao.updateByPrimaryKeySelective(schedule);
					}

					keySet.remove(key);
				}
			}

			// 上边已经把不符合要求和已同步的key删除了,剩下的则为未同步的数据
			for (Integer key : keySet) {
				Schedule schedule = new Schedule();
				schedule.setEmployeeCode(scheduling.getEmployeeId().toString());
				schedule.setYear(scheduling.getYear().intValue());
				schedule.setMonth(scheduling.getMonth().intValue());
				schedule.setDay(key);
				schedule.setWorkWayId(map.get(key));
				schedule.setBatchDate(new Date());
				schedule.setCreProId(1L);// 写死
				if (null != scheduling.getCreator()) {
					schedule.setCreatedBy(scheduling.getCreator().longValue());
				}
				schedule.setCreatedDate(scheduling.getCreateDt());
				schedule.setModProId(1L);// 写死
				if (null != scheduling.getModifier()) {
					schedule.setLastModifiedBy(scheduling.getModifier().longValue());
				}
				schedule.setLastModifiedDate(scheduling.getModifyDt());
				scheduleDao.insertSelective(schedule);
			}
		}
	}

	@Override
	public void syncStandardWorkSystemSchedule(Integer year, Integer month, Integer day, Integer monthNum) {
		// DMS系统的表数据已经同步完成,所以以下是对国家规定的工作日上班的人员排班计划(标准工作制的员工只排当月的时间)
		if (null == monthNum) {
			monthNum = 0;
		}

		// 获取HR系统中有编制的在职人员(编制的工作制为标准工作制)
		List<Employee> employees = scheduleDao.findEmployeeByWorkSystem();
		for (Employee employee : employees) {
			Integer sameMonthDay = day;
			Integer sameMonthLastDay = TimeUtil.getOnlyMonthLastDay(year, month);
			Integer sameYear = year;

			for (Integer sameMonth = month; sameMonth <= month + monthNum; sameMonth++) {
				if (sameMonth != month) {
					sameMonthDay = 1;
				}

				if (sameMonth % 12 != 0) {
					sameYear = year + sameMonth / 12;
					sameMonth = sameMonth % 12;
				} else {
					sameMonth = 12;
				}
				sameMonthLastDay = TimeUtil.getOnlyMonthLastDay(sameYear, sameMonth);

				List<Schedule> scheduleList = scheduleDao.findSchedule(employee.getCode(), sameYear, sameMonth,
						sameMonthDay);
				List<Integer> workDayList = new ArrayList<>();// 存储工作日
				List<Integer> restList = new ArrayList<>();// 存储休息日
				for (int i = 0; i <= sameMonthLastDay - sameMonthDay; i++) {
					Date date = TimeUtil.getDayBeforeOrDayAfter(TimeUtil.getToday(sameYear, sameMonth, sameMonthDay),
							i);
					String dayStr = TimeUtil.formatPattern(date, TimeUtil.FORMAT_DD);
					if (dayStr.startsWith("0")) {
						dayStr = dayStr.substring(1, dayStr.length());
					}

					if (festivalUtil.isWorkDay(date)) {// 工作日
						workDayList.add(Integer.valueOf(dayStr));
					} else {
						restList.add(Integer.valueOf(dayStr));
					}
				}

				// 判断表中是否已经有该职员的排班信息
				for (Schedule schedule : scheduleList) {
					Integer key = schedule.getDay();
					if (workDayList.contains(key)) {
						// 说明今天排班已经被同步,不做任何处理,如果有请假申请,需要同步更新当前的排班workWayID.
						workDayList.remove(key);
					} else if (restList.contains(key)) {
						restList.remove(key);
					}
				}

				// 上边已经把不符合要求和已同步的key删除了,剩下的则为未同步的数据
				for (Integer key : workDayList) {
					Schedule schedule = new Schedule();
					schedule.setEmployeeCode(employee.getCode());
					schedule.setWorkWayId(10005008L);// 标准班
					schedule.setDay(Integer.valueOf(key));
					insertSchedule(schedule, sameYear, sameMonth);
				}
				for (Integer key : restList) {
					Schedule schedule = new Schedule();
					schedule.setEmployeeCode(employee.getCode());
					schedule.setWorkWayId(10050101L);// 公休
					schedule.setDay(Integer.valueOf(key));
					insertSchedule(schedule, sameYear, sameMonth);
				}
			}
		}
	}

	private void insertSchedule(Schedule schedule, Integer sameYear, Integer sameMonth) {
		schedule.setYear(sameYear);
		schedule.setMonth(sameMonth);
		schedule.setBatchDate(new Date());
		schedule.setCreProId(1L);// 写死
		schedule.setCreatedBy(1L);
		schedule.setCreatedDate(new Date());
		schedule.setModProId(1L);// 写死
		schedule.setLastModifiedBy(1L);
		schedule.setLastModifiedDate(schedule.getCreatedDate());
		scheduleDao.insertSelective(schedule);
	}

	private Map<Integer, Long> getMap(Scheduling scheduling) {
		Map<Integer, Long> map = new HashMap<>();
		try {
			for (Integer day = 1; day <= 31; day++) {
				Field field = scheduling.getClass().getDeclaredField("day" + day);
				field.setAccessible(true);
				Long workWayId = (Long) field.get(scheduling);

				if (null != workWayId) {
					// 根据勤务方式ID查询,如果排班为非groupName=3(组名为3的是有上班的勤务方式),则作为公休
					WorkWay workWay = workWayDao.findDutyWorkWay(workWayId, "3");
					if (null != workWay) {
						map.put(day, workWayId);
					} else {
						map.put(day, 10050101L);
					}
				} else {
					map.put(day, 10050101L);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@Override
	public void checkSchedule() {
		String today = TimeUtil.format(TimeUtil.getToday());
		String todayAfter = TimeUtil.format(TimeUtil.getDayBeforeOrDayAfter(TimeUtil.getToday(), 5));

		// 查询所有未来五天没有排班的员工
		List<EmployeeViewVo> employees = scheduleDao.findAllEmployee(today, todayAfter);
		Integer num = 0;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("未排班人员信息");
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("职员编号");
			row.createCell(1).setCellValue("姓名");
			row.createCell(2).setCellValue("部门");
			row.createCell(3).setCellValue("职位");

			for (EmployeeViewVo employee : employees) {
				XSSFRow rowData = sheet.createRow(++num);
				rowData.createCell(0).setCellValue(employee.getCode());
				rowData.createCell(1).setCellValue(employee.getName());
				rowData.createCell(2).setCellValue(employee.getDeptName());
				rowData.createCell(3).setCellValue(employee.getJobName());
			}

			FileOutputStream outputStream = new FileOutputStream(new File("src/main/resources/schedule.xlsx"));
			workbook.write(outputStream);
			outputStream.close();

			String content = "\n--------------------------------" + "\nIT管理\n\nYAMATO(CHINA) TRANSPORT CO., LTD."
					+ "\nMobile：021-58371622" + "\nEmail：" + mailSender.sendFrom
					+ "\nAdd：上海市松江区九亭镇伴亭路418号\nZipCode：201615\nHP：http://sh.cn.ta-q-bin.com/";

			if (num.longValue() == 0) {
				content = "人事课：\n    未来五天无排班信息异常情况, 请悉知." + content;
				mailSender.sendEmail("雅玛多项目 | 员工未排班情况统计", content, null, null);
			} else {
				content = "人事课：\n    附件中是未来五天内无排班信息的员工信息, 共计" + num + "人, 请悉知." + content;
				mailSender.sendEmail("雅玛多项目 | 员工未排班情况统计", content, "未排班人员信息.xlsx",
						new File("src/main/resources/schedule.xlsx"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("发送邮件失败!异常信息为: {}", e.getMessage());
		}
	}
}
