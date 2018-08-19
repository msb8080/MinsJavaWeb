package cn.min.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
*@ClassName:LoginInterceptor
*@Description:登录拦截器
*@author:minshuaibo
*@date创建时间：2018年8月11日下午2:52:17
*@parameter
*@since
*@return
*/
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	//进入Handler之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//判断用户请求url是否是公共地址(实际使用时将公开地址配置到配置文件中)
		//这里公开地址是登录提交的地址
		String url = request.getRequestURI();
		if (url.indexOf("login.action")>=0) {
			//如果为公共地址，则放行
			return true;
		}
		//获得session
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		//如果用户存在则放行
		if (null!=username) {
			return true;
		}
		
		//执行到这里，说明用户没有登录，则转到登录页面进行登录
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}

}
