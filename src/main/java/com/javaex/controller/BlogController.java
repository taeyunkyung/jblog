package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CateService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CateService cateService;
	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String main(@PathVariable("id") String id,
			@RequestParam(value = "cateNo", required = false, defaultValue = "-1") int cateNo,
			@RequestParam(value = "postNo", required = false, defaultValue = "-1") int postNo, 
			@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage, Model model) {
		System.out.println("jblog/id.main");

		if (cateNo < 0) {
			cateNo = cateService.max(id).getCateNo();
		}
		if (postNo < 0) {
			postNo = postService.max(id).getPostNo();
		}

		Map<String, Object> map = blogService.map(id, cateNo, crtPage);
		PostVo readVo = postService.read(postNo);

		model.addAttribute("map", map);
		model.addAttribute("readVo", readVo);

		return "blog/blog-main";
	}

	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String basic(@PathVariable("id") String id, Model model) {
		System.out.println("jblog/id.basic");

		BlogVo blogVo = blogService.basic(id);
		model.addAttribute("blogVo", blogVo);

		return "blog/admin/blog-admin-basic";
	}

	@RequestMapping(value = "/{id}/admin/basic/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@PathVariable("id") String id, @RequestParam("blogTitle") String blogTitle, MultipartFile file,
			Model model) {
		System.out.println("jblog/id.update");

		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		blogVo.setBlogTitle(blogTitle);

		blogService.updateBasic(file, blogVo);
		BlogVo updateVo = blogService.basic(id);
		model.addAttribute("blogVo", updateVo);

		return "redirect:/" + id;
	}

	@RequestMapping("/{id}/admin/category")
	public String category(@PathVariable("id") String id, Model model) {
		System.out.println("jblog/id.category");

		BlogVo blogVo = blogService.basic(id);
		model.addAttribute("blogVo", blogVo);

		return "blog/admin/blog-admin-cate";
	}

	@RequestMapping("/{id}/admin/writeForm")
	public String writeForm(@PathVariable("id") String id, Model model) {
		System.out.println("jblog/id.writeForm");

		BlogVo blogVo = blogService.basic(id);
		model.addAttribute("blogVo", blogVo);

		return "blog/admin/blog-admin-write";
	}

	@RequestMapping("/{id}/admin/write")
	public String write(@PathVariable("id") String id, @ModelAttribute PostVo postVo) {
		System.out.println("jblog/id.write");
		System.out.println(postVo);
		postService.write(postVo);

		return "redirect:/" + id;
	}
}
