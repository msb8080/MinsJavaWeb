package cn.min.ssm.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*@ClassName:LoginController
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年8月11日下午2:38:33
*@parameter
*@since
*@return
*/
@Controller
public class LoginController {
	//登录
	@RequestMapping("/login")
	public String login(HttpSession session,String username,String password) throws Exception{
		//调用service进行用户身份验证
		//...
		
		session.setAttribute("username", username);
		
		//跳转到登录页面
		return "redirect:/items/queryItems.action";
	}
	
	//退出
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception{
		//消除session
		session.invalidate();
		
		//重定向到商品列表页面
		return "redirect:/items/queryItems.action";
	}
}
