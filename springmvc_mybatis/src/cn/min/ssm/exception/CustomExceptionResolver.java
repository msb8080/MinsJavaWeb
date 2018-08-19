package cn.min.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
*@ClassName:CustomExceptionResolver
*@Description:系统自定义的异常处理器
*@author:minshuaibo
*@date创建时间：2018年6月27日上午9:26:21
*@parameter
*@since
*@return
*/
public class CustomExceptionResolver implements HandlerExceptionResolver{
	/**
	 * 
	*@Title: resolveException
	*@Description: 
	* @param request
	* @param response
	* @param handler
	* @param ex 系统抛出的异常
	* @return 
	* @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//解析出异常类型
		//如果该异常类型是系统自定义的异常，直接抛出异常信息，在错误页面展示
//		String message = null;
//		if (ex instanceof CustomException) {
//			message = ((CustomException)ex).getMessage();
//		}else {
//			message = "未知错误";
//		}
		//上面代码变为
		CustomException customException = null;
		if (ex instanceof CustomException) {
			customException = (CustomException) ex;
		}else {
			customException = new CustomException("未知错误");
		}
		//错误信息
		String message = customException.getMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		//将错误信息传到页面
		modelAndView.addObject("message", message);
		//指向错误页面
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

}
