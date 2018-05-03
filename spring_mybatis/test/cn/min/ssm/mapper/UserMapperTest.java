package cn.min.ssm.mapper;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.min.ssm.po.User;

/**
*@ClassName:UserMapperTest
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年4月28日下午4:32:57
*@parameter
*@since
*@return
*/
class UserMapperTest {
	private ApplicationContext applicationContext;
	@BeforeEach
	void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
	@Test
	void testFindUserById() throws Exception {
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}

}
