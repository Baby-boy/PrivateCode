package com.yd.gcj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerBanner;

public interface YdMangerMapperBanner {
	
	/***
	 * 查询所有图片信息
	 * @return
	 */
	List<YdMangerBanner> $queryAll();
	
	/***
	 * 根据分页信息查询图片所有信息
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	List<YdMangerBanner> $queryAllByPage(@Param("startPageNum") Integer startPageNum,@Param("queryPageNum") Integer queryPageNum);
	
	/***
	 * 根据图片信息id集合查询相应的数据
	 * @param ids
	 * @return
	 */
	List<YdMangerBanner> $queryByIds(List<Integer> ids);
	
	/***
	 * 根据id查询单个图片信息
	 * @param banner_id
	 * @return
	 */
	YdMangerBanner $queryById(@Param("banner_id") Integer banner_id);
	
	/***
	 * 添加新图片信息 
	 * @param banner
	 * @return
	 */
	Integer $insert(YdMangerBanner banner);
	
	/***
	 * 跟新图片信息
	 * @param banner
	 * @return
	 */
	Integer $update(YdMangerBanner banner);
	
	/***
	 * 删除指定id图片信息
	 * @param banner_id
	 * @return
	 */
	Integer $delete(@Param("banner_id") Integer banner_id);
	
	Integer $test();
	
}
