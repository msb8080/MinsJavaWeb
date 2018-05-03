package test;
/**
 *@ClassName:MybatisTest1
 *@Description:TODO
 *@author:minshuaibo
 *@date创建时间：2018年3月20日下午2:05:05
 *@parameter
 *@since
 *@return
 */


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.min.mybatis.po.User;

@RunWith(JUnitPlatform.class)
@DisplayName("Testing using JUnit 5")
public class MybatisTest1 {
	//根据用户id查询用户信息，得到一条记录结果
	@Test
	public void findUserByIdTest() {
		InputStream inputStream = null;
		SqlSessionFactory sessionFactory =null;
		SqlSession sqlSession =null;
		//mybatis配置文件
		try { 
			String resource = "SqlMapConfig.xml";
			inputStream = Resources.getResourceAsStream(resource);
			//创建会话工厂，传入mybatis的配置文件信息
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//通过工厂得到SqlSession对象
			sqlSession =  sessionFactory.openSession();
			//通过SqlSession操作数据库
			User user = sqlSession.selectOne("test.findUserById", 10);
			System.out.println(user.getUsername());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	@Test
	public void findUserByNameTest() {
		InputStream inputStream = null;
		SqlSessionFactory sessionFactory =null;
		SqlSession sqlSession =null;
		//mybatis配置文件
		try { 
			String resource = "SqlMapConfig.xml";
			inputStream = Resources.getResourceAsStream(resource);
			//创建会话工厂，传入mybatis的配置文件信息
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//通过工厂得到SqlSession对象
			sqlSession =  sessionFactory.openSession();
			//通过SqlSession操作数据库
			List<User> users = sqlSession.selectList("test.findUserByName", "小明");
			for(User user:users) {
				System.out.println(user.toString());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	//添加新用户
	@Test
	public void addUserTest() {
		InputStream inputStream = null;
		SqlSessionFactory sessionFactory =null;
		SqlSession sqlSession =null;
		//mybatis配置文件
		try { 
			String resource = "SqlMapConfig.xml";
			inputStream = Resources.getResourceAsStream(resource);
			//创建会话工厂，传入mybatis的配置文件信息
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//通过工厂得到SqlSession对象
			sqlSession =  sessionFactory.openSession();
			User user = new User();
			user.setUsername("邓丽君");
			user.setSex("女");
			user.setAddress("中国");
			user.setBirthday(new Date());
			System.out.println(user);
			System.out.println(user.getBirthday());
			//通过SqlSession操作数据库
			sqlSession.insert("test.addUser", user);
			sqlSession.commit();
			System.out.println(user.getId());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	//根据用户名删除用户记录
	@Test
	public void removeUserTest() {
		InputStream inputStream = null;
		SqlSessionFactory sessionFactory =null;
		SqlSession sqlSession =null;
		//mybatis配置文件
		try { 
			String resource = "SqlMapConfig.xml";
			inputStream = Resources.getResourceAsStream(resource);
			//创建会话工厂，传入mybatis的配置文件信息
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//通过工厂得到SqlSession对象
			sqlSession =  sessionFactory.openSession();
			//通过SqlSession操作数据库
			int result = sqlSession.delete("test.removeUser", 10);
			System.out.println(result);
			sqlSession.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	
}
