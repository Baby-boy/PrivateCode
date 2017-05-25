package com.yd.dby.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.app.common.HttpCode;
import com.yd.dby.app.common.ServiceResult;
import com.yd.dby.app.entity.YdAddress;
import com.yd.dby.app.entity.YdUser;
import com.yd.dby.app.mapper.YdAddressMapper;
import com.yd.dby.app.service.YdAddressService;

@Service("ydAddressService")
public class YdAddressServiceImpl implements YdAddressService {

	@Autowired
	private YdAddressMapper ydAddressMapper;

	@Override
	public ServiceResult<Object> insertUserAddress(YdAddress ydAddress) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 判断是否需要设置为默认地址
		if (ydAddress.getAdIsDefault() == 1) {
			// 此新添加的地址需要设置为默认地址,重置所有收货地址为非默认状态
			ydAddressMapper.updateAllAddressState(ydAddress.getUserId());
		} else {
			// 查询是否是第一次添加地址,如果是设置为默认地址
			int info = ydAddressMapper.selectAddressCount(ydAddress.getUserId());
			if (info == 0) {
				// 说明没有添加过地址
				ydAddress.setAdIsDefault(1);
			}
		}

		int info = ydAddressMapper.insertSelective(ydAddress);
		if (info == 1) {
			result.setMsg("添加成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("添加失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<List<YdAddress>> selectAddressList(YdUser user) {
		ServiceResult<List<YdAddress>> result = new ServiceResult<>();

		// 查询普通地址
		List<YdAddress> addressList = ydAddressMapper.selectAddressList(user.getUserId(), 0);

		// 查询默认收货地址
		List<YdAddress> defaultAddress = ydAddressMapper.selectAddressList(user.getUserId(), 1);
		if (null == defaultAddress) {
			if (null != addressList && addressList.size() > 0) {
				addressList.get(0).setAdIsDefault(1);
				ydAddressMapper.updateByPrimaryKeySelective(addressList.get(0));
			}
		} else if (defaultAddress.size() > 1) {
			// 说明有假数据,这种情况一般是不可能出现的,只有可能出现在后台管理人员的信息修改失误情况下
			ydAddressMapper.updateAllAddressState(user.getUserId());
			defaultAddress.get(0).setAdIsDefault(1);
			ydAddressMapper.updateByPrimaryKeySelective(defaultAddress.get(0));
		}

		List<YdAddress> list = new ArrayList<>();
		list.addAll(defaultAddress);
		list.addAll(addressList);

		result.setStatusCode(HttpCode.OK);
		result.setData(list);
		result.setMsg("查询成功!");
		return result;
	}

	@Override
	public ServiceResult<Object> updateUserAddress(YdAddress ydAddress) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 判断是否需要设置为默认地址
		if (ydAddress.getAdIsDefault() == 1) {
			// 此新添加的地址需要设置为默认地址,重置所有收货地址为非默认状态
			ydAddressMapper.updateAllAddressState(ydAddress.getUserId());
		}

		int info = ydAddressMapper.updateByPrimaryKeySelective(ydAddress);
		if (info == 1) {
			result.setMsg("修改成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("修改失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@Override
	public ServiceResult<Object> deleteUserAddress(String adIds) {
		ServiceResult<Object> result = new ServiceResult<>();

		// 对需要删除的多个地址ID进行处理
		String[] str = StringUtils.split(adIds, ",");
		List<Integer> adIdList = new ArrayList<>();
		for (String adId : str) {
			adIdList.add(Integer.parseInt(adId));
		}

		int info = ydAddressMapper.deleteUserAddress(adIdList);
		if (info > 0) {
			result.setMsg("删除成功!");
			result.setStatusCode(HttpCode.OK);
		} else {
			result.setMsg("删除失败!");
			result.setStatusCode(HttpCode.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

}
