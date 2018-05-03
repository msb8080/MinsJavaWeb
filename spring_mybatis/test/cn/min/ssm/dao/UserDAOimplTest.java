package cn.min.ssm.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.min.ssm.po.User;

/**
*@ClassName:UserDAOimplTest
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年4月28日下午4:09:37
*@parameter
*@since
*@return
*/
class UserDAOimplTest {
	
	private ApplicationContext applicationContext;
	//在setUp方法中得到spring这个容器
	@BeforeEach
	void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}

	@Test
	void testFindUserById() throws Exception {
		UserDAO userDao = (UserDAO) applicationContext.getBean("userDao");
		User user = userDao.findUserById(1);
		System.out.println(user);
	}
}
