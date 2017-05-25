package com.yd.dby.b.shop.mapper;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface YdShopMapperClassify {
    List<HashMap<String, Object>> get_many(@Param("cly_type") String cly_type);
    
    
    HashMap<String, Object> info(@Param("id") Integer id);
    
    List<HashMap<String, Object>> son(@Param("id") Integer id);
    
}