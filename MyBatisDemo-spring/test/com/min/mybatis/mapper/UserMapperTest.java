package com.min.mybatis.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.min.mybatis.po.User;
import com.min.mybatis.po.UserCustom;
import com.min.mybatis.po.UserQueryVo;

/**
 *@ClassName:UserMapperTest
 *@Description:TODO
 *@author:minshuaibo
 *@date创建时间：2018年3月24日上午10:46:59
 *@parameter
 *@since
 *@return
 */
class UserMapperTest {
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
	@Test
	void testFindUserById() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建userMapper实现类
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(10);
		sqlSession.close();
		System.out.println(user);

	}
	@Test
	void testFindUserByName() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建userMapper实现类
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.findUserByName("邓丽君");
		sqlSession.close();
		System.out.println(list);
	}
	//用户信息综合查询测试
	@Test
	void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建userMapper实现类
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		//由于使用了动态sql，如果某个查询参数未设置，则这个条件不会拼接在sql语句中
//		userCustom.setSex("1");
		//设置多个id
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(10);
		ids.add(16);
		userQueryVo.setIds(ids);
//		userCustom.setUsername("张三");
		userQueryVo.setUserCustom(userCustom);
		List<UserCustom> list = userMapper.findUserList(userQueryVo);
		//		sqlSession.close();
		System.out.println(list);
	}
	//用户信息综合查询列表总数测试
	@Test
	void testFindUserCount() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建userMapper实现类
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("张三");
		userQueryVo.setUserCustom(userCustom);
		int count = userMapper.findUserCount(userQueryVo);
		//			sqlSession.close();
		System.out.println(count);
	}

	//根据用户id查询用户信息，结果类型用resultMap来指定的测试
	@Test
	void testFindUserByIdResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建userMapper实现类
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list= userMapper.findUserByIdResultMap(10);
		sqlSession.close();
		System.out.println(list);
	}

}
