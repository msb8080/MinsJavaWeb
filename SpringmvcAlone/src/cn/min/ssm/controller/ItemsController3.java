package cn.min.ssm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.min.ssm.po.Items;
/**
 *@ClassName:ItemsController2
 *@Description:TODO
 *@author:minshuaibo
 *@date创建时间：2018年5月22日下午8:22:05
 *@parameter
 *@since
 *@return
 */
//尽量不适用继承，或者接口，扩展性更强

@Controller
public class ItemsController3{
	//商品查询列表
	//@RequestMapping实现对queryItems方法和url进行映射，一个方法对应一个url
	//一般建议将url和方法写成一样的 
	@RequestMapping("/queryItems")
	public ModelAndView queryItems() throws Exception{
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
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}




	/*@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		request.setAttribute("itemsList", itemsList);
		request.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(request, response);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		respnse.getWriter().write("");
	}*/
}
