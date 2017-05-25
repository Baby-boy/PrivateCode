package com.yd.gcj.controller.page;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceUserTeamFile;
import com.yd.gcj.tool.MapInitFactory;

@RestController
@RequestMapping("/page/teamFile")
public class YdMangerControllerPageTeamFile {
	
	@Autowired
	private YdMangerServiceUserTeamFile serviceTeamFile;
	
	@RequestMapping("/queryCount")
	public Object queryCount(Integer userId){
		MapInitFactory mf = new MapInitFactory("200","ok");
		int num = serviceTeamFile.$queryCount(userId);
		System.out.println(num);
		mf.setData(num);
		return mf.getMap();
	}
	
	@RequestMapping("/del")
	public Object del(Integer userId,Integer fileId,HttpServletRequest request){
		MapInitFactory mf = new MapInitFactory();
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if(userVo != null && userVo.getUser_type() > 0){
				int success = serviceTeamFile.$deleteById(fileId);
				if(success > 0){
					mf.setMsg("200", "删除成功！");
				}else{
					mf.setMsg("501", "删除失败！");
				}
			}else{
				mf.setMsg("600", "请登录！");
			}
		} catch (Exception e) {
			mf.setSystemError();
			e.printStackTrace();
		}
		return mf.getMap();
	}
	
	/***
	 * 删除指定用户团队认证信息中所有文件
	 * @param userId
	 * @param request
	 * @return
	 */
	@RequestMapping("dels")
	public Object dels(Integer userId,HttpServletRequest request){
		MapInitFactory mf = new MapInitFactory();
		try {
			YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
			if(userVo != null && userVo.getUser_type() > 0){
				int success = serviceTeamFile.$deleteByUserId(userId);
				if(success > 0){
					mf.setMsg("200", "删除成功！");
				}else{
					mf.setMsg("501", "删除失败！");
				}
			}else{
				mf.setMsg("600", "请登录！");
			}
		} catch (Exception e) {
			mf.setSystemError();
			e.printStackTrace();
		}
		return mf.getMap();
	}
	
}
