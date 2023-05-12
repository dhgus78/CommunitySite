package com.keepgoing.website.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepgoing.website.dao.FileDao;
import com.keepgoing.website.entity.FileDto;

@Repository
public class MybatisFileDao implements FileDao{
	
	private FileDao fileDao;
	
	@Autowired
	MybatisFileDao(SqlSession sqlSession){
		this.fileDao = sqlSession.getMapper(FileDao.class);
		
	}

	@Override
	public int save(FileDto fileDto) {
		fileDao.save(fileDto);
		int fileId = fileDto.getId();
		return fileId; 
		
		
		
	}

	@Override
	public FileDto findFile(int id) {
		return fileDao.findFile(id);
	}

	@Override
	public FileDto findFileByName(String fileName) {
		return fileDao.findFileByName(fileName);
	}

	@Override
	public String getFileId(String savedName) {
		return fileDao.getFileId(savedName);
	}

	@Override
	public int deleteFile(String savedName) {	
		return fileDao.deleteFile(savedName);
	}

	@Override
	public void setNoticeId(int id, String fileId) {
		fileDao.setNoticeId(id, fileId);
		
	}


}
