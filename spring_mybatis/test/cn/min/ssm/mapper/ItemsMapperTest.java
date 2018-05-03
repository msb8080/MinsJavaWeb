package cn.min.ssm.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.min.ssm.po.Items;
import cn.min.ssm.po.ItemsExample;

/**
*@ClassName:ItemsMapperTest
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年5月3日下午7:50:50
*@parameter
*@since
*@return
*/
class ItemsMapperTest {
	//spring bean
	private ApplicationContext applicationContext;
	private ItemsMapper itemsMapper;
	
	@BeforeEach
	void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		itemsMapper = (ItemsMapper) applicationContext.getBean("itemsMapper");
	}
	//根据主键删除item
	@Test
	void testDeleteByPrimaryKey() {
		
	}
	
	//添加item对象
	@Test
	void testInsert() {
		Items items = new Items();
		items.setName("火车");
		items.setPrice(20f);
		itemsMapper.insert(items);
	}
	//自定义条件查询，用的最多
	@Test
	void testSelectByExample() {
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameEqualTo("笔记本");
		List<Items> list = itemsMapper.selectByExample(itemsExample);
		System.out.println(list);
	}
	//根据主键来查询商品信息
	@Test
	void testSelectByPrimaryKey() {
	   Items items = itemsMapper.selectByPrimaryKey(1);
	   System.out.println(items);
	}

	//如果传入字段不为空时才更新，在批量更新中使用此方法，不需要查询在更新
	@Test
	void testUpdateByPrimaryKeySelective() {
		Items items = new Items();
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameEqualTo("火车");
		items.setName("收音机");
		itemsMapper.updateByExampleSelective(items, itemsExample);
	}

	//根据主键更新
	//对所有字段进行更新，需要先查询出来再更新
	@Test
	void testUpdateByPrimaryKey() {
		Items items = itemsMapper.selectByPrimaryKey(1);
		items.setName("收音机");
		itemsMapper.updateByPrimaryKey(items);
	}

}
