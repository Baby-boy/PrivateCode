package com.glanway.hr.attendance.service.attendance.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.hr.attendance.dao.apply.ApplyDao;
import com.glanway.hr.attendance.dao.attendance.AttendanceDao;
import com.glanway.hr.attendance.dao.schedule.ScheduleDao;
import com.glanway.hr.attendance.dao.sign.SignDao;
import com.glanway.hr.attendance.entity.apply.Apply;
import com.glanway.hr.attendance.entity.attendance.Attendance;
import com.glanway.hr.attendance.entity.vo.ScheduleVo;
import com.glanway.hr.attendance.entity.vo.SignRecordVo;
import com.glanway.hr.attendance.service.BaseServiceImpl;
import com.glanway.hr.attendance.service.attendance.AttendanceService;
import com.glanway.hr.attendance.utils.FestivalUtil;
import com.glanway.hr.attendance.utils.TimeUtil;

/**
 * 说明 : 假勤相关业务接口实现层
 *
 * @author 于瑞智
 * @version 1.0.0
 * @dateTime 2017年4月21日 上午9:25:03
 */
@Transactional
@Service("attendanceService")
public class AttendanceServiceImpl extends BaseServiceImpl<Attendance> implements AttendanceService {

	@Autowired
	private ScheduleDao scheduleDao;

	@Autowired
	private SignDao signDao;

	@Autowired
	private AttendanceDao attendanceDao;

	@Autowired
	private ApplyDao applyDao;

	@Autowired
	private FestivalUtil festivalUtil;// 处理法定节假日的工具类

	@Override
	public int getAllLeave(Long employeeId, int year) {
		Map<String, Object> parmsMap = new HashMap<>();
		parmsMap.put("employeeId", employeeId);
		parmsMap.put("year", year);
		return attendanceDao.getAllLeave(parmsMap);
	}

	@Override
	public List<Attendance> getHourByDay(String date) {
		Map<String, Object> parmsMap = new HashMap<>();
		parmsMap.put("date", date);
		return attendanceDao.getHourByDay(parmsMap);
	}

	@Override
	public void dayAttendanceJob(Integer year, Integer month, Integer day) {
		/** 正常排班处理 */
		signHandleBySchedule(year, month, day);
	}

	private void signHandleBySchedule(Integer year, Integer month, Integer day) {
		// 默认的日期
		String defaultDate = year + "-" + month + "-" + day;

		// 先查询今天所有的排班信息
		List<ScheduleVo> schedules = scheduleDao.findAllEmployeeSchedule(year, month, day);
		for (ScheduleVo scheduleVo : schedules) {
			// 排班是休假,无每天开始时间和结束时间,直接跳过
			if (null == scheduleVo.getStartTime() && null == scheduleVo.getEndTime()) {
				continue;
			}

			// 拼接年月日获取时间字符串(排班的开始时间与结束时间)
			String scheduleWorkStart = defaultDate + " " + scheduleVo.getStartTime() + ":00";
			String scheduleWorkEnd = defaultDate + " " + scheduleVo.getEndTime() + ":00";

			// 获取排班时间的前四个小时作为打卡开始时间
			String workStart = TimeUtil.getTimeHourBeforeOrHourAfterFormat(TimeUtil.strToDateLong(scheduleWorkStart),
					-4);
			// 把开启打卡时间加一天作为打卡结束时间,形成一天的时间
			String workEnd = TimeUtil.formatLong(TimeUtil.getDayBeforeOrDayAfter(TimeUtil.strToDateLong(workStart), 1));

			// 根据员工排班需要打卡的时间段进行考勤记录筛选
			SignRecordVo maxAndMinTime = signDao.findSignByEmployeeCode(scheduleVo.getEmployeeCode(), workStart,
					workEnd);

			/** 法定节假日处理 */
			if (festivalUtil.isFestival(TimeUtil.strToDate(defaultDate))) {
				festivalHandle(scheduleVo, maxAndMinTime, scheduleWorkStart, scheduleWorkEnd, workStart, workEnd);

				/** 请假申请和签卡申请处理 */
				leaveApplyAndSignApplyHandle(scheduleVo, scheduleWorkStart, scheduleWorkEnd, workStart, workEnd);
				/** 平时加班申请处理 */
				normalOvertimeApplyHandle(scheduleVo, scheduleWorkStart, scheduleWorkEnd, workStart, workEnd);
				continue;
			}

			/** 普通工作日处理 */
			workDayHandle(scheduleVo, maxAndMinTime, scheduleWorkStart, scheduleWorkEnd, workStart, workEnd);

			/** 请假申请和签卡申请处理 */
			leaveApplyAndSignApplyHandle(scheduleVo, scheduleWorkStart, scheduleWorkEnd, workStart, workEnd);
			/** 平时加班申请处理 */
			normalOvertimeApplyHandle(scheduleVo, scheduleWorkStart, scheduleWorkEnd, workStart, workEnd);
		}

		/** 排班以外的申请都是加班申请 */
		weekendOverTimeAndHolidayOverTimeHandle(defaultDate);

		/** 加班异常签卡申请处理 */
		signApplyHandle(defaultDate);
	}

