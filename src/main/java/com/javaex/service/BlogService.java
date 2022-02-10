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

	public Map<String, Object> map(String id, int cateNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		BlogVo blogVo = blogDao.selectOne(id); 
		List<CateVo> cateList = cateDao.optionList(id); 

		map.put("blogVo", blogVo);
		map.put("cateList", cateList);

		List<PostVo> postList = postDao.list(cateNo);
		map.put("postList", postList);

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
