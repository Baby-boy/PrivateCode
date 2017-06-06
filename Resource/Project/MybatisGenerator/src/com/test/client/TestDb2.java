package com.test.client;

import java.sql.*;
public class TestDb2 {
	
	private static Connection conn = null;  
    
    public static Connection getConn(){  
        try {  
            //注意这串字符串的顺序，不同的数据库版本这行不同。  
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
            //conn = DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=QQ","sa","sa");  
            //注：连接SQLServer 2008 没有microsoft，把这个去掉问题解决。  
            conn = DriverManager.getConnection("jdbc:sqlserver://10.40.201.201:1433;DatabaseName=lingang","trs","trsadmin");  
  
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }  
          
        return conn;  
    }  
	
    public static void main(String[] args) {
    	getConn();

    }
}