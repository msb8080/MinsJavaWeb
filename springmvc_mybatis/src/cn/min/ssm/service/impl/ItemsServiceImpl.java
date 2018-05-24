package cn.min.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.min.ssm.mapper.ItemsMapperCustom;
import cn.min.ssm.po.ItemsCustom;
import cn.min.ssm.po.ItemsQueryVo;
import cn.min.ssm.service.ItemsService;

/**
*@ClassName:ItemsServiceImpl
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年5月24日下午7:20:07
*@parameter
*@since
*@return
*/
public class ItemsServiceImpl implements ItemsService{
	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		// 通过itemsMapperCustom查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

}
