package com.lingang.core.persistence.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lingang.api.domain.entity.SysUser;
import com.lingang.api.domain.vo.SysUserVo;

public interface SysUserReaderMapper {

    SysUser selectByPrimaryKey(Integer userId);
    
    SysUserVo selectSysUserVoLoginByUserAccount(@Param("userAccount")String userAccount); //通过userAccount名字找User
    
    SysUserVo selectSysUserVoByUserId(@Param("userId")Integer userId);  //通过userID 找user
    
    int selectSysUserCount(@Param("map")Map<String, Object> map); 
    
    List<SysUser> selectSysUserPageList(@Param("map")Map<String, Object> map);
}