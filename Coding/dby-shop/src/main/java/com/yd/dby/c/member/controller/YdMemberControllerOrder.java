package com.yd.dby.c.member.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yd.dby.utils.kdniao.YdKdNiao;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yd.dby.YdMain;
import com.yd.dby.b.shop.entity.YdOrder;
import com.yd.dby.b.shop.entity.YdOrderGoods;
import com.yd.dby.c.member.entity.YdComment;
import com.yd.dby.c.member.service.YdMemberServiceOrder;

/**
 * @author Administrator
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(value = "/member/order")
public class YdMemberControllerOrder {

    @Autowired
    private YdMemberServiceOrder ydMemberServiceOrder;


    @RequestMapping(value = "")
    public ModelAndView index(@RequestBody Map<String, HashMap<String, Object>> request) {
        return YdMain.MAV("_YdWebServiceMemberOrder", "index", request);
    }


    /**
     * 取消订单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "cancel")
    @ResponseBody
    public Object cancel(Integer id, String text) {
        return ydMemberServiceOrder.cancel(id, text);
    }


    /**
     * 确认收货
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "receipt")
    @ResponseBody
    public Object receipt(Integer id) {
        return ydMemberServiceOrder.receipt(id);
    }


    /**
     * 订单详情
     */
    @RequestMapping(value = "detail")
    public Object detail(Model model, Integer id) {
        model.addAttribute("data", ydMemberServiceOrder.info(id));
        model.addAttribute("dataGoods", ydMemberServiceOrder.orderGoods(id));
        return "member/temp/order/order_detail";
    }


    /**
     * @param model
     * @param num(订单编号)
     * @return 方法作用(添加已完成订单评论之前先拿到订单中所需要评价的信息)
     */
    @RequestMapping(value = "orderAssess")
    public Object orderAssess(Model model, long num) {
        //查询订单信息  根据订单编号
        List<YdOrder> ydOrderList = ydMemberServiceOrder.queryOrderInfomationByOrderSn(num);
        //查询订单所对应的商品信息
        List<YdOrderGoods> ydOrderGoodsList = ydMemberServiceOrder.queryOrderGoodsByOrderId(ydOrderList.get(0).getOrder_id());

        model.addAttribute("ydOrderGoodsList", ydOrderGoodsList);
        model.addAttribute("ydOrder", ydOrderList);
        return "member/temp/order/orderAssess";
    }


    /**
     * @param data
     * @return 方法作用(订单完成之后添加评论)
     */
    @RequestMapping(value = "addOrderAssess")
    @ResponseBody
    public Object addOrderAssess(String data, Integer orderId, Integer single) {
        //查询收货时间
        YdOrder ydOrder = ydMemberServiceOrder.queryReceivingTimeByOrderId(orderId);
        String receiving_time = ydOrder.getReceiving_time();
        if (receiving_time == null) {
            receiving_time = "";
        }
        return ydMemberServiceOrder.addComment(data, orderId, single, receiving_time);
    }


    /**
     * 退款页面
     */
    @RequestMapping(value = "refund")
    public Object refund(@RequestBody Map<String, HashMap<String, Object>> request) {
        return YdMain.MAV("_YdWebServiceMemberOrder", "refund", request);
    }
    
    
    /**
     * 交易投诉页面
     */
    @RequestMapping(value = "complain")
    public Object complain(@RequestBody Map<String, HashMap<String, Object>> request) {
        return YdMain.MAV("_YdWebServiceMemberOrder", "complain", request);
    }


    /**
     * 查看物流
     */
    @RequestMapping("logis")
    public Object logis(Model model, Integer id) {
        HashMap<String, Object> map = ydMemberServiceOrder.info(id);
        model.addAttribute("data", map);
        model.addAttribute("dataLogis", YdKdNiao.get(map.get("logis_code").toString(), map.get("shipping_code").toString()));
        return "member/temp/order/order_logis";
    }


    /**
     * 删除取消订单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public Object delete(Integer id) {
        return ydMemberServiceOrder.delete(id);
    }
}
