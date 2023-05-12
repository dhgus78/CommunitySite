package com.keepgoing.website.entity;

import java.util.Date;

public class Notice {
	private int id;
	private String title;
	private String content;
	private Date regdate;
	private int hit;
	private boolean pub;
	private String files;
	private int likes;
	private int memberId;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(int id, String title, String content, Date regdate, int hit, boolean pub, String files, int likes, int memberId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.pub = pub;
		this.files = files;
		this.likes = likes;
		this.memberId = memberId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}
	
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", regdate=" + regdate + ", hit="
				+ hit + ", pub=" + pub + ", files=" + files + ", likes=" + likes + ", memberId=" + memberId + "]";
	}

	

	
	
	
	
}
