package cn.min.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
*@ClassName:TestController_min
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年6月26日下午9:01:24
*@parameter
*@since
*@return
*/
@Controller
@RequestMapping("/test")
public class TestController_min {
	private static List<Account> accounts = new ArrayList<Account>();	
    {
        accounts.add(new Account());
        accounts.add(new Account());
        
        Account ac1 = accounts.get(0);
        Account ac2 = accounts.get(1);
        ac1.setUserName("Robin");
        ac1.setPassWord("123123");
        
        ac2.setUserName("Lucy");
        ac2.setPassWord("123456");
    }

    @RequestMapping(method=RequestMethod.GET,value="test1")
    public String index() {
        System.out.println("index");
        return "test";
    }
    
    @ModelAttribute("accounts")
    public List<Account> getAccounts() {
        System.out.println("getAccounts");
        return accounts;
    }

}
