package com.lingang.common.util;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

/**
 *@Description: (导出POIExcel)
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月2日 下午3:45:41
 *@Version:1.0
 */
public class ExcelUtil<T> {

	/**
	 *@Description:导出Excel
	 *@Author: 
	 * @param  title 标题
	 * @param  headers 表头
	 * @param  columns 实体key
	 * @param  data 数据
	 * @param  out 输出流
	 * @param  pattern 时间格式
	 * @param  Exception 异常
	 * @return void
	 * @date 2016年1月12日 下午3:06:38
	 */
    public void exportExcel(String title, String[] headers,String[] columns, Collection<T> data, OutputStream out, String pattern) throws Exception{    
        // 声明一个工作薄    
        HSSFWorkbook workbook = new HSSFWorkbook();    
        // 生成一个表格    
        HSSFSheet sheet = workbook.createSheet(title);    
        // 设置表格默认列宽度为20个字节    
        sheet.setDefaultColumnWidth((short)15);
            
        // 生成一个样式    
        HSSFCellStyle style = workbook.createCellStyle(); 
        // 设置这些样式    
        style.setFillForegroundColor(HSSFColor.GOLD.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体    
        HSSFFont font = workbook.createFont();    
        font.setColor(HSSFColor.VIOLET.index);    
        //font.setFontHeightInPoints((short) 12);    
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    
        // 把字体应用到当前的样式    
        style.setFont(font);    
            
        // 指定当单元格内容显示不下时自动换行    
        style.setWrapText(true);    
          
        // 声明一个画图的顶级管理器   
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
       
       /*  以下可以用于设置导出的数据的样式  */
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  

       /* 产生表格标题行*/
        //表头的样式  
        HSSFCellStyle titleStyle = workbook.createCellStyle();// 创建样式对象  
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中  
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中  
        // 设置字体  
        HSSFFont titleFont = workbook.createFont(); // 创建字体对象  
        titleFont.setFontHeightInPoints((short) 15); // 设置字体大小  
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体  
      //  titleFont.setFontName("黑体"); // 设置为黑体字  
        titleStyle.setFont(titleFont);  
        sheet.addMergedRegion(new Region(0,(short)0,0,(short)(headers.length-1)));//指定合并区域   
        HSSFRow rowHeader = sheet.createRow(0);
        HSSFCell cellHeader = rowHeader.createCell((short)0);   //只能往第一格子写数据，然后应用样式，就可以水平垂直居中  
        HSSFRichTextString textHeader = new HSSFRichTextString(title);    
        cellHeader.setCellStyle(titleStyle);  
        cellHeader.setCellValue(textHeader);  
          
        HSSFRow row = sheet.createRow(1);    
        for (int i = 0; i < headers.length; i++) {    
            HSSFCell cell = row.createCell((short)i);    
            cell.setCellStyle(style);    
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);    
            cell.setCellValue(text);    
         }    
         // 遍历集合数据，产生数据行    
         if(data != null){
             int index = 2;
             for(T t:data){
                 row = sheet.createRow(index); 
                 index++;  
                 for(short i = 0; i < columns.length; i++) {  
                     HSSFCell cell = row.createCell(i);
                     cell.setCellStyle(style2);
                     String fieldName = columns[i];
                     String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);  
                     Class tCls = t.getClass();  
                     Method getMethod = tCls.getMethod(getMethodName, new Class[]{});  
                    // getMethod.getReturnType().isInstance(obj);
                     Object value = getMethod.invoke(t, new Class[]{});  
                     String textValue = null;
                     if(value == null) {  
                         textValue = "";  
                     }else if (value instanceof Date) {  
                         Date date = (Date) value;
                         SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
                          textValue = sdf.format(date);
                      }  else if (value instanceof byte[]) {  
                         // 有图片时，设置行高为60px;  
                         row.setHeightInPoints(60);  
                         // 设置图片所在列宽度为80px,注意这里单位的一个换算  
                         sheet.setColumnWidth(i, (short) (35.7 * 80));  
                         // sheet.autoSizeColumn(i);  
                         byte[] bsValue = (byte[]) value;  
                         HSSFClientAnchor anchor = new HSSFClientAnchor(0,0,1023, 255, (short) 6, index, (short) 6, index);  
                         anchor.setAnchorType(2);  
                         patriarch.createPicture(anchor, workbook.addPicture(  
                               bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));  
                      } else{  
                         //其它数据类型都当作字符串简单处理  
                         textValue = value.toString();  
                      }  
                       
                     if(textValue!= null){  
                         Pattern p = Pattern.compile("^-?\\d+$");    
                         Matcher matcher = p.matcher(textValue);  
                         if(matcher.matches()){  
                            //是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                         }else{
                            HSSFRichTextString richString = new HSSFRichTextString(textValue);  
                            cell.setCellValue(richString);  
                         }  
                      }  
                 }  
             }       
         }    
         workbook.write(out);    
     }
 }  