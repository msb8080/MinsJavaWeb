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

}
