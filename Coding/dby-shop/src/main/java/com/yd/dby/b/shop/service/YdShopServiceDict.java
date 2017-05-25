package com.yd.dby.b.shop.service;
import java.util.HashMap;
import java.util.List;
public interface YdShopServiceDict {
    List<HashMap<String, Object>> list(String dict_type);
    List<HashMap<String, Object>> list(String dict_type, String dict_pid);
    
    List<HashMap<String, Object>> many(String dict_type);
}