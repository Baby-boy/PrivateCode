package com.lingang.platform.controller.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lingang.api.domain.basic.JsonResult;
import com.lingang.api.domain.basic.ServiceResult;
import com.lingang.api.domain.basic.StateCodeConstant;
import com.lingang.api.domain.entity.SysFile;
import com.lingang.api.domain.entity.SysManager;
import com.lingang.api.service.SysFileService;
import com.lingang.api.service.SysLogService;
import com.lingang.common.util.IpAddressUtil;

@Controller
@RequestMapping("/parkFileUpload")
public class ParkFileUploadController {

	@Resource
	private SysLogService sysLogService;

	// 存储在项目里的相对路径
	private String uploadPath = "upload/park/";

	// 项目发布路径
	private String webAppUrl = "/image/upload/park/";

	// 存储在磁盘上的路径
	private String webAppRoot = getRootPath();

	@Resource
	private SysFileService sysFileService;

	// @Resource
	// private IdWorkerServiceApi idWorker;

	// public static void main(String[] args) {
	// Double fileSize=12.3900003433228;
	// fileSize=(double)(Math.round(fileSize*100))/100;
	// System.out.println(fileSize);
	// }

	/**
	 * @Title: fileUpload
	 * @Description: 文件上传
	 * @Param: objectId 文件依附实体ID
	 * @Param: objectNo 文件依附实体编号
	 * @Param: objectType 文件衣服实体类型
	 * @Param: myFile 文件文件
	 * @Param:@return JsonResult
	 * @throws @Author:
	 *             联系方式：
	 * @Since: 2016年2月6日 下午10:06:59
	 */
	@RequestMapping("/fileUpload.do")
	@ResponseBody
	public JsonResult fileUpload(Integer objectId, String objectNo, Integer objectType, MultipartFile myFile,
			HttpServletRequest request, HttpServletResponse response) {
		JsonResult result = new JsonResult();
		if (myFile == null || myFile.getSize() == 0) {
			result.setStateCode(StateCodeConstant.ERROR_CODE_PARAM_NULL);
			result.setMessage("上传文件为空");
			return result;
		}
		// 大小转换
		double fileSize = (double) ((myFile.getSize() * 1.0) / (1024 * 1024));
		fileSize = (double) (Math.round(fileSize * 100)) / 100;
		String originalFileName = myFile.getOriginalFilename();
		String contentType = myFile.getContentType();
		InputStream in;
		try {
			in = myFile.getInputStream();
			ServiceResult<SysFile> resultService = fileUpload(objectId, objectType, objectNo, originalFileName,
					contentType, uploadPath, fileSize, in, request);
			result.setData(resultService.getData());
			result.setStateCode(StateCodeConstant.SUCCESS_CODE);
		} catch (IOException e) {
			result.setMessage("未知错误");
			result.setStateCode(StateCodeConstant.ERROR_CODE);
		}

		return result;
	}

