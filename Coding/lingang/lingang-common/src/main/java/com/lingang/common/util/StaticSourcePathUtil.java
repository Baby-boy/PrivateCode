package com.lingang.common.util;

import java.util.Set;

/**
 * 静态资源路径工具
 * @author lirui
 */
public class StaticSourcePathUtil {
	
	// 图片URL加前缀
	public static String imgUrlFormat(String imgPath,Integer width,Integer height,Integer index){
		return imgUrlComplexFormat(imgPath, width, height, index, null, null);
	}
	
	/**
	 * 获取图片地址
	 * 
	 * @Title: imgUrlComplexFormat
	 * @Description: TODO
	 * @param imgPath	图片地址
	 * @param width		图框宽度
	 * @param height	图片高度
	 * @param index		
	 * @param quality	图片质量 1~100
	 * @param blank		是否留白 1:留 ；其余不留
	 * @return
	 * @author 周成龙
	 */
	public static String imgUrlComplexFormat(
			String imgPath,Integer width,Integer height,
			Integer index, Integer quality, Integer blank) {
		
		String strPrefix =  getServerName(index);
		
		StringBuilder url = new StringBuilder("");
		if (StringUtils.isStrNull(imgPath)) {
			imgPath = "null";
		}
		if (strPrefix.endsWith("/") && imgPath.startsWith("/")) {
			url.append(strPrefix).append(imgPath.substring(1));
		} else if (!strPrefix.endsWith("/") && !imgPath.startsWith("/")) {
			url.append(strPrefix).append("/").append(imgPath);
		} else {
			url.append(strPrefix).append(imgPath);
		}
		
		String sign = "x";
		if(blank != null && blank.intValue() == 1)
			sign = "&";
		
		String qualityStr = "";
		if(quality != null && quality > 100)
			quality = 100;
		if(quality != null && quality < 0)
			quality = 0;
		if(quality != null && quality > 0)
			qualityStr = "_"+quality;
		
		if (!(width == null || height == null || width == 0 || height == 0)) {
			url.append(".").append(width).append(sign).append(height).append(qualityStr).append(".jpg");
		}
		return url.toString();
	}

	public static String getJsAndCssVersion(){
		//获取js  css version ,默认为1.0
		return PropertyUtils.getString("js_css_version","0.0.1");
	}
	
	public static String getJsAndCssUrl(String url){
		//获取js  css url
		return PropertyUtils.getString("js_css_url") + url + "?v=" + getJsAndCssVersion();
	}
	
	/**
	 * 获取静态资源图片地址
	 * 
	 * @Title: getResourceImg
	 * @Description: TODO
	 * @param url
	 * @return
	 * @author 周成龙
	 */
	public static String getResourceImg(String url) {
		return PropertyUtils.getString("resource_img_url") + url;
	}
	
	
	/**
	 * 获取图片服务器地址
	 * @param index
	 * @return
	 */
	public static String getServerName(int index){
		
		//获取图片服务器地址
		Set<String> setServices = PropertyUtils.getkeys("^FASTDFS_SERVER_0.*");
		
		int size = setServices.toArray().length;
		
		//修改去图片服务器，随机取 modify by zm 2015/4/22
		//int tempIndex = index % size;
		int tempIndex = (int)(Math.random()*size);
		
		return PropertyUtils.getString((String)setServices.toArray()[tempIndex]);
	}
	
	/**
	 * 获取图片服务器地址数量
	 * 
	 * @return	图片服务器地址数量
	 */
	public static int imgUrlCount(){
		
		//获取图片服务器地址
		Set<String> setServices = PropertyUtils.getkeys("^FASTDFS_SERVER_0.*");
		
		int size = setServices.toArray().length;
		return size;
	}

}
