package com.yd.dby.app.service;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdBanner;
import com.yd.dby.app.entity.YdOnlinebooking;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;

public interface YdCirculationService {

	/**
	 * 说明: 流通置换线上预约
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月8日 下午3:46:43
	 */
	@Transactional
	public ServiceResult<Object> insertCirculationOnlinebooking(YdOnlinebooking para);

	/**
	 * 说明: 流通置换首页
	 * 
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 下午2:12:09
	 */
	public ServiceResult<YdBanner> selectCirculationHome();

	/**
	 * 说明: 线上预约列表
	 * 
	 * @param para
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月17日 下午3:37:53
	 */
	public ServiceResult<Page<YdOnlinebooking>> selectCirculationOnlinebookingPageList(BasePara para, YdUser user);

	/**
	 * 说明: 在线预约详情
	 * 
	 * @param id
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月17日 下午4:37:28
	 */
	public ServiceResult<YdOnlinebooking> selectCirculationOnlinebookingDetails(Integer id);
}
