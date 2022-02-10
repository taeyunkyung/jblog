package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BlogService;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("jblog.joinForm");

		return "user/joinForm";
	}

	@ResponseBody
	@RequestMapping("/checkId")
	public boolean availability(@RequestParam("keyword") String keyword) {
		System.out.println("jblog.checkId");
		
		return userService.checkId(keyword);
	}

	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("jblog.join");

		userService.join(userVo);
		String id = userVo.getId();
		blogService.startBlog(id);

		return "user/joinSuccess";
	}

	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("jblog.loginForm");

		return "user/loginForm";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("jblog.login");
		
		UserVo authUser = userService.login(userVo);
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/";
			
		} else {
			System.out.println("로그인실패");
			return "redirect:/user/loginForm?result=fail";
		}		
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("jblog.logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}

}
