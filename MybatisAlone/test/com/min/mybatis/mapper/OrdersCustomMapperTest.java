package com.min.mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.min.mybatis.po.OrderCustom;
import com.min.mybatis.po.Orders;
import com.min.mybatis.po.User;

/**
 *@ClassName:OrdersCustomMapperTest
 *@Description:TODO
 *@author:minshuaibo
 *@date创建时间：2018年4月24日下午1:53:30
 *@parameter
 *@since
 *@return
 */
@RunWith(JUnitPlatform.class)
@DisplayName("Testing using JUnit 5")
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
		
	//测试订单查询延迟加载关联查询的用户信息
	@Test 
	void testFindOrdersUserLazyLoading() {
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		OrdersCustomMapper ordersCustomMapper = sqlSession.getMapper(OrdersCustomMapper.class);
		List<Orders> orders = ordersCustomMapper.findOrdersUserLazyLoading();
		for(Orders order:orders) {
			System.out.println(order.getUser());
		}
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
		//关闭sql会话
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
