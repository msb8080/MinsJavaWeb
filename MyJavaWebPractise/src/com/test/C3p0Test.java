package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Test {
	 private static DataSource c3p0DataSource;  
	    static {  
	        c3p0DataSource = new ComboPooledDataSource("mysql");  
	    }  
	  
	    /** 
	     * 获取数据库连接 
	     *  
	     * @return 
	     */  
	    public static Connection getConnection() {  
	        Connection connection = null;  
	        try {  
	            connection = c3p0DataSource.getConnection();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	        return connection;  
	    }  
	  
	    /** 
	     *  
	     * 关闭连接 
	     *  
	     * @param connection 
	     * @param preparedStatement 
	     * @param resultSet 
	     */  
	    public static void close(Connection connection,  
	            PreparedStatement preparedStatement, ResultSet resultSet) {  
	        if (resultSet != null) {  
	            try {  
	                resultSet.close();  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        if (preparedStatement != null) {  
	            try {  
	                preparedStatement.close();  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        if (connection != null) {  
	            try {  
	                connection.close();  
	            } catch (SQLException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	  
	    public static void main(String[] args) {  
	        Connection conn = getConnection();  
	        System.out.println(conn.getClass().getName());  
	        close(conn, null, null);  
	    }  
}
