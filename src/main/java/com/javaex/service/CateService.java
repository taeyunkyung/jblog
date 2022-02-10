package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CateDao;
import com.javaex.vo.CateVo;

@Service
public class CateService {
	
	@Autowired
	private CateDao cateDao;
	
	public List<CateVo> getList(String id) {
		System.out.println("CateService.getList");
		return cateDao.list(id);
	}
	
	public CateVo addReturnVo(CateVo cateVo) {
		System.out.println("CateService.addReturnVo");
		
		cateDao.insertSelectKey(cateVo);
		int cateNo = cateVo.getCateNo();
		
		return cateDao.selectByNo(cateNo);
	}
	
	public int delete(CateVo cateVo) {
		System.out.println("CateService.delete");
		return cateDao.delete(cateVo);
	}
	
	public List<CateVo> optionList(String id) {
		System.out.println("CateService.optionList");
		return cateDao.optionList(id);
	}
}