	private void signApplyHandle(String defaultDate) {

	}

	private void weekendOverTimeAndHolidayOverTimeHandle(String defaultDate) {
		// 查询所有周末加班和法定节假日加班申请
		// 拼接年月日获取时间字符串
		String defaultWorkStart = defaultDate + TimeUtil.START_TIME;
		String defaultWorkEnd = defaultDate + TimeUtil.END_TIME;
		// 查询所有周末加班和法定节假日加班
		List<Apply> applies = applyDao.findAllOvertimeApply(defaultWorkStart, defaultWorkEnd);
		for (Apply apply : applies) {
			// 获取排班时间的前四个小时作为打卡开始时间
			String workStart = TimeUtil.getTimeHourBeforeOrHourAfterFormat(apply.getStartTime(), -4);
			// 把开启打卡时间加一天作为打卡结束时间,形成一天的时间
			String workEnd = TimeUtil.formatLong(TimeUtil.getDayBeforeOrDayAfter(TimeUtil.strToDateLong(workStart), 1));

			// 根据员工排班需要打卡的时间段进行考勤记录筛选
			SignRecordVo maxAndMinTime = signDao.findSignByEmployeeCode(apply.getEmployeeCode(), workStart, workEnd);

			// 根据员工代码查询员工今天的日记录表(周末加班或者法定节假日的日记录数据)
			Attendance attendance = attendanceDao.findAttendance(apply.getEmployeeCode(), workStart, workEnd);
			if (null == attendance && null == maxAndMinTime.getMinTime()) {
				// 排班了,但是无考勤记录,记录为考勤异常
				attendance = new Attendance();
				attendance.setEmployeeCode(apply.getEmployeeCode());
				// 没有考勤记录,默认开始时间为排班时间,结束时间为排班结束时间
				attendance.setWorkStart(apply.getStartTime());
				attendance.setWorkEnd(apply.getEndTime());
				attendance.setWorkHour(0D);
				attendance.setWorkDay(0D);
				attendance.setType(apply.getType());// 申请的加班类型
				attendance.setIsException("Y");// 考勤异常
				attendance.setStatsType("N");// 未统计
				attendance = getInsertAttendance(attendance);
				// attendanceDao.insertSelective(attendance);
			} else if (null == attendance && null != maxAndMinTime.getMinTime()) {
				// 未创建考勤日记录,第一次创建
				attendance = new Attendance();
				attendance.setEmployeeCode(apply.getEmployeeCode());
				attendance.setWorkStart(maxAndMinTime.getMinTime());
				attendance.setWorkHour(0D);
				attendance.setWorkDay(0D);
				attendance.setType(apply.getType());// 平时加班
				attendance.setIsException("N");// 非考勤异常
				attendance.setStatsType("N");// 未统计
				attendance = getInsertAttendance(attendance);
				// attendanceDao.insertSelective(attendance);
			} else if (null != attendance && null != maxAndMinTime.getMinTime()) {
				if (maxAndMinTime.getMaxTime().compareTo(maxAndMinTime.getMinTime()) == 0) {
					// 说明只考勤了一次,更新考勤状态为异常
					attendance.setIsException("Y");// 考勤异常
					attendance = getUpdateAttendance(attendance);
					// attendanceDao.updateByPrimaryKeySelective(attendance);
				} else {
					// 说明存在两次以上的考勤记录
					float signTime = maxAndMinTime.getMaxTime().getTime() - maxAndMinTime.getMinTime().getTime();
					float overtimeTime = apply.getEndTime().getTime() - apply.getStartTime().getTime();// 申请平时加班时间
					if (signTime < 3 * 3600 * 1000) {
						// 两次考勤记录小于三个小时,算异常
						attendance.setIsException("Y");// 考勤异常
						attendance = getUpdateAttendance(attendance);
						// attendanceDao.updateByPrimaryKeySelective(attendance);
					} else if (signTime >= 3 * 3600 * 1000 && signTime <= 7 * 3600 * 1000) {
						// 考勤时间在三个小时以上并且在七个小时以内,算半天出勤
						attendance.setWorkEnd(maxAndMinTime.getMaxTime());
						attendance.setWorkHour(BigDecimal.valueOf((signTime) / (3600 * 1000))
								.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
						attendance.setWorkDay(0.5D);
						// attendanceDao.updateByPrimaryKeySelective(attendance);
					} else {
						// 全天出勤
						// 判断两者时间,那个短取哪个
						if (signTime > overtimeTime) {
							attendance.setWorkStart(apply.getStartTime());
							attendance.setWorkEnd(apply.getEndTime());
							attendance.setWorkHour(Double.parseDouble(String.valueOf(apply.getTotalWorkingHours())));
							// attendanceDao.updateByPrimaryKeySelective(attendance);
						} else {
							attendance.setWorkEnd(maxAndMinTime.getMaxTime());
							// TODO 这里有没有休息的时间
							attendance.setWorkHour(BigDecimal.valueOf((signTime) / (3600 * 1000))
									.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
							// attendanceDao.updateByPrimaryKeySelective(attendance);
						}
					}

				}
			}
		}
	}

	private void normalOvertimeApplyHandle(ScheduleVo scheduleVo, String scheduleWorkStart, String scheduleWorkEnd,
			String workStart, String workEnd) {
		// 查询是否存在平时加班申请(平时加班时间肯定在排班结束时间以后)
		List<Apply> overtimeApply = applyDao.findOvertimeApplyList(scheduleVo.getEmployeeCode(), scheduleWorkEnd,
				workEnd, "NO");
		for (Apply apply : overtimeApply) {
			// 查询考勤记录,加班的考勤开始时间为申请时间的前三十分钟(意思即为下班需要打一次卡,才能允许加班)
			workStart = TimeUtil.getTimeMinuteBeforeOrMinuteAfterFormat(apply.getStartTime(), -30);
			// 根据员工代码查询员工今天平时加班记录是否存在(这里存在一种超特殊的情况,就是加班的结束时间大于通过排班计算出来的一天的结束时间,如果出现这种情况,做特殊处理)
			// 特殊情况处理
			// 查询考勤记录,加班的考勤结束时间为申请时间的后三十分钟,做特殊处理
			workEnd = TimeUtil.getTimeMinuteBeforeOrMinuteAfterFormat(apply.getEndTime(), 30);
			SignRecordVo maxAndMinTime = signDao.findSignByEmployeeCode(scheduleVo.getEmployeeCode(), workStart,
					workEnd);
			Attendance attendance = attendanceDao.findAttendanceByEmployeeCode(scheduleVo.getEmployeeCode(), workStart,
					workEnd, "NO");
			if (null == attendance && null == maxAndMinTime.getMinTime()) {
				// 排班了,但是无考勤记录,记录为考勤异常
				attendance = new Attendance();
				attendance.setEmployeeCode(scheduleVo.getEmployeeCode());
				// 没有考勤记录,默认开始时间为排班时间,结束时间为排班结束时间
				attendance.setWorkStart(apply.getStartTime());
				attendance.setWorkEnd(apply.getEndTime());
				attendance.setWorkHour(0D);
				attendance.setWorkDay(0D);
				attendance.setType("NO");// 平时加班
				attendance.setIsException("Y");// 考勤异常
				attendance.setStatsType("N");// 未统计
				attendance = getInsertAttendance(attendance);
				// attendanceDao.insertSelective(attendance);
			} else if (null == attendance && null != maxAndMinTime.getMinTime()) {
				// 未创建考勤日记录,第一次创建
				attendance = new Attendance();
				attendance.setEmployeeCode(scheduleVo.getEmployeeCode());
				attendance.setWorkStart(maxAndMinTime.getMinTime());
				attendance.setWorkHour(0D);
				attendance.setWorkDay(0D);
				attendance.setType("NO");// 平时加班
				attendance.setIsException("N");// 非考勤异常
				attendance.setStatsType("N");// 未统计
				attendance = getInsertAttendance(attendance);
				// attendanceDao.insertSelective(attendance);
			} else if (null != attendance && null != maxAndMinTime.getMinTime()) {
				float overtimeTime = apply.getEndTime().getTime() - apply.getStartTime().getTime();// 申请平时加班时间
				float signTime = maxAndMinTime.getMaxTime().getTime() - maxAndMinTime.getMinTime().getTime();// 实际出勤时间
				if (maxAndMinTime.getMaxTime().compareTo(maxAndMinTime.getMinTime()) == 0) {
					// 说明只考勤了一次,更新考勤状态为异常
					attendance.setIsException("Y");// 考勤异常
					attendance = getUpdateAttendance(attendance);
					// attendanceDao.updateByPrimaryKeySelective(attendance);
				} else {
					// 说明存在两次以上的考勤记录
					// 判断两者时间,那个短取哪个
					if (signTime > overtimeTime) {
						attendance.setWorkStart(apply.getStartTime());
						attendance.setWorkEnd(apply.getEndTime());
						attendance.setWorkHour(Double.parseDouble(String.valueOf(apply.getTotalWorkingHours())));
						// attendanceDao.updateByPrimaryKeySelective(attendance);
					} else {
						attendance.setWorkEnd(maxAndMinTime.getMaxTime());
						attendance.setWorkHour(BigDecimal.valueOf((signTime) / (3600 * 1000))
								.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
						// attendanceDao.updateByPrimaryKeySelective(attendance);
					}
				}
			}
		}
	}

	private void leaveApplyAndSignApplyHandle(ScheduleVo scheduleVo, String scheduleWorkStart, String scheduleWorkEnd,
			String workStart, String workEnd) {
		// 查询是否存在请假申请
		List<Apply> leaveApply = applyDao.findApplyList(scheduleVo.getEmployeeCode(), workStart, workEnd, "1");

		List<Apply> signApply = applyDao.findApplyList(scheduleVo.getEmployeeCode(), workStart, workEnd, "6");

		// 查询今日是否有异常考勤日记录
		Attendance attendance = attendanceDao.findExAttendance(scheduleVo.getEmployeeCode(), workStart, workEnd);
		if (null != attendance) {
			// 是否存在请假申请
			if (null != leaveApply && leaveApply.size() > 0) {
				// 把异常的那条记录标记为已处理
				attendance.setStatsType("Y");
				// attendanceDao.updateByPrimaryKeySelective(attendance);
				for (Apply apply : leaveApply) {
					if (apply.getEndTime().compareTo(TimeUtil.strToDateLong(scheduleWorkEnd)) <= 0) {
						attendance = new Attendance();
						attendance.setEmployeeCode(scheduleVo.getEmployeeCode());
						attendance.setWorkStart(TimeUtil.strToDateLong(scheduleWorkStart));
						attendance.setWorkEnd(apply.getEndTime());
						attendance.setWorkHour(0D);
						attendance.setWorkDay(0D);
						attendance.setType(apply.getType());// 类型简称
						attendance.setIsException("N");// 非考勤异常
						attendance.setStatsType("N");// 未统计
						attendance = getInsertAttendance(attendance);
						// attendanceDao.insertSelective(attendance);

					}
				}
			}
		}
	}

	private void workDayHandle(ScheduleVo scheduleVo, SignRecordVo maxAndMinTime, String scheduleWorkStart,
			String scheduleWorkEnd, String workStart, String workEnd) {

		// 根据排班的名称获取排班的英文缩写
		String simpleName = scheduleDao.findWorkWaySimpleNameByName(scheduleVo.getName());

		// 根据员工代码查询员工今天的日记录表(排班工作期的日记录数据),开始时间是四小时前时间,结束时间为排班时间
		Attendance attendance = attendanceDao.findAttendanceByEmployeeCode(scheduleVo.getEmployeeCode(), workStart,
				scheduleWorkEnd, null);
		if (null == attendance && null == maxAndMinTime.getMinTime()) {
			// 排班了,但是无考勤记录,记录为考勤异常
			attendance = new Attendance();
			attendance.setEmployeeCode(scheduleVo.getEmployeeCode());
			// 没有考勤记录,默认开始时间为排班时间,结束时间为排班结束时间
			attendance.setWorkStart(TimeUtil.strToDateLong(scheduleWorkStart));
			attendance.setWorkEnd(TimeUtil.strToDateLong(scheduleWorkEnd));
			attendance.setWorkHour(0D);
			attendance.setWorkDay(0D);
			attendance.setType(simpleName);// 类型简称
			attendance.setIsException("Y");// 考勤异常
			attendance.setStatsType("N");// 未统计
			attendance = getInsertAttendance(attendance);
			// attendanceDao.insertSelective(attendance);
		} else if (null == attendance && null != maxAndMinTime.getMinTime()) {
			// 未创建考勤日记录,第一次创建
			attendance = new Attendance();
			attendance.setEmployeeCode(scheduleVo.getEmployeeCode());
			attendance.setWorkStart(maxAndMinTime.getMinTime());
			attendance.setWorkHour(0D);
			attendance.setWorkDay(0D);
			attendance.setType(simpleName);// 类型简称
			attendance.setIsException("N");// 非考勤异常
			attendance.setStatsType("N");// 未统计
			attendance = getInsertAttendance(attendance);
			// attendanceDao.insertSelective(attendance);
		} else if (null != attendance && null != maxAndMinTime.getMinTime()) {
			if (maxAndMinTime.getMaxTime().compareTo(maxAndMinTime.getMinTime()) == 0) {
				// 说明只考勤了一次,更新考勤状态为异常
				attendance.setIsException("Y");// 考勤异常
				attendance = getUpdateAttendance(attendance);
				// attendanceDao.updateByPrimaryKeySelective(attendance);
			} else {
				// 说明存在两次以上的考勤记录
				float signTime = maxAndMinTime.getMaxTime().getTime() - maxAndMinTime.getMinTime().getTime();
				if (signTime < 3 * 3600 * 1000) {
					// 两次考勤记录小于三个小时,算异常
					attendance.setIsException("Y");// 考勤异常
					attendance = getUpdateAttendance(attendance);
					// attendanceDao.updateByPrimaryKeySelective(attendance);
				} else if (signTime >= 3 * 3600 * 1000 && signTime <= 7 * 3600 * 1000) {
					// 考勤时间在三个小时以上并且在七个小时以内,算半天出勤
					attendance.setWorkEnd(maxAndMinTime.getMaxTime());
					attendance.setWorkHour(BigDecimal.valueOf((signTime) / (3600 * 1000))
							.setScale(1, BigDecimal.ROUND_HALF_UP).subtract(scheduleVo.getRestTime()).doubleValue());
					attendance.setWorkDay(0.5D);
					// attendanceDao.updateByPrimaryKeySelective(attendance);
				} else {
					// 全天出勤
					float scheduleTime = TimeUtil.strToDateLong(scheduleWorkEnd).getTime()
							- TimeUtil.strToDateLong(scheduleWorkStart).getTime();
					// 判断打卡时间和排班时间哪个短,取短的时间
					if (signTime >= scheduleTime) {
						attendance.setWorkStart(TimeUtil.strToDateLong(scheduleWorkStart));
						attendance.setWorkEnd(TimeUtil.strToDateLong(scheduleWorkEnd));
						attendance.setWorkHour(
								BigDecimal.valueOf((scheduleTime) / (3600 * 1000)).setScale(1, BigDecimal.ROUND_HALF_UP)
										.subtract(scheduleVo.getRestTime()).doubleValue());
					} else {
						attendance.setWorkEnd(maxAndMinTime.getMaxTime());
						attendance.setWorkHour(
								BigDecimal.valueOf((signTime) / (3600 * 1000)).setScale(1, BigDecimal.ROUND_HALF_UP)
										.subtract(scheduleVo.getRestTime()).doubleValue());
					}
					attendance.setWorkDay(1.0D);
					// attendanceDao.updateByPrimaryKeySelective(attendance);
				}
			}
		}
	}

	private void festivalHandle(ScheduleVo scheduleVo, SignRecordVo maxAndMinTime, String scheduleWorkStart,
			String scheduleWorkEnd, String workStart, String workEnd) {

		// 判断该员工是否在日记录表中已经插入一条法定节假日的记录
		Attendance attendance = attendanceDao.findAttendanceByEmployeeCode(scheduleVo.getEmployeeCode(), workStart,
				workEnd, "HO");
		if (null == attendance && null == maxAndMinTime.getMinTime()) {
			// 法定节假日排班了,但是无考勤记录,先记录为法定节假日考勤异常
			attendance = new Attendance();
			attendance.setEmployeeCode(scheduleVo.getEmployeeCode());
			// 没有考勤记录,默认开始时间为排班时间,结束时间为排班结束时间
			attendance.setWorkStart(TimeUtil.strToDateLong(scheduleWorkStart));
			attendance.setWorkEnd(TimeUtil.strToDateLong(scheduleWorkEnd));
			attendance.setWorkHour(0D);
			attendance.setWorkDay(0D);
			attendance.setType("HO");// 法定节假日
			attendance.setIsException("Y");// 考勤异常
			attendance.setStatsType("N");// 未统计
			attendance = getInsertAttendance(attendance);
			// attendanceDao.insertSelective(attendance);
		} else if (null == attendance && null != maxAndMinTime.getMinTime()) {
			// 未创建考勤日记录,第一次创建
			attendance = new Attendance();
			attendance.setEmployeeCode(scheduleVo.getEmployeeCode());
			attendance.setWorkStart(maxAndMinTime.getMinTime());
			attendance.setWorkHour(0D);
			attendance.setWorkDay(0D);
			attendance.setType("HO");// 法定节假日
			attendance.setIsException("N");// 非考勤异常
			attendance.setStatsType("N");// 未统计
			attendance = getInsertAttendance(attendance);
			// attendanceDao.insertSelective(attendance);
		} else if (null != attendance && null != maxAndMinTime.getMinTime()) {
			if (maxAndMinTime.getMaxTime().compareTo(maxAndMinTime.getMinTime()) == 0) {
				// 说明只考勤了一次,更新考勤状态为异常
				attendance.setIsException("Y");// 考勤异常
				attendance = getUpdateAttendance(attendance);
				// attendanceDao.updateByPrimaryKeySelective(attendance);
			} else {
				// 说明存在两次以上的考勤记录
				float signTime = maxAndMinTime.getMaxTime().getTime() - maxAndMinTime.getMinTime().getTime();
				if (signTime < 3 * 3600 * 1000) {
					// 两次考勤记录小于三个小时,算异常
					attendance.setIsException("Y");// 考勤异常
					attendance = getUpdateAttendance(attendance);
					// attendanceDao.updateByPrimaryKeySelective(attendance);
				} else if (signTime >= 3 * 3600 * 1000 && signTime <= 7 * 3600 * 1000) {
					// 考勤时间在三个小时以上并且在七个小时以内,算半天出勤
					attendance.setWorkEnd(maxAndMinTime.getMaxTime());
					attendance.setWorkHour(BigDecimal.valueOf((signTime) / (3600 * 1000))
							.setScale(1, BigDecimal.ROUND_HALF_UP).subtract(scheduleVo.getRestTime()).doubleValue());
					attendance.setWorkDay(0.5D);
					// attendanceDao.updateByPrimaryKeySelective(attendance);
				} else {
					// 全天出勤
					float scheduleTime = TimeUtil.strToDateLong(scheduleWorkEnd).getTime()
							- TimeUtil.strToDateLong(scheduleWorkStart).getTime();
					// 判断打卡时间和排班时间哪个短,取短的时间
					if (signTime >= scheduleTime) {
						attendance.setWorkStart(TimeUtil.strToDateLong(scheduleWorkStart));
						attendance.setWorkEnd(TimeUtil.strToDateLong(scheduleWorkEnd));
						attendance.setWorkHour(
								BigDecimal.valueOf((scheduleTime) / (3600 * 1000)).setScale(1, BigDecimal.ROUND_HALF_UP)
										.subtract(scheduleVo.getRestTime()).doubleValue());
					} else {
						attendance.setWorkEnd(maxAndMinTime.getMaxTime());
						attendance.setWorkHour(
								BigDecimal.valueOf((signTime) / (3600 * 1000)).setScale(1, BigDecimal.ROUND_HALF_UP)
										.subtract(scheduleVo.getRestTime()).doubleValue());
					}
					attendance.setWorkDay(1.0D);
					// attendanceDao.updateByPrimaryKeySelective(attendance);
				}
			}
		}

	}

	private Attendance getInsertAttendance(Attendance attendance) {
		attendance.setBatchDate(new Date());
		// TODO 创建人ID写死,后期需要更改
		attendance.setCreatedBy(1L);
		attendance.setCreatedDate(new Date());
		// TODO 创建程序ID写死,后期需要更改
		attendance.setCreProId(1L);
		// TODO 最后更新人ID写死,后期需要更改
		attendance.setLastModifiedBy(1L);
		attendance.setLastModifiedDate(attendance.getCreatedDate());
		// TODO 更新程序ID写死,后期需要更改
		attendance.setModProId(1L);
		attendance.setDeleted("0");// 防止数据库没有设置默认值
		return attendance;
	}

	private Attendance getUpdateAttendance(Attendance attendance) {
		// TODO 最后更新人ID写死,后期需要更改
		attendance.setLastModifiedBy(1L);
		attendance.setLastModifiedDate(new Date());
		// TODO 更新程序ID写死,后期需要更改
		attendance.setModProId(1L);
		return attendance;
	}
}
