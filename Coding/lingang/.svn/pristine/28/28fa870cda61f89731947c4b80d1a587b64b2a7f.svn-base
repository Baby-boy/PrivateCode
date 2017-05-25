package com.lingang.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.WcmCount;
import com.lingang.api.service.WcmCountService;
import com.lingang.core.persistence.reader.WcmCountReaderMapper;
import com.lingang.core.persistence.writer.WcmCountWriterMapper;

@Service("wcmCountService")
public class WcmCountServiceImpl implements WcmCountService {

	@Resource
	private WcmCountReaderMapper wcmCountReaderMapper;

	@Resource
	private WcmCountWriterMapper wcmCountWriterMapper;

	@Override
	public ServiceResult<WcmCount> selectClickCount(Integer docId, Integer siteId) {
		ServiceResult<WcmCount> result = new ServiceResult<>();
		// 先查询数据存不存在
		WcmCount wc = wcmCountReaderMapper.selectWcmCountByDocIdAndSiteId(docId, siteId);
		if (null == wc) {
			// 说明该条数据不存在,要添加一条新的
			WcmCount wcmCount = new WcmCount();
			wcmCount.setEventId(null);
			wcmCount.setDocId(docId);
			wcmCount.setSiteId(siteId);
			try {
				wcmCountWriterMapper.insertSelective(wcmCount);
				result.setData(wcmCount);
				result.setMessage("添加成功!");
				result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			} catch (Exception e) {
				result.setStateCode(StateCodeConstant.ERROR_CODE);
				result.setMessage("添加失败!");
			}
		} else {
			// 说明该条数据存在,需要把总数 +1
			wc.setClickCount(wc.getClickCount() + 1);
			try {
				wcmCountWriterMapper.updateSelective(wc);
				result.setData(wc);
				result.setMessage("添加成功!");
				result.setStateCode(StateCodeConstant.SUCCESS_CODE);
			} catch (Exception e) {
				result.setStateCode(StateCodeConstant.ERROR_CODE);
				result.setMessage("添加失败!");
			}
		}

		return result;
	}

}
