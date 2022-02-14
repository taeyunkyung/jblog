package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(BlogVo blogVo) {
		System.out.println("BlogDao.insert");
		return sqlSession.insert("blog.insert", blogVo);
	}
		
	public BlogVo selectOne(String id) {
		System.out.println("BlogDao.selectOne");
		return sqlSession.selectOne("blog.selectOne", id);
	}
	
	public int update(BlogVo blogVo) {
		System.out.println("BlogDao.update");
		return sqlSession.update("blog.update", blogVo);
	}
	
	// 검색 //
	public List<BlogVo> searchTitle(String keyword) {
		System.out.println("BlogDao.searchTitle");
		return sqlSession.selectList("blog.searchTitle", keyword);
	}
	
	public List<BlogVo> searchName(String keyword) {
		System.out.println("BlogDao.searchName");
		return sqlSession.selectList("blog.searchName", keyword);
	}
	// 검색 //
}
