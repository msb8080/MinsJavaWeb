package cn.min.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cn.min.ssm.po.Items;

/**
*@ClassName:ItemsContorller
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年5月6日上午11:14:51
*@parameter
*@since
*@return
*/
public class ItemsController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Items> itemsList = new ArrayList<Items>();
		Items items_1 = new Items();
		items_1.setName("可乐");
		items_1.setPrice(30f);
		items_1.setDetail("可口可乐 不一样的感觉");
		Items items_2 = new Items();
		
		items_2.setName("联想笔记本");
		items_2.setPrice(6000f);
		items_2.setDetail("ThinkPad T430联想笔记本电脑");
		itemsList.add(items_1);
		itemsList.add(items_2);
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//相当于request的setAttribute
		modelAndView.addObject("itemsList", itemsList);
		//
		modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
		return modelAndView;
	}

}
