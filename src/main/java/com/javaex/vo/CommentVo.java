package com.javaex.vo;

public class CommentVo {

	private int cmtNo;
	private int postNo;
	private int userNo;
	private String userName;
	private String cmtContent;
	private String regDate;

	public CommentVo() {
	}

	public CommentVo(int cmtNo, int postNo, int userNo, String userName, String cmtContent, String regDate) {
		this.cmtNo = cmtNo;
		this.postNo = postNo;
		this.userNo = userNo;
		this.userName = userName;
		this.cmtContent = cmtContent;
		this.regDate = regDate;
	}

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCmtContent() {
		return cmtContent;
	}

	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CommentVo [cmtNo=" + cmtNo + ", postNo=" + postNo + ", userNo=" + userNo + ", userName=" + userName
				+ ", cmtContent=" + cmtContent + ", regDate=" + regDate + "]";
	}

}
