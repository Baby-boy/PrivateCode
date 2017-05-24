package com.glanway.iclock.dao.sign;

import com.glanway.iclock.dao.BaseDao;
import com.glanway.iclock.entity.sign.Device;
import com.glanway.iclock.entity.sign.Sign;

/**
 * 
 * 说明 : 
 * 员工考勤记录 Dao
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月19日 下午9:00:49
 */
public interface SignDao extends BaseDao<Sign> {
    int deleteByPrimaryKey(Long id);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);
}