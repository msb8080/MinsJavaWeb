package cn.min.ssm.mapper;

import cn.min.ssm.po.User;


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
	//根据Id查询用户信息
	public User findUserById(int id) throws Exception;
}
