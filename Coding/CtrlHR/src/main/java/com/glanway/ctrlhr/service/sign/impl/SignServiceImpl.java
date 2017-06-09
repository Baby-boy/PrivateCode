package com.glanway.ctrlhr.service.sign.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glanway.ctrlhr.dao.sign.SignDao;
import com.glanway.ctrlhr.entity.para.SignPara;
import com.glanway.ctrlhr.entity.sign.Sign;
import com.glanway.ctrlhr.entity.vo.SignDetailVo;
import com.glanway.ctrlhr.entity.vo.SignExportVo;
import com.glanway.ctrlhr.service.BaseServiceImpl;
import com.glanway.ctrlhr.service.sign.SignService;

@Transactional
@Service("signService")
public class SignServiceImpl extends BaseServiceImpl<Sign> implements SignService {

    @Autowired
    private SignDao signDao;

    @Override
    public List<SignDetailVo> findMany(SignPara para) {

        return signDao.findMany(para);
    }

    @Override
    public List<SignExportVo> findSignExportList(SignPara para) {

        return signDao.findSignExportList(para);
    }

    @Override
    public List<SignExportVo> findEmployeeList(SignPara para) {
        return signDao.findEmployeeList(para);
    }

}
