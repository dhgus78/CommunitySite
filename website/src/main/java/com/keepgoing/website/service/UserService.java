package com.keepgoing.website.service;

import com.keepgoing.website.entity.UserVo;
import com.keepgoing.website.entity.UserVoRole;

public interface UserService {
	public void joinUser(UserVo userVo);
	public UserVoRole getMemberByEmail(String email);
	public void membershipWithdrawal(int id);
	boolean checkPassword(String inputPwd, String memberPwd);
	int passwordUpate(UserVoRole memberInfo, String input_password);
	UserVoRole getMemberForTempPwd(String email,String name);
	
}
