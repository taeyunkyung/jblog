package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/cmt")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<CommentVo> list(@RequestParam("postNo") int postNo) {
		System.out.println("cmt.list");
		return commentService.getList(postNo);
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public CommentVo add(@ModelAttribute CommentVo commentVo, HttpSession session) {
		System.out.println("cmt.add");
	
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		commentVo.setUserNo(authUser.getUserNo());
		
		return commentService.addReturnVo(commentVo);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(@ModelAttribute CommentVo commentVo) {
		System.out.println("cmt.delete");
		
		return commentService.delete(commentVo);
	}
}
