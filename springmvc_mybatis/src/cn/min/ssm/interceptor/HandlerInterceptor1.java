package cn.min.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
*@ClassName:HandlerInterceptor1
*@Description:处理器拦截器
*@author:minshuaibo
*@date创建时间：2018年8月9日下午5:57:50
*@parameter
*@since
*@return
*/
public class HandlerInterceptor1 implements HandlerInterceptor{
	//在Handler执行完成之后执行
	//应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
			System.out.println("afterCompletion1 run!");

		
	}
	//在进入Handler之后，返回ModelAndView之前执行
	//应用场景从modelandview出发：将公用的模型数据在这里传到视图(比如菜单导航),也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle1 run!");
	}
	//在进入Handler之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证不通过表示当前用户没有登录成功，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		//return false 表示拦截，不向下执行
		//return true 表示放行
		System.out.println("preHandle1 run!");
		return true;
	}

}
