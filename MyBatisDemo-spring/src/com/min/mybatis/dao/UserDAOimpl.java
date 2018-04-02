package com.min.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.mybatis.po.User;

/**
 *@ClassName:UserDAOimpl
 *@Description:TODO
 *@author:minshuaibo
 *@date创建时间：2018年3月24日上午9:52:25
 *@parameter
 *@since
 *@return
 */
public class UserDAOimpl implements UserDAO{
	//需要向dao实现类注入SqlSessionFactory
	//这里通过构造方法注入
	private SqlSessionFactory sqlSessionFactory;
	public UserDAOimpl(SqlSessionFactory sqlSessionFactorys) {
		this.sqlSessionFactory = sqlSessionFactorys;
	}
	@Override
	public User findUserById(int id) throws Exception {
		//创建sql会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//SqlSession执行sql语句
		User user = sqlSession.selectOne("test.findUserById",id);
		
		sqlSession.close();
		return user;
	}

	@Override
	public void addUser(User user) throws Exception {
		//创建sql会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//SqlSession执行sql语句
		sqlSession.insert("test.addUser",user);
		//关闭资源
		sqlSession.close();
	}

	@Override
	public void removeUser(int id) throws Exception {
		//创建sql会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//SqlSession执行sql语句
		sqlSession.delete("test.removeUser",id);
		//关闭资源
		sqlSession.close();		
	}

}
