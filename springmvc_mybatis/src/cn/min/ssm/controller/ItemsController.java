package cn.min.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.min.ssm.po.ItemsCustom;
import cn.min.ssm.po.ItemsQueryVo;
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
//为了对url进行分类管理，可以在这里定义根路径，最终访问url是根据根路径+子路径
@RequestMapping("/items")
public class ItemsController {
	@Autowired
	private ItemsService itemsService;
	
	//商品查询
//	@RequestMapping("/queryItems")
//	public ModelAndView queryItems(HttpServletRequest request) throws Exception{
//		String id= request.getParameter("id");
//		System.out.println(id);
//		//调用service查询数据库，查询商品列表	
//		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
//		//返回ModelAndView
//		ModelAndView modelAndView = new ModelAndView();
//		//相当于request的setAttribute
//		modelAndView.addObject("itemsList", itemsList);
////		modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
//		modelAndView.setViewName("items/itemsList");
//		return modelAndView;
//	}
	//返回void
	@RequestMapping("/queryItems")
	public void queryItems(ItemsQueryVo itemsQueryVo,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		String id = request.getParameter("id");
//		System.out.println(id);
		
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		request.setAttribute("itemsList", itemsList);
		request.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(request, response);
//		response.sendRedirect("items/itemsList.jsp");
		
	}
	
//	1.商品信息修改页面显示
//	@RequestMapping("/editItems")
//	@RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
//	public ModelAndView editItems() throws Exception{
//		//调用service来根据id查询商品信息
//		ItemsCustom itemsCustom = itemsService.findItemsById(1);
//		
//		ModelAndView modelAndView = new ModelAndView();
//		//设置数据
//		modelAndView.addObject("itemsCustom", itemsCustom);
//		//商品修改页面
//		modelAndView.setViewName("items/editItems");
//		return modelAndView;
//	}
	@RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
	//@RequestParam 通过指定request传入参数名称和形参进行绑定
	//通过required属性指定参数是否必须要传入
	//通过defaultValue可以设置默认值，如果id参数没有传入，将默认值和形参进行绑定
	public String editItems(Model model,@RequestParam(value="id",required=true,defaultValue="3")
							Integer items_id) throws Exception{
		//调用service来根据id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
		//通过形参中的model将model数据传到页面
		//相当于modelAndView.addObject
		model.addAttribute("itemsCustom", itemsCustom);
		return "items/editItems";
	}
	
	
	
//	2.商品信息修改提交
//	@RequestMapping("/editItemsSubmit")
//	public ModelAndView editItemsSubmit()throws Exception{
//		//调用service更新商品信息，页面需要将商品信息传到此方法中
//		//。。。
//		
//		ModelAndView modelAndView = new ModelAndView();
//		//设置数据
//	
//		//商品修改成功页面
//		modelAndView.setViewName("success");
//		return modelAndView;
//	}
	
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom)throws Exception{
		
		itemsService.updateItems(id, itemsCustom);	
		//重定向
//		return "redirect:queryItems.action";
		//页面转发
		return "forward:queryItems.action";
//		return "success";		
	}
}
