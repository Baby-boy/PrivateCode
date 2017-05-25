package com.yd.dby.app.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.dby.app.basic.Page;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdCtc;
import com.yd.dby.app.entity.YdDict;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.entity.para.BasePara;
import com.yd.dby.app.entity.para.CtcHomePara;
import com.yd.dby.app.entity.para.CtcPara;
import com.yd.dby.app.entity.vo.CtcClassifyVo;
import com.yd.dby.app.entity.vo.CtcCommentVo;
import com.yd.dby.app.entity.vo.CtcGoodsDetailsVo;
import com.yd.dby.app.entity.vo.CtcGoodsListVo;

public interface YdCtcService {

	/**
	 * 说明:懒鱼筛选分类
	 * 
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 上午11:38:21
	 */
	public ServiceResult<List<CtcClassifyVo>> selectCtcClassifyList();

	/**
	 * 说明: 懒鱼商品的质量列表
	 * 
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月24日 下午1:12:54
	 */
	public ServiceResult<List<YdDict>> selectQualityList();

	/**
	 * 说明: 发布懒鱼商品
	 * 
	 * @param ydCtc
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 上午11:53:19
	 */
	@Transactional
	public ServiceResult<Object> insertCtcGoods(YdCtc ydCtc);

	/**
	 * 说明: 查询用户发布商品列表
	 * 
	 * @param user
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月17日 下午8:41:21
	 */
	public ServiceResult<Page<CtcGoodsListVo>> selectUserCtcGoodsPageList(YdUser user, BasePara para);

	/**
	 * 说明: 用户编辑上传的懒鱼商品
	 * 
	 * @param ydCtc
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月18日 上午9:47:59
	 */
	@Transactional
	public ServiceResult<Object> updateUserCtcGoods(YdCtc ydCtc);

	/**
	 * 说明: 删除懒鱼商品
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月18日 下午2:12:46
	 */
	@Transactional
	public ServiceResult<Object> deleteUserCtcGoods(CtcPara para);

	/**
	 * 说明: 懒鱼首页
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月15日 下午3:25:26
	 */
	public ServiceResult<Page<CtcGoodsListVo>> selectCtcHome(CtcHomePara para);

	/**
	 * 说明: 懒鱼商品详情
	 * 
	 * @param ctcId
	 * @param user
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月16日 上午10:55:42
	 */
	public ServiceResult<CtcGoodsDetailsVo> selectCtcGoodsDetails(Integer ctcId, YdUser user);

	/**
	 * 说明: 查询懒鱼他人主页
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月16日 下午5:09:15
	 */
	public ServiceResult<Page<CtcGoodsListVo>> selectCtcOthersHomepage(CtcPara para);

	/**
	 * 说明: 他人主页评论列表
	 * 
	 * @param para
	 * @return
	 * @author fqh(fqh@zwzyd.com)
	 * @date: 2017年2月17日 下午1:31:15
	 */
	public ServiceResult<Page<CtcCommentVo>> selectCtcOthersHomepageCommentPageList(CtcPara para);

}
