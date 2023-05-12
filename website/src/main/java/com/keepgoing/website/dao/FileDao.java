package com.keepgoing.website.dao;

import com.keepgoing.website.entity.FileDto;

public interface FileDao {
	
	int save(FileDto fileDto);
	FileDto findFile(int id);
	FileDto findFileByName(String fileName);
	String getFileId(String savedName);
	int deleteFile(String savedName);
	void setNoticeId(int id,String fileId);
}
