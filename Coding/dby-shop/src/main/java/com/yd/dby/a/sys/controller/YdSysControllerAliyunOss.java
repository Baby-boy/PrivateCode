package com.yd.dby.a.sys.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "aliyun/oss")
public class YdSysControllerAliyunOss {

	private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	private static String accessKeyId = "LTAIQqrJHVmXJ7Vp";
	private static String accessKeySecret = "HLsGQc8BYaiblPM5aIPgxRfNGjnGzl";
	private static String bucketName = "woadby";
	private static String key = "aa.jpg";
	private static String uploadFile = "C:/Users/Administrator/Desktop/dby/uploads/goods/00a6b407-a065-4e94-abc6-c1e1cf171608.jpg";
	
	
	@RequestMapping(value = "test")
	public String test() {
		return "upload";
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		
		
		
		response.setContentType("text/html;charset=UTF-8");

	    // Create path components to save the file
	    final String path = request.getParameter("destination");
	    final Part filePart = request.getPart("file");
	    final String fileName = getFileName(filePart);
		
		System.out.println( filePart.getInputStream() );
		
		
	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();

	    try {
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        writer.println("New file " + fileName + " created at " + path);
	        
	        System.out.println(1);
	    } catch (FileNotFoundException fne) {
	        writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        writer.println("<br/> ERROR: " + fne.getMessage());

	        System.out.println(2);
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	        if (writer != null) {
	            writer.close();
	        }
	    }
	    
	    
		
//		InputStream inputStream = null;
//        FileOutputStream outputStream = null;
//        
//        
//    	inputStream = request.getInputStream();
//    	
//    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//    	
//    	MultipartFile multipartFile = multipartRequest.getFile("file");
//    	
//    	System.out.println( multipartFile.getContentType() );
//   
//        
//		// 创建OSSClient实例
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		
//		inputStream = request.getInputStream();
//		
//		
//		// 上传文件
//		ossClient.putObject("woadby", "abc.png", request.getInputStream() );
//		// 关闭client
//		ossClient.shutdown();
		
		
		
		
		
		
		
		
//		System.out.println(request.getRealPath("/"));
//		
//		System.out.println(request.getSession().getServletContext().getRealPath("/"));
//		
//		System.out.println("-------------------------------------------------------");
//		
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		
//		
//		MultipartFile multipartFile = multipartRequest.getFile("file");
//		
////		File file=(File) multipartRequest.getFile("file");
//		
//		
//		String suffix = multipartFile.getOriginalFilename().substring(
//                multipartFile.getOriginalFilename().lastIndexOf("."));
//		
//		
//		uploadFile = multipartFile.getOriginalFilename();
//		
//		key = Long.valueOf( System.currentTimeMillis() ).toString();
//		
////		System.out.println( multipartFile.getOriginalFilename() );
//		
//		
//		
//		
//		
//		
//		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
//				accessKeySecret);
//
//		try {
//			UploadFileRequest uploadFileRequest = new UploadFileRequest(
//					bucketName, key);
//			// 待上传的本地文件
//			uploadFileRequest.setUploadFile(uploadFile);
//			// 设置并发下载数，默认1
//			uploadFileRequest.setTaskNum(5);
//			// 设置分片大小，默认100KB
//			uploadFileRequest.setPartSize(1024 * 1024 * 1);
//			// 开启断点续传，默认关闭
//			uploadFileRequest.setEnableCheckpoint(true);
//
//			UploadFileResult uploadResult = ossClient
//					.uploadFile(uploadFileRequest);
//
//			CompleteMultipartUploadResult multipartUploadResult = uploadResult
//					.getMultipartUploadResult();
//			System.out.println(multipartUploadResult.getETag()+"============");
//
//		} catch (OSSException oe) {
//			System.out
//					.println("Caught an OSSException, which means your request made it to OSS, "
//							+ "but was rejected with an error response for some reason.");
//			System.out.println("Error Message: " + oe.getErrorCode());
//			System.out.println("Error Code:       " + oe.getErrorCode());
//			System.out.println("Request ID:      " + oe.getRequestId());
//			System.out.println("Host ID:           " + oe.getHostId());
//		} catch (ClientException ce) {
//			System.out
//					.println("Caught an ClientException, which means the client encountered "
//							+ "a serious internal problem while trying to communicate with OSS, "
//							+ "such as not being able to access the network.");
//			System.out.println("Error Message: " + ce.getMessage());
//		} catch (Throwable e) {
//			e.printStackTrace();
//		} finally {
//			ossClient.shutdown();
//		}
		return "456";
	}

	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");

	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}
