package com.min.mybatis.po;
/**
*@ClassName:OrderCustom
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年3月31日下午9:21:35
*@parameter
*@since
*@return
*/
//通过此类映射订单和用户查询的结果，让此类继承包括字段较多的po类
public class OrderCustom extends Orders{
	//添加用户属性
	private String username;
	private String sex;
	private String birthday;
	@Override
	public String toString() {
		return "OrderCustom [username=" + username + ", sex=" + sex + ", birthday=" + birthday + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	
}	
