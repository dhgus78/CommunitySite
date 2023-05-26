package com.keepgoing.website.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepgoing.website.dao.UserMapper;
import com.keepgoing.website.entity.UserVo;
import com.keepgoing.website.entity.UserVoRole;
import com.keepgoing.website.function.CurrentDateTime;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	
	
	@Autowired 
	BCryptPasswordEncoder passwordEncoder;
	 
	 
	
	
	@Transactional
	@Override
	public void joinUser(UserVo userVo) {
		//이메일 중복체크
		UserVo checkEmailResult = userMapper.checkEmailDuplicate(userVo);
		//닉네임 중복체크
		UserVo checkNameResult = userMapper.checkNameDuplicate(userVo);
		
		if(checkEmailResult==null && checkNameResult==null) {//해당 이메일이 없을경우 가입 진행
			userVo.setPwd(passwordEncoder.encode(userVo.getPwd()));//비밀번호 인코딩후 재세팅
		 	
			userMapper.saveUser(userVo);
			userMapper.memberRole(userVo);			
		}else if(checkEmailResult!=null){ //이미 해당 이메일이 존재 할 경우 예외 던지기
			throw new RuntimeException("이미 등록된 이메일입니다.");

		}else {//이미 해당 이름(닉네임)이 존재 할 경우 예외 던지기
			throw new RuntimeException("이미 등록된 이름(닉네임)입니다.");
		}
	}




	@Override
	public UserVoRole getMemberByEmail(String email) {
		
		return userMapper.getMemberByEmail(email);
	}




	@Override
	public void membershipWithdrawal(int id) {
		userMapper.membershipWithdrawal(id);
		
	}




	@Override
	public boolean checkPassword(String inputPwd, String memberPwd) {
		
		boolean matcheResult = passwordEncoder.matches(inputPwd, memberPwd);
		
		return matcheResult;
	}




	@Override
	public int passwordUpate(UserVoRole memberInfo, String input_password) {
		
		//입력된 패스워드를 인코딩
		String encoded_password = passwordEncoder.encode(input_password);
		
		//인코딩 된 패스워드를 객체에 다시 세팅
		memberInfo.setPwd(encoded_password);
		
		//현재시간 얻어서 업데이트 날짜도 같이 세팅
		Date time = CurrentDateTime.getCurrentTime();
		
		memberInfo.setUpdate_date(time);
		
		
		return userMapper.passwordUpate(memberInfo);
	}




	@Override
	public UserVoRole getMemberForTempPwd(String email, String name) {
		
		return userMapper.getMemberForTempPwd(email, name);
	}



}


/*
 * public boolean confirmUser(String password, String dbPassword) { return
 * passwordEncoder.matches(password, dbPassword); }
 */
