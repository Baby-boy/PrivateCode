package com.lingang.common.util;

import java.io.PrintWriter;

/**
 * 文件下载处理公共接口
 * 
 * @author admin
 *
 */
public interface DownLoadHandler {

	void callBack(PrintWriter out, Object query);
}
