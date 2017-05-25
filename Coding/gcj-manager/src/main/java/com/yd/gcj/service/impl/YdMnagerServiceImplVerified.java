package com.yd.gcj.service.impl;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerVerified;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.mapper.YdMangerMapperUser;
import com.yd.gcj.mapper.YdMangerMapperVerified;
import com.yd.gcj.service.YdMangerServiceVerified;
import com.yd.gcj.tool.MapInitFactory;

@Service("YdMnagerServiceImplVerified")
public class YdMnagerServiceImplVerified implements YdMangerServiceVerified {

	@Autowired
	private YdMangerMapperVerified mapperVerified;

	@Autowired
	private YdMangerMapperUser mapperUser;

	@Autowired
	private HttpSession session;// TODO 此session存在线程安全问题,后期进行修改

	@Override
	public Integer queryIdByUserId(Integer userId) {
		return mapperVerified.$queryIdByUserId(userId);
	}

	@Override
	public YdMangerVerified queryByUserId(Integer userId) {
		return mapperVerified.$queryByUserId(userId);
	}

	@Override
	public Object carAUpLoad(Integer userId, String carAImgPath) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		YdMangerVerified verified = mapperVerified.$queryByUserId(userId);
		if (verified != null) {
			String cara = verified.getV_idcaraimg();
			Integer success = 0;
			if (cara.trim().equals("") || cara == null) {
				success = mapperVerified.$updateVIdCarImgA(carAImgPath, verified.getV_id());
			} else {
				String path = session.getServletContext().getRealPath(cara);
				File file = new File(path);
				if (file.exists()) {
					file.delete();
				}
				success = mapperVerified.$updateVIdCarImgA(carAImgPath, verified.getV_id());
			}
			if (success > 0) {
				mapInitFactory.put("path", carAImgPath);
				mapInitFactory.setMsg("200", "上传成功！");
			} else {
				mapInitFactory.setMsg("502", "上传失败！");
			}
		} else {
			mapInitFactory.setMsg("501", "参数错误！");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object carBUpLoad(Integer userId, String carBImgPath) {
		MapInitFactory mapInitFactory = new MapInitFactory();
		YdMangerVerified verified = mapperVerified.$queryByUserId(userId);
		if (verified != null) {
			String carb = verified.getV_idcarbimg();
			Integer success = 0;
			if (carb.trim().equals("") || carb == null) {
				success = mapperVerified.$updateVIdCarImgB(carBImgPath, verified.getV_id());
			} else {
				String path = session.getServletContext().getRealPath(carb);
				File file = new File(path);
				if (file.exists()) {
					file.delete();
				}
				success = mapperVerified.$updateVIdCarImgB(carBImgPath, verified.getV_id());
			}
			if (success > 0) {
				mapInitFactory.put("path", carBImgPath);
				mapInitFactory.setMsg("200", "上传成功！");
			} else {
				mapInitFactory.setMsg("502", "上传失败！");
			}
		} else {
			mapInitFactory.setMsg("501", "参数错误！");
		}
		return mapInitFactory.getMap();
	}

	@Override
	public Object submitUserMsg(Integer vId, Integer userId, String name, String idNum, Integer yosId) {

		MapInitFactory mapInitFactory = new MapInitFactory();
		YdMangerVerified verified = mapperVerified.$queryById(vId);
		if (verified != null) {
			if (verified.getV_uid() == userId) {
				Integer success = mapperVerified.$updateVName(name, vId, idNum, yosId);
				if (success > 0) {
					Integer isOk = mapperUser.$updateVerifiedState(userId, 3);
					if (isOk > 0) {
						mapperUser.$updateUserName(userId, name);
						YdMangerUserVo userVo = (YdMangerUserVo) session.getAttribute("user");
						userVo.setUser_verified(3);
						session.setAttribute("user", userVo);
						mapInitFactory.setMsg("200", "提交成功，等待后台审核，请耐心等待！");
					} else {
						mapInitFactory.setMsg("504", "申请认证失败，请稍后再试！");
					}
				} else {
					mapInitFactory.setMsg("503", "提交失败，请稍后重试！");
				}
			} else {
				mapInitFactory.setMsg("502", "参数有误！");
			}
		} else {
			mapInitFactory.setMsg("501", "参数错误！");
		}
		return mapInitFactory.getMap();
	}

}
