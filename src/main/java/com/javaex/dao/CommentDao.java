package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CommentVo> list(int postNo) {
		System.out.println("CommentDao.list");
		return sqlSession.selectList("comment.selectList", postNo);
	}
	
	public int insertSelectKey(CommentVo commentVo) {
		System.out.println("CommentDao.insertSelectKey");
		return sqlSession.insert("comment.insertSelectKey", commentVo);
	}

	public CommentVo selectByNo(int cmtNo) {
		System.out.println("CommentDao.selectByNo");
		return sqlSession.selectOne("comment.selectOne", cmtNo);
	}
	
	public int delete(CommentVo commentVo) {
		System.out.println("CommentDao.delete");
		return sqlSession.delete("comment.delete", commentVo);
	}
}
