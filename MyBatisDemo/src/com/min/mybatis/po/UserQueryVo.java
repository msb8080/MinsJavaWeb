package com.min.mybatis.po;

import java.util.List;

/**
 *@ClassName:UserQueryVo
 *@Description:用于用户综合查询的包装pojo类
 *@author:minshuaibo
 *@date创建时间：2018年3月25日下午1:53:56
 *@parameter
 *@since
 *@return
 */
public class UserQueryVo {
	//在这里包装所需要的查询条件

	//传入多个id
	private List<Integer> ids;
	//用户查询条件
	public UserCustom userCustom;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	//还可以包装其他的查询条件，订单，商品
}
