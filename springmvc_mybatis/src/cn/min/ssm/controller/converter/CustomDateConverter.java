package cn.min.ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
*@ClassName:CustomDateConverter
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年6月2日下午4:47:10
*@parameter
*@since
*@return
*/
public class CustomDateConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formater.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//参数绑定失败则返回空
		return null;
	}

}
