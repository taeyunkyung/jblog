package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	public List<CommentVo> getList(int postNo) {
		System.out.println("CommentService.getList");
		return commentDao.list(postNo);
	}

	public CommentVo addReturnVo(CommentVo commentVo) {
		System.out.println("CommentService.addReturnVo");

		commentDao.insertSelectKey(commentVo);
		int cmtNo = commentVo.getCmtNo();

		return commentDao.selectByNo(cmtNo);
	}

	public String delete(CommentVo commentVo) {
		System.out.println("CommentService.delete");
		int count = commentDao.delete(commentVo);
		if (count > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}
