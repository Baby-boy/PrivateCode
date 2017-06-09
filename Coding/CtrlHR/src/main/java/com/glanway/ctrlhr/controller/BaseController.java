package com.glanway.ctrlhr.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

/**
 * 每个项目都有的 Base Controller, 项目中 Controller 都应该继承, 方便后续增加逻辑
 *
 * @author yangchanghe
 * @see com.glanway.eshop.admin.controller.AdminBaseController
 */
public abstract class BaseController {

    /**
     * 导出功能使用
     *
     * @author fuqihao
     * @param cvsList
     * @param bw
     * @throws IOException
     * @since 1.0-20170526
     */
    protected void writeLine(final List<String> cvsList, BufferedWriter bw) throws IOException {
        StringBuffer buffer = new StringBuffer();
        for (String line : cvsList) {
            if (null == line) {
                line = "";
            }
            buffer.append(line).append(",");
        }
        /* bw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF })); */
        bw.write(buffer.deleteCharAt(buffer.length() - 1).toString());
        bw.newLine();
    }
}
