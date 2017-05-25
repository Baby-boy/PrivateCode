package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperAttribute;
import com.yd.dby.d.seller.mapper.YdSellerMapperAttributeValue;
import com.yd.dby.d.seller.mapper.YdSellerMapperClassify;
import com.yd.dby.d.seller.mapper.YdSellerMapperType;
import com.yd.dby.d.seller.mapper.YdSellerMapperTypeBrand;
import com.yd.dby.d.seller.service.YdSellerServiceType;

@Service("_YdSellerServiceType")
public class YdSellerServiceImplType implements YdSellerServiceType {

	@Autowired
	private YdSellerMapperClassify ydSellerMapperClassify;

	@Autowired
	private YdSellerMapperType ydSellerMapperType;
	
	@Autowired
	private YdSellerMapperAttribute ydSellerMapperAttribute;
	
	@Autowired
	private YdSellerMapperAttributeValue ydSellerMapperAttributeValue;
	
	@Autowired
	private YdSellerMapperTypeBrand ydSellerMapperTypeBrand;


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> info(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		HashMap<String, Object> dataType = new HashMap<String, Object>();
		
		dataType = (HashMap<String, Object>) ydSellerMapperClassify.info( Integer.parseInt( request.get("class_id").toString() ) );
	
		if ( !dataType.get("type_id").equals(0) ) {
			Object dataTypeObject = ydSellerMapperType.info( Integer.parseInt( dataType.get("type_id").toString() ) );
			dataType = (HashMap<String, Object>) dataTypeObject;
			map.put("data", dataType);
			map.put("dataAttrs", ydSellerMapperAttribute.lists(dataType));
			map.put("dataAttrValues", ydSellerMapperAttributeValue.lists(dataType));
			map.put("dataBrand", ydSellerMapperTypeBrand.lists( Integer.parseInt( dataType.get("type_id").toString() ) ));
		} else {
			map.put("data", "");
		}
		
		return map;
	}


}