package com.keepgoing.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.keepgoing.website.entity.MailDto;
import com.keepgoing.website.entity.UserVoRole;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private UserService userService;
	@Autowired
	private static final String FROM_ADDRESS = "dhgus78@gmail.com";

	
	
	
    
    public MailDto createMailAndChangePassword(UserVoRole member) {
    	String tempPwd = getTempPassword();
        MailDto dto = new MailDto();
        String email = member.getEmail();
        dto.setAddress(email);
        dto.setTitle(email+"님의 Keep Going 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. Keep Going 임시비밀번호 안내 관련 이메일 입니다." + "[" + email + "]" +"님의 임시 비밀번호는 "
        + tempPwd + " 입니다.");
        userService.passwordUpate(member, tempPwd);
        return dto;
    }
    
    //임시 비밀번호 생성 (상징)
  	 public String getTempPassword(){
  	        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
  	                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

  	        String str = "";

  	        int idx = 0;
  	        for (int i = 0; i < 10; i++) {
  	            idx = (int) (charSet.length * Math.random());
  	            str += charSet[idx];
  	        }
  	        return str;
  	 }
    
    
    public void mailSend(MailDto mailDto){
        
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(mailDto.getAddress());
        message.setFrom(EmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        javaMailSender.send(message);
        System.out.println("이메일 전송 완료!");
    }
	
}
