package com.javaex.vo;

public class CateVo {

	private int cateNo;
	private String id;
	private String cateName;
	private int count;
	private String description;
	private String regDate;

	public CateVo() {
	}

	public CateVo(int cateNo, String id, String cateName, int count, String description, String regDate) {
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.count = count;
		this.description = description;
		this.regDate = regDate;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CateVo [cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName + ", count=" + count
				+ ", description=" + description + ", regDate=" + regDate + "]";
	}

}
