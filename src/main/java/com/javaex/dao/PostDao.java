package com.javaex.dao;

import java.util.List;

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
	
	public List<PostVo> list(int cateNo) {
		System.out.println("PostDao.list");
		return sqlSession.selectList("post.selectList", cateNo);
	}
	
	public PostVo read(int postNo) {
		System.out.println("PostDao.read");
		return sqlSession.selectOne("post.selectOne", postNo);
	}
}
