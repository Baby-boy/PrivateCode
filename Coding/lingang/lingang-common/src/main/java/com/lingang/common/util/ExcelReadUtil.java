package com.lingang.common.util;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *@Description: Excel读取工具类(97-2003格式Excel)
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年12月2日 下午3:45:10
 *@Version:1.0
 */
public class ExcelReadUtil {
	
	private static Log log = LogFactory.getLog(ExcelReadUtil.class);
	
	/**
	 * @param inputStream 输入流数据
	 * @return List<Map<String,String>> 数据以表头和值的形式保存到map中
	 */
	public static List<Map<String,String>> getData(InputStream inputStream){
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Map<String,String> rowCellValue=null;
		
		
		 BufferedInputStream in=null;
		 POIFSFileSystem  poifs=null;
		 HSSFWorkbook wb=null;
		
		HSSFCell cell=null;
		//表头所在列索引
		Map<String,Integer> cmCellIndex=new HashMap<String,Integer>();
		boolean sheetflag=false;
		try {
			in=new BufferedInputStream(inputStream);
			poifs=new POIFSFileSystem(in);
			wb=new HSSFWorkbook(poifs);
			for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++){
				HSSFSheet st=wb.getSheetAt(sheetIndex);
				for(int rowIndex=0;rowIndex<=st.getLastRowNum();rowIndex++){
					rowCellValue=new HashMap<String,String>();
					rowCellValue.put("rowIndex", (rowIndex+1)+"");
					HSSFRow row=st.getRow(rowIndex);
					if(row==null){
						continue;
					}
					for(int columnIndex=0;columnIndex<row.getLastCellNum();columnIndex++){
						cell=row.getCell(columnIndex);
						//int type=cell.getCellType();
						String value=getCellFormatValue(cell);
						
						//将excel表头所在列位置记录
						if(rowIndex==0){
							if(sheetflag){
								cmCellIndex.clear();
							}
							cmCellIndex.put(value.toString(), columnIndex);
						}else{
							//将excel文档中的标题和值key-value保存
							for(String key:cmCellIndex.keySet()){
								int cellIndex=cmCellIndex.get(key);
								if(columnIndex==cellIndex){
									rowCellValue.put(key, value.toString());
								}
							}
						}
					}
					if(rowIndex!=0){
						list.add(rowCellValue);
					}
					
				}
				sheetflag=true;
			}
			//关闭流
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}finally{
			if(null!=in){
				try {
					in.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		return list;
	}
	
	
	private static  String getCellFormatValue(HSSFCell cell){
		String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:{
            	if(cell.getNumericCellValue()>=100000){
            		DecimalFormat df = new DecimalFormat("0");  
            		cellvalue = df.format(cell.getNumericCellValue());  
            		break;
            	}
            }
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return StringUtils.deleteWhitespace(cellvalue);
	}
	
	/**
	 * 获取exeel文件内容
	 * @return List<Map<String,String>> 数据以表头和值的形式保存到map中
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String,String> getData2(File file){
		
		Map<String,String> rowCellValue=null;
		
		 BufferedInputStream in=null;
		 POIFSFileSystem  poifs=null;
		 HSSFWorkbook wb=null;
		 Map data = new HashMap();
		
		HSSFCell cell=null;
		//表头所在列索引
		Map<String,Integer> cmCellIndex=new HashMap<String,Integer>();
		boolean sheetflag=false;
		try {
			in=new BufferedInputStream(new FileInputStream(file));
			poifs=new POIFSFileSystem(in);
			wb=new HSSFWorkbook(poifs);
			for(int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++){
				HSSFSheet st=wb.getSheetAt(sheetIndex);
				String sheetName = st.getSheetName();
				List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				for(int rowIndex=0;rowIndex<=st.getLastRowNum();rowIndex++){
					rowCellValue=new HashMap<String,String>();
					HSSFRow row=st.getRow(rowIndex);
					for(int columnIndex=0;null != row && columnIndex<row.getLastCellNum();columnIndex++){
						cell=row.getCell(columnIndex);
						String value=getCellFormatValue(cell);
						//将excel表头所在列位置记录
						if(rowIndex==0){
							if(sheetflag){
								sheetflag=false;
								cmCellIndex.clear();
							}
							cmCellIndex.put(value.toString(), columnIndex);
						}else{
							//将excel文档中的标题和值key-value保存
							for(String key:cmCellIndex.keySet()){
								int cellIndex=cmCellIndex.get(key);
								if(columnIndex==cellIndex){
									rowCellValue.put(key, value.toString());
								}
							}
						}
					}
					if(rowIndex!=0){
						list.add(rowCellValue);
					}
				}
				data.put(sheetName, list);
				sheetflag=true;
			}
			//关闭流
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}finally{
			if(null!=in){
				try {
					in.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		return data;
	}
	
	public static void main(String[] args) {
		File file=new File("D:\\test.xls");
		try {
			// u=new ExcelReadUtil(file);
			List<Map<String,String>> list = ExcelReadUtil.getData(new FileInputStream(file));
			for(int i=0;i<list.size();i++){
				Map<String,String> map = list.get(i);
				StringBuffer s=new StringBuffer();
				for(String key:map.keySet()){
					s.append(key+"="+map.get(key));
				}
			}
			System.out.println(list.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}