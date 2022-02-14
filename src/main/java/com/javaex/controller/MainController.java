package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class MainController {

	@Autowired
	private BlogService blogService;

	@RequestMapping("/")
	public String main() {
		System.out.println("jblog.main");

		return "main/index";
	}

	@RequestMapping("/search")
	public String search(@RequestParam("kwdOpt") String kwdOpt, 
					   @RequestParam("keyword") String keyword, Model model) {
		System.out.println("jblog.search");
		
		List<BlogVo> searchList = null;
		if(kwdOpt.equals("optTitle")) {
			searchList = blogService.searchTitle(keyword);
		} else if(kwdOpt.equals("optName")) {
			searchList = blogService.searchName(keyword);
		}
		System.out.println(searchList);
		model.addAttribute("searchList", searchList);
		return "main/index";
	}
}
