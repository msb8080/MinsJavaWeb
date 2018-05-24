package cn.min.ssm.mapper;

import cn.min.ssm.po.ItemsCustom;
import cn.min.ssm.po.ItemsQueryVo;

import java.util.List;

public interface ItemsMapperCustom {
    //商品列表查询
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}