package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		System.out.println("UserService.join");	
		return userDao.insert(userVo);	
	}
	
	public boolean checkId(String keyword) {
		System.out.println("UserService.checkId");
		List<UserVo> list = userDao.searchId(keyword);
		
		boolean result = false;
		if(list.isEmpty() == true) {
			result = true;
		}
		return result;
	}
		
	public UserVo login(UserVo userVo) {
		System.out.println("UserService.login");
		return userDao.login(userVo);
	}

}
