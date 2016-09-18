package com.paper.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paper.pojo.User;
import com.paper.service.UserService;
import com.paper.util.CookieUtils;

@Controller
@RequestMapping("/interceptor")
public class InterceptorTestController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(){
		return "InterceptorTest/index";
	}
	
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request,HttpServletResponse response){
		User user2 = userService.getByUserName(user.getUserName());
		if(user2 == null){
			return "InterceptorTest/index";
		}else if(!user2.getPassword().equals(user.getPassword())){
			return "InterceptorTest/index";
		}
		CookieUtils.setCookie(request, response,"PP_USER", user.getUserName());
		return "InterceptorTest/success";
	}
	
	@RequestMapping("/go")
	public String go(){
		return "InterceptorTest/go";
	}
	
	
	
	
	
}
