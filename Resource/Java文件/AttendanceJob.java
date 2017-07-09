package com.glanway.hr.attendance.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glanway.hr.attendance.dao.apply.ApplyDao;
import com.glanway.hr.attendance.dao.attendance.AttendanceDao;
import com.glanway.hr.attendance.dao.schedule.ScheduleDao;
import com.glanway.hr.attendance.dao.sign.SignDao;
import com.glanway.hr.attendance.entity.apply.Apply;
import com.glanway.hr.attendance.entity.attendance.Attendance;
import com.glanway.hr.attendance.entity.sign.Sign;
import com.glanway.hr.attendance.service.attendance.AttendanceService;
import com.glanway.hr.attendance.utils.FestivalUtil;
import com.glanway.hr.attendance.utils.TimeUtil;

@Component
public class AttendanceJob {

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private ScheduleDao scheduleDao;// 排班

	@Autowired
	private SignDao signDao;// 考勤

	@Autowired
	private AttendanceDao attendanceDao;// 日记录

	@Autowired
	private ApplyDao applyDao;// 申请

	@Autowired
	private FestivalUtil festivalUtil;// 处理法定节假日的工具类

	/**
	 * 处理考勤记录的为日记录的定时任务.
	 *
	 * @author FUQIHAO
	 * @dateTime 2017年7月3日 上午9:42:41
	 */
	// @Scheduled(cron = "0 0 0/1 * * ?") // 每一个小时执行一次
	public void attendanceJob() {
		// 获取今天的年月日
		Integer year = TimeUtil.getYear(null);// null代表当前年份
		Integer month = TimeUtil.getMonth(null);// null代表当前月份
		Integer day = TimeUtil.getDay(null);// null代表当前天份

		// 简化了逻辑代码
		attendanceService.dayAttendanceJob(year, month, day);

		signHandleBySchedule();
	}

	private void signHandleBySchedule() {
		// 处理所有签卡记录(将签卡记录转换为 T_SIGN 记录)
		signApplyHandle();
		// 查询所有未处理的申请
		leaveApplyAndOvertimeApplyHandle();

	}

	private void leaveApplyAndOvertimeApplyHandle() {
		List<Apply> applies = applyDao.findAllApply(null);
		for (Apply apply : applies) {
			// 创建日记录对象
			Attendance attendance = new Attendance();
			attendance.setEmployeeId(apply.getEmployeeId());
			attendance.setEmployeeCode(apply.getEmployeeCode());
			attendance.setWorkStart(apply.getStartTime());
			attendance.setWorkEnd(apply.getEndTime());
			attendance.setWorkHour(Double.parseDouble(String.valueOf(apply.getTotalWorkingHours())));
			attendance.setWorkDay(Double.parseDouble(String.valueOf(apply.getTotalWorkingDays())));

			// 根据员工查询该员工的工作制,如果是非标准工作的员工,平时加班和周末加班都是正常出勤
			Integer workSystemId = attendanceDao.findWorkSystemByEmployeeCode(apply.getEmployeeCode());
			if (null != workSystemId && workSystemId != 1) {
				if (apply.getType().equals("NO") || apply.getType().equals("WO")) {
					attendance.setType("DU");// 正常出勤
				}
			} else {
				attendance.setType(apply.getType());// 申请类型
			}
			attendance.setIsException("N");// 默认都为非签卡记录
			attendance.setStatsType("N");// 未统计
			attendance = getInsertAttendance(attendance);
			attendanceDao.insertSelective(attendance);

			// 将记录标记为已统计
			apply.setStatsType("Y");
			applyDao.updateByPrimaryKeySelective(apply);
		}
	}

	private void signApplyHandle() {
		List<Apply> signApplies = applyDao.findAllApply("SC");
		for (Apply apply : signApplies) {
			Sign sign = new Sign();
			sign.setEmployeeCode(apply.getEmployeeCode());
			sign.setTime(apply.getStartTime());
			sign.setState("0");// 签卡生成的记录
			sign = getInsertSign(sign);
			signDao.insertSelective(sign);

			sign.setTime(apply.getEndTime());
			sign = getInsertSign(sign);
			signDao.insertSelective(sign);

			// 将记录标记为已统计
			apply.setStatsType("Y");
			applyDao.updateByPrimaryKeySelective(apply);
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

	private Sign getInsertSign(Sign sign) {

		sign.setBatchDate(new Date());
		// TODO 创建人ID写死,后期需要更改
		sign.setCreProId(1L);
		// TODO 创建程序ID写死,后期需要更改
		sign.setCreatedBy(1L);
		sign.setCreatedDate(new Date());
		// TODO 最后更新人ID写死,后期需要更改
		sign.setModProId(1L);
		// TODO 更新程序ID写死,后期需要更改
		sign.setLastModifiedBy(1L);
		sign.setLastModifiedDate(sign.getCreatedDate());
		sign.setDeleted("0");// 防止数据库没有设置默认值
		return sign;
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
