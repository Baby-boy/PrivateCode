package com.yd.dby.app.util;

public class Base64ImageUtil {

	// private static String diskImgPath = "D:/lgl/test/";

	// public static void main(String[] args) {
	// String strImg1 = imageToBase64Str("D:/lgl/test/base64test1.jpg");
	// String strImg2 = imageToBase64Str("D:/lgl/test/base64test2.jpg");
	// String baseStr = strImg1 + "," + strImg2;
	// String[] imgs = baseStr.split(",");
	// for (String imgStr : imgs) {
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	// String dateStr = sdf.format(new Date());
	// System.out.println("----------------------------------------");
	// System.out.println(imgStr);
	// base64StrToImage(imgStr, diskImgPath + dateStr, ".png");
	// }
	// }

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imgFile
	 *            base64的图片字符串
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月12日 下午1:45:06
	 */
	// public static String imageToBase64Str(String imgFile) {
	// InputStream in = null;
	// byte[] data = null;
	// // 读取图片字节数组
	// try {
	// in = new FileInputStream(imgFile);
	// data = new byte[in.available()];
	// in.read(data);
	// in.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// // 对字节数组Base64编码
	// BASE64Encoder encoder = new BASE64Encoder();
	// // 返回Base64编码过的字节数组字符串
	// return encoder.encode(data);
	// }

	/**
	 * base64字符串转化成图片
	 * 
	 * @param base64String(base64的字符串)
	 * @param imagePath(图片生成路径)
	 * @param format(图片格式)
	 * @return
	 * @author lgl(lgl@zwzyd.com)
	 * @date: 2017年2月12日 下午1:47:35
	 */
	// public static boolean base64StrToImage(String base64String, String imagePath, String format) {
	// // 图像数据为空
	// if (base64String == null)
	// return false;
	// BASE64Decoder decoder = new BASE64Decoder();
	// try {
	// // Base64解码
	// byte[] b = decoder.decodeBuffer(base64String);
	// for (int i = 0; i < b.length; ++i) {
	// // 调整异常数据
	// if (b[i] < 0) {
	// b[i] += 256;
	// }
	// }
	// // 生成图片
	// String imgFilePath = imagePath + format;
	// OutputStream out = new FileOutputStream(imgFilePath);
	// out.write(b);
	// out.flush();
	// out.close();
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	// }
}