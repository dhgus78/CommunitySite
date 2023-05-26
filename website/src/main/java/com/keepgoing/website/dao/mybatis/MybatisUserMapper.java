package com.keepgoing.website.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepgoing.website.dao.UserMapper;
import com.keepgoing.website.entity.UserVo;
import com.keepgoing.website.entity.UserVoRole;

@Repository
public class MybatisUserMapper implements UserMapper{
	
	private UserMapper mapper;
	
	@Autowired
	public MybatisUserMapper(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(UserMapper.class);
	}
	
	@Override
	public UserVo checkEmailDuplicate(UserVo userVo) {
		UserVo result = mapper.checkEmailDuplicate(userVo);
		
		return result;
	}
	
	@Override
	public void saveUser(UserVo userVo) {
		mapper.saveUser(userVo);
		
	}

	@Override
	public void memberRole(UserVo userVo) {
		mapper.memberRole(userVo);
		
	}

	@Override
	public UserVoRole findByUsername(String username) {
		return mapper.findByUsername(username);
		
	}

	@Override
	public UserVoRole getMemberByEmail(String email) {
		return mapper.getMemberByEmail(email);
	}

	@Override
	public void membershipWithdrawal(int id) {
		mapper.membershipWithdrawal(id);
		
	}

	@Override
	public int passwordUpate(UserVoRole memberInfo) {
		
		return mapper.passwordUpate(memberInfo);
	}

	@Override
	public UserVoRole getMemberForTempPwd(String email, String name) {
		
		return mapper.getMemberForTempPwd(email, name);
	}

	


	
}