	public ServiceResult<SysFile> fileUpload(Integer objectId, Integer objectType, String objectNo,
			String originalFileName, String contentType, String fileUploadPath, double fileSize, InputStream in,
			HttpServletRequest request) {
		ServiceResult<SysFile> result = new ServiceResult<SysFile>();
		// 上传图片文件夹绝对路径
		String basePath = webAppRoot + "/" + fileUploadPath;
		// 创建路径
		String filePathFragment = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "";
		// 最终文件夹的绝对路径
		String originalFilePath = basePath + filePathFragment;
		// 最终图片网址的相对路径
		String fileUrl = webAppUrl + filePathFragment;
		// 图片
		// String uploadName = contentType;
		// 上传文件的类型
		// String fileType = uploadName.substring(uploadName.indexOf("/") + 1,
		// uploadName.length());
		// 上传文件的后缀名
		String extension = generateFileExtension(originalFileName);
		// 文件名(new SysFile添加使用)
		String fileTitle = originalFileName;
		// 新的文件名
		String newFileName = generateFileName(originalFileName);
		//
		String fullPath = originalFilePath + "/" + newFileName + extension;

		File fileFile = new File(originalFilePath);
		if (!fileFile.exists()) {
			fileFile.mkdirs();
		}
		// 写入磁盘
		try {
			System.out.println(fullPath);
			FileOutputStream fos = new FileOutputStream(fullPath);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			/*
			 * BufferedImage srcBufferImage = ImageIO.read(in);
			 * 
			 * BufferedImage scaledImage; BufferedImage scaledImage2; ScaleImage
			 * scaleImage = ScaleImage.getInstance(); ScaleImage scaleImage2 =
			 * ScaleImage.getInstance(); int yw = srcBufferImage.getWidth(); int
			 * yh = srcBufferImage.getHeight(); //int w = 400, h = 400; //
			 * 如果上传图片 宽高 比 压缩的要小 则不压缩
			 * 
			 * Map<String , Integer> size75 = this.getSmallImgSize(yw,yh,75);
			 * Map<String , Integer> size400= this.getSmallImgSize(yw,yh,400);
			 * 
			 * scaledImage = scaleImage.imageZoomOut(srcBufferImage,
			 * size75.get("w"), size75.get("h")); FileOutputStream out = new
			 * FileOutputStream(fullPath.replace("s_"+newFileName,
			 * "s1_"+newFileName)); ImageIO.write(scaledImage, "jpeg", out);
			 * 
			 * scaledImage2 = scaleImage2.imageZoomOut(srcBufferImage,
			 * size400.get("w"), size400.get("h")); FileOutputStream o = new
			 * FileOutputStream(fullPath); ImageIO.write(scaledImage2, "jpeg",
			 * o);
			 */
			fileUrl += "/" + newFileName + extension;
			// 插入数据库
			SysFile file = new SysFile();
			file.setFileAddress(fileUrl);
			file.setFileType(objectType);
			file.setParkId(objectId);
			file.setFileTitle(fileTitle);
			Date nowDate = new Date();
			file.setCreateTime(nowDate);
			file.setUpdateTime(nowDate);
			file.setFatherId(0);
			file.setFileSize(fileSize);
			file.setFileRemark(objectNo);
			ServiceResult<SysFile> resultFile = sysFileService.addSysFile(file);
			result.setData(resultFile.getData());

			// 记录日志
			if (result.getStateCode().equals(StateCodeConstant.SUCCESS_CODE)) {
				HttpSession session = request.getSession();
				SysManager manager = (SysManager) session.getAttribute("manager");
				sysLogService.insertSysLog(manager.getManagerAccount(), IpAddressUtil.getIpAddress(request), nowDate,
						"上传园区文件");
			}
		} catch (IOException e) {
			result.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	private String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		return formatDate + random;
	}

	private String generateFileExtension(String fileName) {
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return extension;
	}

	@SuppressWarnings("unused")
	private Map<String, Integer> getSmallImgSize(int srcWi, int srcHi, double baseSize) {
		double srcW = srcWi;
		double srcH = srcHi;
		double w = baseSize, h = baseSize;
		if (srcW >= baseSize && srcH >= baseSize) {
			if (srcW >= srcH) {
				w = baseSize;
				h = (baseSize / srcW) * srcH;
			} else {
				h = baseSize;
				w = (baseSize / srcH) * srcW;
			}
		}

		if (srcW >= baseSize && srcH < baseSize) {
			w = baseSize;
			h = (baseSize / srcW) * srcH;
		}

		if (srcH >= baseSize && srcW < baseSize) {
			h = baseSize;
			w = (baseSize / srcH) * srcW;
		}

		if (srcW < baseSize && srcH < baseSize) {
			if (srcW >= srcH) {
				w = baseSize;
				h = (baseSize / srcW) * srcH;
			} else {
				h = baseSize;
				w = (baseSize / srcH) * srcW;
			}
		}
		int wi = (int) w;
		int hi = (int) h;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("w", wi);
		map.put("h", hi);
		return map;
	}

	public String getRootPath() {
		String classPath = ParkFileUploadController.class.getClassLoader().getResource("/").getPath();
		String rootPath = "";
		// windows下
		if ("\\".equals(File.separator)) {
			rootPath = classPath.substring(1, classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("%20", " ");
			rootPath = "D:\\file";
		}
		// linux下
		if ("/".equals(File.separator)) {
			rootPath = classPath.substring(0, classPath.indexOf("/WEB-INF/classes"));
			rootPath = "/mydata/file";
		}
		return rootPath;
	}
}
