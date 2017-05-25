package com.lingang.api.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.Page;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.entity.SysNews;
import com.lingang.api.domain.para.SysNewsPara;
import com.lingang.api.domain.vo.SysNewsVo;

public interface SysNewsService {

	/**
	 * @Description: (查询新闻列表)
	 * @param map
	 * @return 参数注释
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月3日 下午5:38:13
	 */

	ServiceResult<Page<SysNewsVo>> selectSysNewsAll(Map<String, Object> map);

	/**
	 * @Description: (查询新闻详情)
	 * @param 新闻ID
	 * @return
	 * @author gsh(15136390655@163.com)
	 * @date: 2016年12月3日 下午1:42:59
	 */
	ServiceResult<SysNewsVo> selectByPrimaryKeys(Integer newsId);

	/******** 后台 *********/

	/**
	 * @Description:后台查询新闻
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月8日 下午9:01:05
	 */
	ServiceResult<Page<SysNewsVo>> queryAllByPage(SysNewsPara para);

	/**
	 * @Description:查询指定新闻详情
	 * @param map
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月12日 下午12:20:54
	 */
	JsonResult queryById(HashMap<String, Object> map);

	/**
	 * @Description:添加新闻信息
	 * @param record
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月7日 下午9:21:40
	 */
	JsonResult insert(HashMap<String, Object> map,Date newDate);

	/**
	 * @Description:修改新闻信息
	 * @param record
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月7日 下午9:22:18
	 */
	JsonResult update(SysNews news);

	/**
	 * @Description:删除指定的新闻信息
	 * @param news_id
	 * @return
	 * @author lgl(lgl1012dream@foxmail.com)
	 * @date: 2016年12月7日 下午9:23:41
	 */
	JsonResult del(HashMap<String, Object> map,Date newDate);

//	/**
//	 * 
//	 * @Description: (查询新闻详情-分享新闻)
//	 * @param 新闻ID
//	 * @return
//	 * @param newsId
//	 * @author cnk
//	 * @return
//	 */
//	ServiceResult<SysNewsVo> selectByPrimaryKeys(Integer newsId);

}
