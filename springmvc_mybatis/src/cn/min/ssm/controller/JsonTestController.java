package cn.min.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.min.ssm.po.ItemsCustom;

/**
*@ClassName:JsonTestController
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年8月8日下午12:25:17
*@parameter
*@since
*@return
*/
@Controller
public class JsonTestController {
	//请求json串(商品信息)，输出json(商品信息)
	//@RequestBody 将json串转换成java ItemsCustom对象
	//@ResponseBody 将java对象转换成 json字符串
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) {
		
		return itemsCustom;
	}
	
	//请求key/value数据，输出json字符串
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom) {
		
		return itemsCustom;
	}
	
}
