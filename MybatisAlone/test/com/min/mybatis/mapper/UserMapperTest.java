package com.min.mybatis.mapper;

import java.io.InputStream;
import java.util.ArrayList;
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
//使junit识别
@RunWith(JUnitPlatform.class)
@DisplayName("Testing using JUnit 5")
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
	
	//测试mybatis的二级缓存
	@Test
	void testCache2() throws Exception {
		//获取三个sql会话
		SqlSession sqlSession1 = sqlSessionFactory.openSession(); 
		SqlSession sqlSession2 = sqlSessionFactory.openSession(); 
		SqlSession sqlSession3 = sqlSessionFactory.openSession(); 
		
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		//第一次查询id为1的用户
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);
		//这里执行关闭操作，将sqlSession1中的数据写到二级缓存区域
		sqlSession1.close();
		//通过sqlsession3的commit()操作
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		User user = userMapper3.findUserById(1);
		user.setUsername("张龙");
		userMapper3.updateUser(user);
		sqlSession3.commit();
		sqlSession3.close();
		//第二次查询id为1的用户
		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);
		sqlSession2.close();
	}

	
	//测试 mybatis的一级缓存
	@Test
	void testCache1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//第一次查询id为1的用户
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		//如果sqlSession去执行commit操作(执行插入、更新、删除)，清空SqlSession中的一级缓存，
		//这样做的目的是为了让缓存中存储的是最新的信息，避免读到脏数据。
		user1.setUsername("王五21");
		userMapper.updateUser(user1);
		//执行commit操作去清空缓存
		sqlSession.commit();
		//第二次查询id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		sqlSession.close(); //记得关闭sql会话
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
