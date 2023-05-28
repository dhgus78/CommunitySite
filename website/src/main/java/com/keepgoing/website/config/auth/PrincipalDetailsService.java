package com.keepgoing.website.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.keepgoing.website.dao.UserMapper;
import com.keepgoing.website.entity.UserVoRole;

//시큐리티 설정에서 loginProcessUrl("/login");
//login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행.

@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private UserMapper userMapper;

	//시큐리티 session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVoRole userEntity = userMapper.findByUsername(username);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		
		throw new UsernameNotFoundException(username);
	}
}
