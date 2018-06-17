package cn.min.ssm.po;

import java.util.List;

/**
*@ClassName:ItemsQueryVo
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年5月24日下午6:50:20
*@parameter
*@since
*@return
*/

public class ItemsQueryVo {
	//商品信息
	private Items items;
	//为了系统的可扩展性，对原始类型生成的po进行扩展
	private ItemsCustom itemsCustom;
	
	//list参数绑定
	private List<ItemsCustom> itemsList;
	
	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}
	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	
}
