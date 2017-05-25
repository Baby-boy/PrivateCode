package com.yd.gcj.controller.page;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.util.MyStaticFactory;

@RestController
@RequestMapping("/")
public class YdMangerControllerPageImageFile {

	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/headimg/{imgName}.{type}")
	public void getImage(@PathVariable String imgName, @PathVariable String type) {
		if (type.equals("jpg") || type.equals("png") || type.equals("jpeg")) {
			String pathName = MyStaticFactory.systemPath + MyStaticFactory.headimg + imgName + "." + type;
			response.setContentType("text/html; charset=UTF-8");
			response.setContentType("image/" + type);
			FileInputStream fis = null;
			OutputStream os = null;
			try {
				fis = new FileInputStream(pathName);
				os = response.getOutputStream();
				int count = 0;
				byte[] buffer = new byte[1024 * 1024];
				while ((count = fis.read(buffer)) != -1) {
					os.write(buffer, 0, count);
				}
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null)
						os.close();
					if (fis != null)
						fis.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@RequestMapping("/verifiedImg/{imgName}.{type}")
	public void getVerified(@PathVariable String imgName, @PathVariable String type, HttpServletRequest request) {
		YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
		if (userVo != null && (type.equals("jpg") || type.equals("png") || type.equals("jpeg"))) {
			String pathName = MyStaticFactory.systemPath + MyStaticFactory.verifiedImg + imgName + "." + type;
			response.setContentType("text/html; charset=UTF-8");
			response.setContentType("image/" + type);
			FileInputStream fis = null;
			OutputStream os = null;
			try {
				fis = new FileInputStream(pathName);
				os = response.getOutputStream();
				int count = 0;
				byte[] buffer = new byte[1024 * 1024];
				while ((count = fis.read(buffer)) != -1) {
					os.write(buffer, 0, count);
				}
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null)
						os.close();
					if (fis != null)
						fis.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
