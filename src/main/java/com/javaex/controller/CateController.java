package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CateService;
import com.javaex.vo.CateVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/cate")
public class CateController {

	@Autowired
	private CateService cateService;

	@ResponseBody
	@RequestMapping("/list")
	public List<CateVo> list(@RequestParam("id") String id) {
		System.out.println("cate.list");
		return cateService.getList(id);
	}

	@ResponseBody
	@RequestMapping("/add")
	public CateVo add(@ModelAttribute CateVo cateVo, HttpSession session) {
		System.out.println("cate.add");

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		cateVo.setId(authUser.getId());

		return cateService.addReturnVo(cateVo);
	}

	@ResponseBody
	@RequestMapping("/delete")
	public String delete(@RequestParam("cateNo") int cateNo, HttpSession session) {
		System.out.println("cate.delete");

		CateVo cateVo = new CateVo();
		cateVo.setCateNo(cateNo);
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		cateVo.setId(authUser.getId());
		int result = cateService.delete(cateVo);

		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}

	@ResponseBody
	@RequestMapping("/optionList")
	public List<CateVo> option(@RequestParam("id") String id) {
		System.out.println("post.option");
		return cateService.optionList(id);
	}
}
