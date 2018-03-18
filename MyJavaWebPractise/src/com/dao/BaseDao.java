package com.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.beans.Employee;

/**
 * 数据库的增删改查等对数据库的访问和修改
 * @author min
 *
 */
public class BaseDao {
	private static final QueryRunner runner = new QueryRunner();
	public <T> List<T> getForList(String sql,Class<T> clazz,Object ... args){
		List<T> list = null;
		Connection conn =null;
		try {
			conn = DBManager.getInstance().getConnection();
			list = runner.query(conn,sql,new BeanListHandler<T>(clazz),args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			DbUtils.closeQuietly(conn);
		}
		return list;	
	}	
	@SuppressWarnings("unchecked")
	public <T> T get(String sql,Class<T> clazz,Object ... args) {
		List<T> list = null;
		T result = null;
		Connection conn =null;
		try {
			conn = DBManager.getInstance().getConnection();
			list = runner.query(conn, sql,new BeanListHandler<T>(clazz),args);
			result = (T) list.get(0) ;
			
			System.out.println(result instanceof Employee);
			System.out.println(result instanceof List);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		finally {
			DbUtils.closeQuietly(conn);
		}
		return result;	
	}

}
