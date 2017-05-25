package com.test.client;

import java.sql.*;
public class TestDb {
    public static void main(String[] args) {
        
        String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//        String url = "jdbc:microsoft:sqlserver://192.168.152.1:1433;DatabaseName=GTest";
        String url = "jdbc:sqlserver://10.40.201.201:1433;databasename=lingang";
//        String user= "sa";
//        String password = "123";
        String user= "trs";
        String password = "trsadmin";
        
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException ex) {
            System.out.println("加载错误！");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("连接成功");
        } catch (SQLException ex1) {
            System.out.println(ex1);
            System.out.println("失败");
        }
    }
}