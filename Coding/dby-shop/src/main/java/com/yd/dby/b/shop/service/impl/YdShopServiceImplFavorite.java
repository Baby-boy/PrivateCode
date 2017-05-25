package com.yd.dby.b.shop.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.entity.YdFavorite;
import com.yd.dby.b.shop.mapper.YdShopMapperFavorite;
import com.yd.dby.b.shop.service.YdShopServiceFavorite;
import com.yd.dby.utils.YdUtilResponse;

@Service("_Fav")
public class YdShopServiceImplFavorite implements YdShopServiceFavorite {

    @Autowired
    private HttpSession session;

    @Autowired
    private YdShopMapperFavorite ydShopMapperFavorite;

    @Override
    public Integer delete(Integer fav_type, Integer user_id, Integer fav_fid) {
        return ydShopMapperFavorite.delete(fav_type, user_id, fav_fid);
    }

    @Override
    public Object store(YdFavorite ydFavorite) {
        if (session.getAttribute("user_id") == null) {
            return YdUtilResponse.fail("请先登录");
        }
        try {
            ydFavorite.setUser_id((Integer) session.getAttribute("user_id"));

            YdFavorite ydFavoriteData = ydShopMapperFavorite.info(ydFavorite);
            if (ydFavoriteData != null) {
                return YdUtilResponse.fail("已经存在");
            }

            ydShopMapperFavorite.store(ydFavorite);
            return YdUtilResponse.success(null, "收藏成功");
        } catch (Exception e) {
            return YdUtilResponse.fail("收藏失败");
        }
    }


    @Override
    public boolean isFavorite(Integer fav_fid, Integer fav_type) {
        if (session.getAttribute("user_id") == null) {
            return false;
        }
        try {
            YdFavorite ydFavorite = new YdFavorite();
            ydFavorite.setUser_id((Integer) session.getAttribute("user_id"));
            ydFavorite.setFav_fid(fav_fid);
            ydFavorite.setFav_type(fav_type);
            YdFavorite ydFavoriteData = ydShopMapperFavorite.info(ydFavorite);
            if (ydFavoriteData != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Object destory(YdFavorite ydFavorite) {
        if (session.getAttribute("user_id") == null) {
            return YdUtilResponse.fail("请先登录");
        }

        Integer userId = Integer.parseInt(session.getAttribute("user_id").toString());

        try {
            ydFavorite.setUser_id(userId);

            YdFavorite ydFavoriteData = ydShopMapperFavorite.info(ydFavorite);
            if (ydFavoriteData == null) {
                return YdUtilResponse.fail("未收藏过此商品");
            }

            ydShopMapperFavorite.destory(ydFavorite);
            return YdUtilResponse.success(null, "取消收藏成功");
        } catch (Exception e) {
            return YdUtilResponse.fail("取消收藏失败");
        }
    }
}