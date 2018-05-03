package com.min.mybatis.mapper;

import java.util.List;

import com.min.mybatis.po.User;
import com.min.mybatis.po.UserCustom;
import com.min.mybatis.po.UserQueryVo;

/**
 *@ClassName:UserMapper
 *@Description:mapper接口，相当于dao接口
 *@author:minshuaibo
 *@date创建时间：2018年3月24日上午10:27:49
 *@parameter
 *@since
 *@return
 */
public interface UserMapper {
	//用户信息综合查询
	public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
	//用户信息综合查询列表总数
	public int findUserCount(UserQueryVo userQueryVo)throws Exception;
	//根据Id查询用户信息,结果用resultMap返回
	public List<User> findUserByIdResultMap(int id) throws Exception;
	//根据Id查询用户信息
	public User findUserById(int id) throws Exception;
	
	//根据用户名查询用户信息
	public List<User> findUserByName(String userName);
	//添加用户信息
	public void addUser(User user) throws Exception;
	//删除用户信息
	public void  removeUser(int id)throws Exception;
	//更新用户信息
	public void updateUser(User user) throws Exception;
	
}
