package com.paper.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paper.pojo.User;


@Controller
@RequestMapping("/shiro")
public class ShiroTestController {

	@RequestMapping("/login")
	public String login(User user){
		System.out.println("enter controller");
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try{
			subject.login(token);
			return "/shiro/success";
		}catch(Exception e){
			e.printStackTrace();
			return "/shiro/index";
		}
	}
	
	@RequestMapping("/index")
	public String index(){
		return "/shiro/index";
	}

}
