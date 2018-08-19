package cn.min.ssm.exception;
/**
*@ClassName:CustomException
*@Description:系统自定义异常类，针对预期的异常，需要在程序中抛出此类的异常
*@author:minshuaibo
*@date创建时间：2018年6月27日上午9:13:41
*@parameter
*@since
*@return
*/
public class CustomException extends Exception{
	//异常信息
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CustomException(String message)
	{
		super(message);
		this.message = message;
	}
	
	

}
