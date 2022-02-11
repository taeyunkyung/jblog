package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, Object> rlist(int cateNo, int crtPage) {

		int listCnt = 5;
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);

		int startnum = (crtPage - 1) * listCnt + 1;
		int endnum = startnum + listCnt - 1;
		List<PostVo> rlist = postDao.rlist(cateNo, startnum, endnum);

		int totalCnt = postDao.selectTotal(cateNo);
		int pgBtnCnt = 5;

		int epgBtnNo = (int) (Math.ceil(crtPage / (double) pgBtnCnt)) * pgBtnCnt;
		int spgBtnNo = epgBtnNo = pgBtnCnt + 1;

		boolean next = false;
		if (epgBtnNo * listCnt < totalCnt) {
			next = true;
		} else {
			epgBtnNo = (int) (Math.ceil(totalCnt / (double) listCnt));
		}

		boolean prev = false;
		if (spgBtnNo != 1) {
			prev = true;
		}

		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("rlist", rlist);
		rmap.put("prev", prev); rmap.put("next", next);
		rmap.put("spgBtnNo", spgBtnNo); rmap.put("epgBtnNo", epgBtnNo); 
		
		return rmap;
	}

	public List<PostVo> getList(int cateNo) {
		System.out.println("PostService.getList");
		return postDao.list(cateNo);
	}

	public PostVo max(String id) {
		return postDao.max(id);
	}

	public PostVo read(int postNo) {
		System.out.println("PostService.read");
		return postDao.read(postNo);
	}
}
