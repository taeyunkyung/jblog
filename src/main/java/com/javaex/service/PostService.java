package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	
	public int write(PostVo postVo) {
		System.out.println("PostService.write");
		return postDao.insert(postVo);
	}
	
	public List<PostVo> getList(int cateNo) {
		System.out.println("PostService.getList");
		return postDao.list(cateNo);
	}
	
	public PostVo read(int postNo) {
		System.out.println("PostService.view");
		return postDao.read(postNo);
	}
}
