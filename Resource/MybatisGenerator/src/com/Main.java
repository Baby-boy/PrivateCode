/**
 * 
 */package com;import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 *@Description: (mybatis自动生成start)
 *@Author: lgl(lgl1012dream@foxmail.com)
 *@date:2016年11月24日 上午10:25:45
 *@Version:1.0
 */
public class Main {
	
	 public static void main(String[] args) {
		 try {
			 List<String> warnings = new ArrayList<String>();  
			  boolean overwrite = true;  
			  File configFile = new File("E:\\Software\\Eclipse\\Eclipse-4.5.2-work\\WorkSpace\\dby\\MybatisGenerator\\src\\com\\generatorConfig.xml");  
			  ConfigurationParser cp = new ConfigurationParser(warnings);  
			  Configuration config = cp.parseConfiguration(configFile);  
			  DefaultShellCallback callback = new DefaultShellCallback(overwrite);  
			  MyBatisGenerator myBatisGenerator;
			
			  myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			  myBatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
