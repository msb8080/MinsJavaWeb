 package com.min.mybatis.dao;
/**
*@ClassName:UserDAO
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年3月24日上午9:48:29
*@parameter
*@since
*@return
*/

import com.min.mybatis.po.User;

public interface UserDAO {
	//根据Id查询用户信息
	public User findUserById(int id) throws Exception;
	//添加用户信息
	public void addUser(User user) throws Exception;
	//删除用户信息
	public void  removeUser(int id)throws Exception;

}
