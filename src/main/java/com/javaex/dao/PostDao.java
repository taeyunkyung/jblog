package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(PostVo postVo) {
		System.out.println("PostDao.insert");
		return sqlSession.insert("post.insert", postVo);
	}
	
	// 페이징 //
	public List<PostVo> rlist(int cateNo, int startnum, int endnum) {
		System.out.println("PostDao.rlist");
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("cateNo", cateNo);
		map.put("startnum", startnum);
		map.put("endnum", endnum);
		System.out.println(map);
		return sqlSession.selectList("post.rlist", map);
	}
	
	public int selectTotal(int cateNo) {
		System.out.println("PostDao.selectTotal");
		return sqlSession.selectOne("post.totalCnt", cateNo);
	}
	// 페이징 //
	
	public List<PostVo> list(int cateNo) {
		System.out.println("PostDao.list");
		return sqlSession.selectList("post.selectList", cateNo);
	}
	
	public PostVo max(String id) {
		return sqlSession.selectOne("post.max", id);
	}
	
	public PostVo read(int postNo) {
		System.out.println("PostDao.read");
		return sqlSession.selectOne("post.selectOne", postNo);
	}
}
