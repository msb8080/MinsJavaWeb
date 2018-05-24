package cn.min.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.min.ssm.po.ItemsCustom;
import cn.min.ssm.service.ItemsService;

/**
*@ClassName:ItemsController
*@Description:商品的Controller
*@author:minshuaibo
*@date创建时间：2018年5月24日下午8:17:53
*@parameter
*@since
*@return
*/
@Controller
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	
	//商品查询
	@RequestMapping("/queryItems")
	public ModelAndView queryItems() throws Exception{
		
		//调用service查询数据库，查询商品列表	
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//相当于request的setAttribute
		modelAndView.addObject("itemsList", itemsList);
//		modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}
}
