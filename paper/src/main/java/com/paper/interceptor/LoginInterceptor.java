package com.paper.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.paper.pojo.User;
import com.paper.service.UserService;
import com.paper.util.CookieUtils;

public class LoginInterceptor implements HandlerInterceptor{
	
	@Autowired
	private UserService userService;
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		
		
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
			throws Exception {
		
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
				System.out.println("enter intercptor");
				//判断用户是否登录
				String userName = CookieUtils.getCookieValue(request, "PP_USER");
				
				User user = userService.getByUserName(userName);
				//取不到用户信息
				if (null == user) {
					response.sendRedirect("http://localhost:8080/paper/interceptor/index");
					//返回false
					return false;
				}
				//取到用户信息，放行
				//返回值决定handler是否执行。true：执行，false：不执行。
				return true;
	}

}
