package com.min.mybatis.mapper;
/**
*@ClassName:OrdersCustomMapper
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年3月31日下午9:26:42
*@parameter
*@since
*@return
*/

import java.util.List;

import com.min.mybatis.po.OrderCustom;
//查询订单映射类
import com.min.mybatis.po.Orders;
import com.min.mybatis.po.User;
public interface OrdersCustomMapper {
	//查询订单，关联查询创建订单的用户信息
	public List<OrderCustom> findOrdersUser();
	//使用resultMap查询订单，关联查询创建订单的用户信息
	public List<Orders> findOrdersUserResultMap();
	//查询订单关联查询订单明细信息，使用resultMap
	public List<Orders> findOrdersAndOrderDetailResultMap();
	//查询用户和用户购买的商品信息
	public List<User> findUserAndItemsResultMap();
	//查询订单信息，延迟加载关联查询的用户信息
	public List<Orders> findOrdersUserLazyLoading();
} 
