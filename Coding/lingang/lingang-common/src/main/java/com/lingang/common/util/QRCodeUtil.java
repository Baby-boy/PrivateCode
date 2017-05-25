package com.lingang.common.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


/**
 * 				
* @ClassName: QRCodeUtil
* @Description: 生成二维码工具类
* @author 
* @date 2015年3月16日 下午3:50:38
 */
public class QRCodeUtil {

		// 二维码写码器
		private static MultiFormatWriter mutiWriter = new MultiFormatWriter();
		//二维码容错率，分四个等级：H、L 、M、 Q
		private static ErrorCorrectionLevel level = ErrorCorrectionLevel.H;
		//二维码白边宽度，分五个等级：0~4
		private static int MARGIN = 1;
		
		private static final Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);

		/**
		* @Title: encode
		* @Description: 生成有背景的二维码,如果没有背景图则生成不带背景图的二维码
		* @param content 待生成内容
		* @param width 二维码宽
		* @param height 二维码高
		* @param blackgroundImagePath 背景图片路径  
		* @param x 二维码在背景图片的x轴
		* @param y 二维码在背景图片的y轴
		* @return destImagePath  目标生成后地址
		* @author 
		* @date 2015年3月16日 下午5:27:31
		 */
	/*	public static String encode(String content, int width, int height,String blackgroundImagePath,int x, int y) {
            String destImagePath = "";
			destImagePath = uploadAndGetImgUrl(genBarcode(content, width, height,blackgroundImagePath,x,y));
			//获取二维码图片url
			return destImagePath;
		}*/
		
	 public static BufferedImage genBarcode(String content, int width,int height,String blackgroundImagePath,int x, int y){
			Hashtable<EncodeHintType,Object> hints = new Hashtable<EncodeHintType,Object>();
			//设置编码
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			//设置容错率
			hints.put(EncodeHintType.ERROR_CORRECTION, level);
			//设置二维码白边宽度
			hints.put(EncodeHintType.MARGIN,MARGIN);
			//二维码图片
			BufferedImage qtc = null;
			//生成的二维码
			BitMatrix matrix = null;
			//去除二维码白边
			BitMatrix resMatrix = null; 
			try {
				matrix = mutiWriter.encode(content,BarcodeFormat.QR_CODE,width,height,hints);
				//去白边
				int[] rec = matrix.getEnclosingRectangle();  
				int resWidth = rec[2] + 1;
				int resHeight = rec[3] + 1;
				resMatrix = new BitMatrix(resWidth,resHeight);  
				resMatrix.clear();  
				for (int i = 0; i < resWidth; i++) {
				    for (int j = 0; j < resHeight; j++) {
				        if (matrix.get(i + rec[0], j + rec[1])) {
				             resMatrix.set(i,j); 
				        } 
				    }  
				}  
			} catch (WriterException e) {
				logger.error("二维码生成错误",e); 
				return qtc;
			}
			try {
				qtc = MatrixToImageWriter.toBufferedImage(resMatrix);
				if(!StringUtils.isStrNull(blackgroundImagePath)){
					//合成图片
					qtc = FlattenImageUtils.modifyImagetogeter(qtc,FlattenImageUtils.loadImageLocal(blackgroundImagePath),x,y);
				}
			} catch (Exception e) {
				logger.error("二维码再次合成错误",e); 
			}
			return qtc;
		}
		
/*		public static String uploadAndGetImgUrl(BufferedImage bi) {
			String imgUrl = null;
			InputStream is = null;
			is = getImageStream(bi);
			FastDFSResult result = FastDFS.uploadFile(is);
			if (result.is_success) {
				imgUrl = result.remote_file_url;
				//关闭流
				if (is != null ) {
					try {
						is.close();
					} catch (IOException e) {
						logger.error("",e);
					}
				}
			} 
			return  imgUrl;
		}*/
		
		public static InputStream getImageStream(BufferedImage bi){ 
	        InputStream is = null; 
	        ByteArrayOutputStream bs = new ByteArrayOutputStream();  
	        ImageOutputStream imOut; 
	        try { 
	            imOut = ImageIO.createImageOutputStream(bs); 
	            ImageIO.write(bi, "jpg",imOut); 
	            is= new ByteArrayInputStream(bs.toByteArray()); 
	        } catch (IOException e) { 
	        	logger.error("",e); 
	        }  
	        return is; 
	    }
		
     public static BufferedImage getBufferedImage(byte[] b){ 
			//将b作为输入流；
			ByteArrayInputStream in = new ByteArrayInputStream(b);    
			BufferedImage image = null;
			try {
				image = ImageIO.read(in);
			} catch (IOException e) {
				logger.error("",e);
			} 
	        return image; 
	    }
     
     public static void main(String[] args) {
    	 String content = "快点下班吧";
    	 String blackgroundImagePath = "";
    	 
    	 BufferedImage im = genBarcode(content,211,211,blackgroundImagePath,0,0);
    	 try {
			ImageIO.write(im,"png",new File("F:/erweima.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 
	}
     
/*     public static void main(String[] args) {
 		String contents = "https://";
 		Hashtable<EncodeHintType,Object> hints = new Hashtable<EncodeHintType,Object>();
 		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
 		hints.put(EncodeHintType.MARGIN,0);
 		BitMatrix matrix = null;
 		try {
 			matrix = new MultiFormatWriter().encode(contents,BarcodeFormat.QR_CODE, 157, 144, hints);
 		} catch (WriterException e) {
 			e.printStackTrace();
 		}
 		File file = new File("F:/erweima.png");
 		try {
 			MatrixToImageWriter.writeToFile(matrix, "png", file);
 		} catch (IOException e) {
 			e.printStackTrace();
		}
    	 String url = QRCodeUtil.encode("http://smokeimg3.hxshop.com/g1/M00/00/3E/rBAKBFUKh2CASs77AAFwCUVrwBk540.jpg", 
    			 700, 700, "http://smokeimg.hxshop.com//g1/M00/00/3E/rBAKBFUKfqCAAjscAAqXRihNX0s669.jpg", 
    			 240, 890);
    //	 System.out.println(url);
 	}*/
}