package cn.min.ssm.service;

import java.util.List;

import cn.min.ssm.po.ItemsCustom;
import cn.min.ssm.po.ItemsQueryVo;

/**
*@ClassName:ItemsService
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年5月24日下午7:17:04
*@parameter
*@since
*@return
*/
public interface ItemsService {
	//商品信息列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	//根据id查询商品信息
	public ItemsCustom findItemsById(Integer id)throws Exception;
	//商品修改功能实现
	/**
	 * 
	* @Title: updateItems 
	* @Description: TODO
	* @param id 修改商品的id
	* @param itemsCustom  修改的商品信息
	* @throws Exception  
	* @return void   
	* @throws
	 */
	public void updateItems(Integer id,ItemsCustom itemsCustom)throws Exception;
}
