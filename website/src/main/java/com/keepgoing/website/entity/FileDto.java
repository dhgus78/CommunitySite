package com.keepgoing.website.entity;

public class FileDto {
	private int id;
	private String orgName;
	private String savedName;
	private String savedPath;
	private String contentType;
	private int noticeId;
	
	public FileDto(){}

	public FileDto(int id, String orgName, String savedName, String savedPath, String contentType, int noticeId) {
		super();
		this.id = id;
		this.orgName = orgName;
		this.savedName = savedName;
		this.savedPath = savedPath;
		this.contentType = contentType;
		this.noticeId = noticeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSavedName() {
		return savedName;
	}

	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}

	public String getSavedPath() {
		return savedPath;
	}

	public void setSavedPath(String savedPath) {
		this.savedPath = savedPath;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	@Override
	public String toString() {
		return "FileDto [id=" + id + ", orgName=" + orgName + ", savedName=" + savedName + ", savedPath=" + savedPath
				+ ", contentType=" + contentType + ", noticeId=" + noticeId + "]";
	}

	

	
	
	

	
	
	
}
