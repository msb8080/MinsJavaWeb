package cn.min.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.min.ssm.exception.CustomException;
import cn.min.ssm.po.ItemsCustom;
import cn.min.ssm.po.ItemsQueryVo;
import cn.min.ssm.service.ItemsService;
import cn.min.ssm.validation.ValidGroup1;
import cn.min.ssm.validation.ValidGroup2;

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
	
	
	
	/**
	* @Title: itemsView 
	* @Description: 查询商品信息，输出json
	* itemsView/{id}里面的{id}表示将这个位置的参数传到@PathVariable指定名称中
	* @param id
	* @return
	* @throws Exception  
	* @return ItemsCustom   
	* @throws
	 */
	@RequestMapping("itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception {
		
		ItemsCustom items = itemsService.findItemsById(id);
		return items;
		
	}

	//商品分类
	//itemTypes表示最终将方法返回值放到request中的key
	@ModelAttribute("itemtypes")
	public Map<String,String> getItemTypes(){
		Map<String,String> itemTypes = new HashMap<String,String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		return itemTypes;
	}
	
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
	/**
	* @Title: queryItems 
	* @Description: 商品查询
	* @param model
	* @param itemsQueryVo
	* @param request
	* @param response
	* @throws Exception  
	* @return void   
	* @throws
	 */
	@RequestMapping("/queryItems")
	public void queryItems(
			Model model,
			ItemsQueryVo itemsQueryVo,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		//		String id = request.getParameter("id");
		//		System.out.println(id);
		Map<String,String> itemTypes = new HashMap<String,String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		request.setAttribute("itemtypes", itemTypes);
//		model.addAttribute("itemtypes", itemTypes);
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		request.setAttribute("itemsList", itemsList);
		request.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(request, response);
		//		response.sendRedirect("items/itemsList.jsp");

	}

	/**
	* @Title: deleteItems 
	* @Description: 批量删除商品
	* @param items_id
	* @param request
	* @return
	* @throws Exception  
	* @return String   
	* @throws
	 */
	@RequestMapping("/deleteItems")
	public String deleteItems(
			Integer[] items_id,
			HttpServletRequest request)throws Exception{
		//根据商品id删除商品
		if(null!=items_id) {  //如果不为空，则删除相应的数据
			for (Integer id : items_id) {
				itemsService.deleteItems(id);
			}
		}
		//查询删除后的商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);
		request.setAttribute("itemsList", itemsList);
		return "items/itemsList";
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
	
	
	/**
	* @Title: editItems 
	* @Description: 查询商品信息，进入修改商品信息页面
	* @param model
	* @param items_id
	* @return
	* @throws Exception  
	* @return String   
	* @throws
	 */
	@RequestMapping(value="/editItems",method= {RequestMethod.POST,RequestMethod.GET})
	//@RequestParam 通过指定request传入参数名称和形参进行绑定
	//通过required属性指定参数是否必须要传入
	//通过defaultValue可以设置默认值，如果id参数没有传入，将默认值和形参进行绑定
	public String editItems(
			Model model,
			@RequestParam(value="id",required=true,defaultValue="3") Integer items_id) throws Exception{
		//调用service来根据id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
		//判断查询商品是否为空
		if(itemsCustom==null) {
			throw new CustomException("查询商品不存在！");
		}
		//通过形参中的model将model数据传到页面
		//相当于modelAndView.addObject
		model.addAttribute("items", itemsCustom);
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
	/**
	 * @Title: editItemsSubmit 
	 * @Description: 商品信息的修改提交
	 * 在需要校验的pojo前边添加@Validated,在需要校验的pojo后边添加BindingResult bindingResult接受校验出错信息
	 * 注意：@Validted和BindingResult bindResult是配对出现，并且形参顺序是固定的(一前一后)
	 * value={ValidGroup1.class}指定使用ValidgROUP1分组的校验
	 * @ModelAttribute可以指定pojo回显到页面的key值
	 * @param request
	 * @param id
	 * @param itemsCustom
	 * @return
	 * @throws Exception  
	 * @return String   
	 * @throws
	 */
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(
			Model model,
			HttpServletRequest request,
			Integer id,
			@ModelAttribute("items") @Validated(value= {ValidGroup1.class,ValidGroup2.class}) ItemsCustom itemsCustom,
			BindingResult bindingResult,
			MultipartFile items_pic)throws Exception{	
		//获取校验信息
		if (bindingResult.hasErrors()) {
			//获取错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				//输出错误信息
				System.out.println(objectError.getDefaultMessage());
			}
			model.addAttribute("allErrors", allErrors);
			return "items/editItems";
		}
		//上传图片
		if (items_pic!=null) {
			//设置存储图片的路径
			String pic_path = "F:\\javaPictureServer\\upload\\temp\\";
			//获取图片原始名字
			String originalFileName = items_pic.getOriginalFilename();
			//新的图片名字
			String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
			//新图片
			File newFile = new File(pic_path+newFileName);
			//将内存中的数据写到磁盘
			items_pic.transferTo(newFile);
			//将新的图片名称写到itemsCustom中
			itemsCustom.setPic(newFileName);
			
		}
		//调用service更新商品信息，页面需要将商品信息传到此方法
		itemsService.updateItems(id, itemsCustom);	
		//重定向
		//		return "redirect:queryItems.action";
		//页面转发
		//		return "forward:queryItems.action";
		return "success";		
	}
	/**
	 * @Title: editItemsQuery 
	 * @Description: list绑定修改      数据查询
	 * @return
	 * @throws Exception  
	 * @return String   
	 * @throws
	 */
	@RequestMapping("/editItemsQuery")
	public String editItemsQuery(
			HttpServletRequest request,
			ItemsQueryVo itemsQueryVo)throws Exception{
		//调用service查询数据库，查询商品列表	
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		request.setAttribute("itemsList", itemsList);
		return "items/editItemsQuery";
	}
	/**
	 * 
	 * @Title: editItemsSubmit 
	 * @Description: 待修改的数据，使用包装类中的list进行绑定，进行数据更新
	 * @return
	 * @throws Exception  
	 * @return String   
	 * @throws
	 */
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		if(null!=itemsQueryVo.getItemsList()) {
			for(ItemsCustom itemsCustom:itemsQueryVo.getItemsList()) {
				itemsService.updateItems(itemsCustom.getId(), itemsCustom);
			}
		}
		return "forward:editItemsQuery.action";
	}
}
