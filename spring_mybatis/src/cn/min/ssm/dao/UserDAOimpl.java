package cn.min.ssm.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.min.ssm.po.User;

/**
 *@ClassName:UserDAOimpl
 *@Description:TODO
 *@author:minshuaibo
 *@date创建时间：2018年3月24日上午9:52:25
 *@parameter
 *@since
 *@return
 */
public class UserDAOimpl extends SqlSessionDaoSupport implements UserDAO{
	//需要向dao实现类注入SqlSessionFactory
	//这里通过构造方法注入
	
	@Override
	public User findUserById(int id) throws Exception {
		//创建sql会话
		SqlSession sqlSession = this.getSqlSession();
		//SqlSession执行sql语句
		User user = sqlSession.selectOne("test.findUserById",id);
		return user;
	}
}
