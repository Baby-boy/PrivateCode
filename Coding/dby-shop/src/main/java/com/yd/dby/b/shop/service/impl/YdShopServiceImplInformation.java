package com.yd.dby.b.shop.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperInformation;
import com.yd.dby.b.shop.service.YdShopServiceInformation;


@Service("_ydShopServiceInformation")
public class YdShopServiceImplInformation implements YdShopServiceInformation {

    @Autowired
    private YdShopMapperInformation ydShopMapperInformation;

    @Override
    public List<HashMap<String, Object>> findAll(Integer page_start) {
        return ydShopMapperInformation.findAll(page_start);
    }

    @Override
    public HashMap<String, Object> findById(Integer id) {
        return ydShopMapperInformation.findById(id);
    }

    @Override
    public Integer total() {
        int page = 10;
        int total = ydShopMapperInformation.total();
        if (total % page == 0) {
            return (int) Math.ceil(total / page);
        } else {
            return (int) (Math.ceil(total / page) + 1);
        }
    }

    @Override
    public Map<String, Object> page(HashMap<String, Object> request) {
        Integer page = 10;

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", ydShopMapperInformation.page((Integer.parseInt(request.get("current").toString()) - 1) * page));

        return map;
    }

    @Override
    public List<HashMap<String, Object>> messageList(Integer id, Integer page_start) {
        return ydShopMapperInformation.messageList(id, page_start);
    }

    @Override
    public Integer messageTotal(Integer id) {
        int page = 10;
        int total = ydShopMapperInformation.messageTotal(id);
        if (total % page == 0) {
            return (int) Math.ceil(total / page);
        } else {
            return (int) (Math.ceil(total / page) + 1);
        }
    }

    @Override
    public Map<String, Object> messagePage(HashMap<String, Object> request) {
        Integer page = 10;
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data", ydShopMapperInformation.messageList(Integer.parseInt(request.get("id").toString()), (Integer.parseInt(request.get("current").toString()) - 1) * page));

        return map;
    }

    @Override
    public HashMap<String, Object> msgReply(HashMap<String, Object> request) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        request.put("re_im_time", sdf.format(new Date()));


        map.put("data", ydShopMapperInformation.msgReply(request));

        return map;
    }

    @Override
    public HashMap<String, Object> Reply(HashMap<String, Object> request) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        request.put("im_time", sdf.format(new Date()));


        map.put("data", ydShopMapperInformation.Reply(request));

        return map;
    }


}
