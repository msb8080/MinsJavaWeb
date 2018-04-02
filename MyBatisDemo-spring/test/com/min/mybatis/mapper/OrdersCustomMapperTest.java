package com.min.mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.min.mybatis.po.OrderCustom;
import com.min.mybatis.po.Orders;
import com.min.mybatis.po.User;

/**
 *@ClassName:OrdersCustomMapperTest
 *@Description:TODO
 *@author:minshuaibo
 *@date创建时间：2018年3月31日下午9:41:01
 *@parameter
 *@since
 *@return
 */
class OrdersCustomMapperTest {

	public SqlSessionFactory sqlSessionFactory;
	@BeforeEach
	void setUp() throws Exception {
		//mybatis配置文件
		String resource = "SqlMapConfig.xml";
		//得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	//测试使用resultType查询订单信息关联查询用户信息
	@Test
	void testFindOrdersUser() {
		//SQL会话
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		//生成映射代理对象
		OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);
		//调用mapper对象中的函数进行查询
		List<OrderCustom> list = ordersCustomMapper.findOrdersUser();
		System.out.println(list);
		sqlSession.close();
	}

	//测试使用resultMap查询订单信息关联查询用户信息
	@Test
	void testFindOrdersUserResultMap() {
		//SQL会话
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		//生成映射代理对象
		OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);
		//调用mapper对象中的函数进行查询
		List<Orders> list = ordersCustomMapper.findOrdersUserResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	//测试使用resultMap查询订单信息关联查询用户信息
	@Test
	void testFindOrdersAndOrderDetailResultMap() {
		//SQL会话
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		//生成映射代理对象
		OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);
		//调用mapper对象中的函数进行查询
		List<Orders> list = ordersCustomMapper.findOrdersAndOrderDetailResultMap();
		System.out.println(list);
		sqlSession.close();
	}
	//测试使用resultMap查询订单信息关联查询用户信息
	@Test
	void testFindUserAndItemsResultMap() {
		//SQL会话
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		//生成映射代理对象
		OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);
		//调用mapper对象中的函数进行查询
		List<User> list = ordersCustomMapper.findUserAndItemsResultMap();
		System.out.println(list);
		sqlSession.close();
	}

}
