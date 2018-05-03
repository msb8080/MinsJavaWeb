 package cn.min.ssm.dao;
/**
*@ClassName:UserDAO
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年3月24日上午9:48:29
*@parameter
*@since
*@return
*/

import cn.min.ssm.po.User;

public interface UserDAO {
	//根据Id查询用户信息
	public User findUserById(int id) throws Exception;

}
