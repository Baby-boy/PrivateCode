package com.yd.dby.b.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface YdShopMapperAuction {

	// 出价历史
	List<Object> select_history_bid(@Param("auction_id") Integer auction_id);

	// 出价历史
	@Select("select count(*) from yd_money_record where money_fid = #{auction_id} and money_type = 4;")
	@ResultType(java.lang.Integer.class)
	Integer count_history_bid(@Param("auction_id") Integer auction_id);

	

	
	
	// 查询最后出价人
	Object select_latest_bid(@Param("auction_id") Integer auction_id);

	// 是否出了保证金
	@Select("select count(*) from yd_money_record where money_fid = #{auction_id} and user_id = #{user_id} and money_type = 3;")
	@ResultType(java.lang.Integer.class)
	Integer check_is_bail(@Param("auction_id") Integer auction_id, @Param("user_id") Integer user_id);

	// 添加保证金
	@Insert("INSERT yd_money_record(user_id,money_fid,money_num,money_type,money_content,money_created_time)VALUE(#{user_id},#{auction_id},#{price},3,#{money_content},NOW());")
	Integer insert_bail(@Param("auction_id") Integer auction_id, @Param("user_id") Integer user_id,
			@Param("price") Float price, @Param("money_content") String money_content);

	// 添加出价
	@Insert("INSERT yd_money_record(user_id,money_fid,money_num,money_type,money_content,money_created_time)VALUE(#{user_id},#{auction_id},#{price},4,#{money_content},NOW());")
	Integer insert_bid(@Param("auction_id") Integer auction_id, @Param("user_id") Integer user_id,
			@Param("price") Float price, @Param("money_content") String money_content);

	// 更新价格
	@Update("update yd_depot set depot_current_bid = depot_current_bid + #{price}, depot_latestbid_user_id = #{user_id} where depot_id = #{depot_id}")
	Integer update_bid(@Param("depot_id") Integer depot_id, @Param("user_id") Integer user_id,
			@Param("price") Float price);

}