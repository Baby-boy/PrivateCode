package com.glanway.ctrlhr.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.glanway.ctrlhr.dao.sign.DaySignDao;
import com.glanway.ctrlhr.dao.sign.SignDao;
import com.glanway.ctrlhr.entity.sign.DaySign;
import com.glanway.ctrlhr.entity.vo.SignRecordVo;
import com.glanway.ctrlhr.util.TimeUtil;

@Service
public class SignJob {

	@Autowired
	private SignDao signDao;

	@Autowired
	private DaySignDao daySignDao;

	/**
	 * 说明 : 每一小时执行一次
	 * 
	 * @author 付其浩
	 * @dateTime 2017年4月26日 上午11:46:55
	 */
	@Scheduled(cron = "0 0 0/1  * * ?")
	// @Scheduled(cron = "0 0 20 * * ? ") 每天十点执行一次
	public void taskJob() {
		// 查询人员当天考勤异常状态
		List<SignRecordVo> list = signDao.findExList(TimeUtil.getDateStart(), TimeUtil.getDateEnd());
		if (null != list && list.size() > 0) {
			for (SignRecordVo signRecordVo : list) {
				if (signRecordVo.getEmployeeId() == null) {
					continue;
				}
				// 根据员工代码查询天表中有没有该员工今天的考勤记录
				DaySign daySign = daySignDao.findEmployeeById(signRecordVo.getEmployeeId(), TimeUtil.getDateStart(),
						TimeUtil.getDateEnd());
				if (null == daySign) {
					if (signRecordVo.getCount() > 0) {
						SignRecordVo maxAndMinTime = signDao.findEmployeeByCode(signRecordVo.getEmployeeCode(),
								TimeUtil.getDateStart(), TimeUtil.getDateEnd());
						daySign = new DaySign();
						daySign.setEmployeeId(signRecordVo.getEmployeeId());
						daySign.setDateFrom(maxAndMinTime.getMinTime());
						daySign.setHours(new BigDecimal(0));
						daySign.setState(1);

						daySign.setBatchDate(new Date());
						// TODO 创建人ID写死,后期需要更改
						daySign.setCreatedBy(1L);
						daySign.setCreatedDate(new Date());
						// TODO 创建程序ID写死,后期需要更改
						daySign.setCreProId(1L);
						// TODO 最后更新人ID写死,后期需要更改
						daySign.setLastModifiedBy(1L);
						daySign.setLastModifiedDate(daySign.getCreatedDate());
						// TODO 更新程序ID写死,后期需要更改
						daySign.setModProId(1L);
						daySignDao.insertSelective(daySign);
					}
				} else {
					SignRecordVo maxAndMinTime = signDao.findEmployeeByCode(signRecordVo.getEmployeeCode(),
							TimeUtil.getDateStart(), TimeUtil.getDateEnd());
					if (signRecordVo.getCount() <= 1) {
						daySign.setState(3);
						// TODO 最后更新人ID写死,后期需要更改
						daySign.setLastModifiedBy(1L);
						daySign.setLastModifiedDate(new Date());
						// TODO 更新程序ID写死,后期需要更改
						daySign.setModProId(1L);
						daySignDao.updateByPrimaryKeySelective(daySign);
					} else {
						float time = maxAndMinTime.getMaxTime().getTime() - maxAndMinTime.getMinTime().getTime();
						if (time < 3600 * 1000) {
							daySign.setState(3);
							// TODO 最后更新人ID写死,后期需要更改
							daySign.setLastModifiedBy(1L);
							daySign.setLastModifiedDate(new Date());
							// TODO 更新程序ID写死,后期需要更改
							daySign.setModProId(1L);
							daySignDao.updateByPrimaryKeySelective(daySign);
						} else {
							BigDecimal hours = BigDecimal.valueOf((time - 3600 * 1000) / (3600 * 1000)).setScale(1,
									BigDecimal.ROUND_HALF_UP);
							daySign.setDateTo(maxAndMinTime.getMaxTime());
							daySign.setHours(hours);
							daySign.setState(1);

							// TODO 最后更新人ID写死,后期需要更改
							daySign.setLastModifiedBy(1L);
							daySign.setLastModifiedDate(new Date());
							// TODO 更新程序ID写死,后期需要更改
							daySign.setModProId(1L);
							daySignDao.updateByPrimaryKeySelective(daySign);
						}
					}
				}
			}
		}
	}
}
