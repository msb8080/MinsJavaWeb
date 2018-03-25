package com.min.mybatis.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.min.mybatis.po.User;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

/**
*@ClassName:UserDAOimplTest
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年3月24日上午10:05:51
*@parameter
*@since
*@return
*/
class UserDAOimplTest {
	public SqlSessionFactory sqlSessionFactory;
	//此方法是在执行tetFindUserById之前执行
	@BeforeEach
	void setUp() throws Exception {
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}
	@Test
	void testFindUserById() {
		//创建UserDao对象
		UserDAO userDAO = new UserDAOimpl(sqlSessionFactory);
		//调用UserDao的方法
		try {
			User user = userDAO.findUserById(10);
			System.out.println(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
