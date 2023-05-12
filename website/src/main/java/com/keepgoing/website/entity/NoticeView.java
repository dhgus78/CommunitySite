package com.keepgoing.website.entity;

import java.util.Date;

public class NoticeView extends Notice{
	
	private String memberName;
	
	public NoticeView() {
		// TODO Auto-generated constructor stub
	}

	public NoticeView(int id, String title, String content, Date regdate, int hit, boolean pub, String files, int likes, int memberId, String memberName) {
		super(id, title, content, regdate, hit, pub, files, likes, memberId);
		this.memberName = memberName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "NoticeView [getMemberName()=" + getMemberName() + ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getContent()=" + getContent() + ", getRegdate()=" + getRegdate() + ", getHit()=" + getHit()
				+ ", isPub()=" + isPub() + ", getFiles()=" + getFiles() + ", getLikes()=" + getLikes()
				+ ", getMemberId()=" + getMemberId() + "]";
	}

	

	
	
	
	
	
}
