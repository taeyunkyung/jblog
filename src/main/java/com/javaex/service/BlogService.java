package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CateDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CateDao cateDao;
	@Autowired
	private PostDao postDao;

	public int startBlog(String id) {
		System.out.println("BlogService.startBlog");
		BlogVo blogVo = new BlogVo(id, "", "", "");

		return blogDao.insert(blogVo);
	}

	public BlogVo basic(String id) {
		System.out.println("BlogService.basic");
		return blogDao.selectOne(id);
	}

	public Map<String, Object> map(String id, int cateNo, int crtPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		BlogVo blogVo = blogDao.selectOne(id); 
		List<CateVo> cateList = cateDao.optionList(id); 

		map.put("blogVo", blogVo);
		map.put("cateList", cateList);

		// 포스트 페이징 //
		int listCnt = 5;
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);

		int startnum = (crtPage - 1) * listCnt + 1;
		int endnum = startnum + listCnt - 1;
		List<PostVo> postList = postDao.rlist(cateNo, startnum, endnum);

		int totalCnt = postDao.selectTotal(cateNo);
		int pgBtnCnt = 5;

		int epgBtnNo = (int) (Math.ceil(crtPage / (double) pgBtnCnt)) * pgBtnCnt;
		int spgBtnNo = epgBtnNo - pgBtnCnt + 1;

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

		map.put("postList", postList);
		map.put("prev", prev); map.put("next", next);
		map.put("spgBtnNo", spgBtnNo); map.put("epgBtnNo", epgBtnNo);		
		// 포스트 페이징 //	
		System.out.println(map);
		return map;
	}

	public int updateBasic(MultipartFile file, BlogVo blogVo) {
		System.out.println("BlogService.updateBasic");

		String saveDir = "C:\\javastudy\\upload";
		String orgName = file.getOriginalFilename();
		String exName = orgName.substring(orgName.lastIndexOf("."));
		String logoFile = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		String filePath = saveDir + "\\" + logoFile;

		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		blogVo.setLogoFile(logoFile);
		System.out.println(logoFile);
		System.out.println(blogVo);

		return blogDao.update(blogVo);
	}
}
