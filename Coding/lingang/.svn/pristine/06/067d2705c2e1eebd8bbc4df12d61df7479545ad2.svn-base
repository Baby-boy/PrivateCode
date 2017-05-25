package com.lingang.api.service;

import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysRegion;
import com.lingang.api.domain.para.SysRegionPara;
import com.lingang.api.domain.vo.SysRegionStatisticsVo;

public interface SysRegionService {

	/**
	* @Description: (获取地区信息)
	* @return    
	* @author lgl(lgl1012dream@foxmail.com)
	* @date: 2016年12月5日 上午9:59:19
	 */
	ServiceResult<Object> selectRegionAllList();

	/**
	* @Description: (获取地区信息后台)
	* @param para
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月8日 下午8:52:32
	 */
	ServiceResult<Page<SysRegion>> selectSysRegionPageList(SysRegionPara para);
	
	/**
	 * 产业园区统计_区位分布
	 */
	ServiceResult<Page<SysRegionStatisticsVo>> selectRegionParkStatisticsPageList(Integer regionType,Integer pageIndex,Integer pageSize);
	
	/**
	* @Description: (修改地区信息后台)
	* @param region
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月10日 下午2:48:27
	 */
	ServiceResult<SysRegion> updateSysRegion(SysRegion region);

	/**
	* @Description: (添加地区信息后台)
	* @param region
	* @return    
	* @author zf(18817590078@163.com)
	* @date: 2016年12月10日 下午3:12:09
	 */
	ServiceResult<SysRegion> addSysBasics(SysRegion region);

}
