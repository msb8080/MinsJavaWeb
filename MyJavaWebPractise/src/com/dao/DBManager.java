package com.dao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 负责从数据库连接池中获取到指定数据库的连接
 * @author min
 *
 */
public class DBManager {
	private static  DataSource dataSource;
	  static{
	         //只被创建一次
	       dataSource=new ComboPooledDataSource("mysql");  // myc3p0 一定要与配置文件中的名字一样
	   } 
	 /**
	    * 返回数据源的一个Connection 对象
	    * @return
	    * @throws Exception 
	    */
	   public  Connection getConnection() throws Exception{
		   Connection conn =null;
		   if(dataSource!=null) {
			   try {
				   conn = dataSource.getConnection();
				   
			   }catch(SQLException e) {
				   throw new RuntimeException("获取数据库连接失败");
			   }
		   }
	       return conn;
	    }    
	   private DBManager() {}
	   //单例模式，构造函数私有化，类名调用静态方法，获取静态属性，实例
	   private static  DBManager instance = new DBManager();
	   public static  DBManager getInstance() {
		   return instance;
	   }
}
