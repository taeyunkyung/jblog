package com.javaex.vo;

public class BlogVo {

	private String id;
	private String userName;
	private String blogTitle;
	private String logoFile;
	private String joinDate;

	public BlogVo() {
	}

	public BlogVo(String id, String userName, String blogTitle, String logoFile, String joinDate) {
		this.id = id;
		this.userName = userName;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
		this.joinDate = joinDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", userName=" + userName + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile
				+ ", joinDate=" + joinDate + "]";
	}

}
