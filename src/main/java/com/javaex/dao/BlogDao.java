package com.javaex.dao;

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
}
