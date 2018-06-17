package cn.min.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.min.ssm.mapper.ItemsMapper;
import cn.min.ssm.mapper.ItemsMapperCustom;
import cn.min.ssm.po.Items;
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
	@Autowired
	private ItemsMapper itemsMapper;
	
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		// 通过itemsMapperCustom查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		// 添加业务校验，通常在service接口对关键参数进行校验
		//检验id是否为空，如果为空抛出异常
		if(null==id) {
			System.out.println("id为空，更新失败");
			return;
		}
		//更新商品信息使用updateByPrimaryKeyWithBLOBs根据id更新items表中的所有字段，包括大文本类型
		//updateByPrimaryKeyWithBLOBs必须传入id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);	
	}	
	

	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		//中间对商品信息进行业务处理
		//...
		//返回ItemsCustom
		ItemsCustom itemsCustom = new ItemsCustom();
		//将items中的内容拷贝到itemsCustom中
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}

	@Override
	public boolean deleteItems(Integer id) throws Exception {
		if(null==id) {
			System.out.println("id为空，删除失败");
			return false;
		}
		
		if (itemsMapper.deleteByPrimaryKey(id)<0) {
			return false;
		}
		return true;
	}

}
