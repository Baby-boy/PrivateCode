package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperMessage {

	List<HashMap<String, Object>> get_many(@Param("msg_type") Integer msg_type, @Param("msg_fid") Integer msg_fid, @Param("page_start") Integer page_start);

	List<HashMap<String, Object>> get_latest(@Param("msg_type") Integer msg_type, @Param("msg_fid") Integer msg_fid, @Param("limit") Integer limit);
}
