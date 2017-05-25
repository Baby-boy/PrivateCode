package com.lingang.common.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 				
* @ClassName: FlattenImageUtils
* @Description: 拼合图像工具类
* @author 
* @date 2015年3月16日 下午4:58:43
 */
public class FlattenImageUtils {
	
	private static Graphics2D g = null;
	
	private static final Logger logger = LoggerFactory.getLogger(FlattenImageUtils.class);

	/**
	 * 导入本地图片到缓冲区
	 */
	public static BufferedImage loadImageLocal(String imgName) {
		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			logger.error("导入本地图片到缓冲区失败",e); 
		}
		return null;
	}

	/**
	 * 导入网络图片到缓冲区
	 */
	public static BufferedImage loadImageUrl(String imgName) {
		try {
			URL url = new URL(imgName);
			return ImageIO.read(url);
		} catch (IOException e) {
			logger.error("导入网络图片到缓冲区失败",e); 
		}
		return null;
	}

	/**
	 * 生成新图片到本地
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {
		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				ImageIO.write(img, "jpg", outputfile);
			} catch (IOException e) {
				logger.error("生成新图片到本地失败",e); 
			}
		}
	}

	/**
	 * 合并图片
	 */
	public static BufferedImage modifyImagetogeter(BufferedImage qrc,BufferedImage blackground,int width, int height) {
		try {
			int w = blackground.getWidth();
			int h = blackground.getHeight();
			g = qrc.createGraphics();
			g.drawImage(blackground, width, height, w, h, null);
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("合并图片失败",e); 
		}
		return qrc;
	}

	public static void main(String[] args) {
		FlattenImageUtils flattenImageUtils = new FlattenImageUtils();
		BufferedImage q = FlattenImageUtils.loadImageLocal("F:\\erweima.png");
		BufferedImage b = FlattenImageUtils.loadImageLocal("F:\\1.png");
		flattenImageUtils.writeImageLocal("F:\\hehe.png",FlattenImageUtils.modifyImagetogeter(q,b,50,50));
		//将多张图片合在一起
		System.out.println("success");
	}
}
