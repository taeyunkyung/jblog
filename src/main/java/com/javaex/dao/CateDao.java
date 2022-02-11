package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CateVo;

@Repository
public class CateDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CateVo> list(String id) {
		System.out.println("CateDao.list");
		return sqlSession.selectList("cate.selectList", id);
	}
	
	public int insertSelectKey(CateVo cateVo) {
		System.out.println("CateDao.insertSelectKey");
		return sqlSession.insert("cate.insertSelectKey", cateVo);
	}
	
	public CateVo selectByNo(int cateNo) {
		System.out.println("CateDao.selectByNo");
		return sqlSession.selectOne("cate.selectByNo", cateNo);
	}
	
	public int delete(CateVo cateVo) {
		System.out.println("CateDao.delete");
		return sqlSession.delete("cate.delete", cateVo);
	}
	
	public CateVo max(String id) {
		return sqlSession.selectOne("cate.max", id);
	}
	
	public List<CateVo> optionList(String id) {
		System.out.println("CateDao.optionList");
		return sqlSession.selectList("cate.optionList", id);
	}
}
