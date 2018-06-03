package cn.min.ssm.po;
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
	//商品信息
	private Items items;
	//商品信息包装类
	private ItemsCustom itemsCustom;
}
