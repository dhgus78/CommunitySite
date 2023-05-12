package com.keepgoing.website.dao;



import com.keepgoing.website.entity.UserVo;
import com.keepgoing.website.entity.UserVoRole;

//@Mapper
public interface UserMapper {
	//회원가입
	void saveUser(UserVo userVo);
	void memberRole(UserVo userVo);
	
	// 로그인
	UserVoRole findByUsername(String username);
	
	
	//이메일로 회원정보 얻기
	UserVoRole getMemberByEmail(String email);
	
	//회원탈퇴
	void membershipWithdrawal(int id);
	
	int passwordUpate(UserVoRole memberInfo);
	
	//임시 비밀번호 얻기위해 이메일, 이름으로 조회
	UserVoRole getMemberForTempPwd(String email, String name);
}
