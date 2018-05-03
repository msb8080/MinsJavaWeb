package com.log4j;

import org.apache.log4j.Logger;


/**
 * 
* @ClassName: Test 
* @Description: TODO
* @author min
* @date 2018年3月16日 下午9:32:48 
*
 */
public class Test {
	private static Logger logger = Logger.getLogger(Test.class);  
	/**
	 * 
	* @Title: main 
	* @Description: TODO
	* @param args  
	* @return void   
	* @throws
	 */
    public static void main(String[] args) {  
        // System.out.println("This is println message.");  

        // 记录debug级别的信息  
        logger.debug("This is debug message.");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
        logger.error("This is error message.");  
    }  

}