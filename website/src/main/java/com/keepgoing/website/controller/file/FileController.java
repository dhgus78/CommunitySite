package com.keepgoing.website.controller.file;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.keepgoing.website.entity.FileDto;
import com.keepgoing.website.service.FileService;


@RestController
@Controller
@RequestMapping("/file")
public class FileController {
	
	
	private String fileDir = "C:/spring_boot_work/fileUpload/";

	@Autowired
	private FileService fileService;
	
	
	@GetMapping("/download/{savedName}")
	public ResponseEntity<Resource> filedownload(@PathVariable("savedName") String savedFileName) throws MalformedURLException {
		
		FileDto file = fileService.findFileByName(savedFileName);
		
		UrlResource resource = new UrlResource("file:" + fileDir + file.getSavedPath());
		
		String encodedFileName = UriUtils.encode(file.getOrgName(), StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);

	}
}
