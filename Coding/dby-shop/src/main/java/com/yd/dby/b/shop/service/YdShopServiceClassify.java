package com.yd.dby.b.shop.service;
import java.util.HashMap;
import java.util.List;
public interface YdShopServiceClassify {
    List<HashMap<String, Object>> get(String cly_type);
    
    Object getSon(Integer classifyId);
}