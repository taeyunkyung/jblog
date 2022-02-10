package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo userVo) {
		System.out.println("UserDao.insert");
		return sqlSession.insert("user.insert", userVo);
	}
	
	public List<UserVo> searchId(String keyword) {
		System.out.println("UserDao.searchId");
		return sqlSession.selectList("user.searchId", keyword);
	}
		
	public UserVo login(UserVo userVo) {
		System.out.println("UserDao.login");
		return sqlSession.selectOne("user.login", userVo);
	}

}
