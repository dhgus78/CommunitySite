package com.keepgoing.website.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.keepgoing.website.dao.FileDao;
import com.keepgoing.website.entity.FileDto;

@Service
public class FileService {
	
	@Value("part4.upload.path")
	private String fileDir;
	
	@Autowired
	private FileDao fileDao; 
	
	
	public String saveFile(MultipartFile files) throws IOException {
		if(files.isEmpty()) {
			return null;
		}
		// 원래 파일 이름 추출
        String origName = files.getOriginalFilename();

        // 파일 이름으로 쓸 uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출(ex : .png)
        String extension = origName.substring(origName.lastIndexOf("."));

        // uuid와 확장자 결합
        String savedName = uuid + extension;

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + savedName;

        // 파일 엔티티 생성
        FileDto file = new FileDto();
        file.setOrgName(origName);
        file.setSavedName(savedName);
        file.setSavedPath(savedPath);
        file.setContentType(extension);

        //전송받은 파일의 내용을 해당경로의 파일에 이동 후 저장.
        files.transferTo(new File(savedPath));

        // 데이터베이스에 파일 정보 저장 
        int fileId = fileDao.save(file);
        

        return Integer.toString(fileId);
	}
	
	public FileDto findFile(String id){
		id.trim();
		
		
		try {
			
			
			int int_id = Integer.parseInt(id);
			
			return fileDao.findFile(int_id);
		
		}catch(NumberFormatException e){
			return null;
			
		}catch(Exception e){
            e.printStackTrace();
            return null;
        }
	}
	
	public FileDto findFileByName(String fileName){
		
		return fileDao.findFileByName(fileName);
	}
	
	@Transactional
	public String deleteFile(String savedName) {
		
		String delId = fileDao.getFileId(savedName);
		fileDao.deleteFile(savedName);
		
		return delId;
	}
	
	public void setNoticeId(int id,String fileIds_str) {
		
		if(fileIds_str==null||fileIds_str.trim().isEmpty() ) {
			//파일 아이디가 널 이거나 공백 값일 경우에는 아무것도 수행X
			
		}else { //파일 아이디 값이 있을경우
			
			fileIds_str.trim();
			
			//파일 아이디가 여러개 일 경우
			if(fileIds_str.contains(",")) {
				String[] fileIdsArr = fileIds_str.split(",");
				for( String fileId : fileIdsArr) {
					fileDao.setNoticeId(id,fileId);
				}
				
			}else { //파일 아이디가 한개 일 경우
				
				fileDao.setNoticeId(id,fileIds_str);
			}
			
			
		}
		
	}
	
	
}
