package com.keepgoing.website.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationFailureHandler userLoginFailHandler;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests() //요청 권한 관련 설정.
        .antMatchers("/user/**").authenticated() //해당 url로 요청이 오면 공인되어야 한다. (로그인 되어야 한다.)
        .antMatchers("/manager/**").access("hasRole('ADMIN')or hasRole('MANAGER')") //해당 url로 요청이 오고 접근할려면 해당 역할을 부여받아야 한다.
        .antMatchers("/admin/**").access("hasRole('ADMIN')") //해당 url로 요청이 오고 접근할려면 해당 역할을 부여받아야 한다.
        .anyRequest().permitAll(); //위에 요청들 이외의 나머지 요청은 전부 허락한다.

        http.formLogin() //로그인 관련 설정.
        .loginPage("/loginForm") //해당 로그인 페이지 경로로
        .loginProcessingUrl("/login")// /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행 해줍니다.(따라서 컨트롤러에 설정 필요X)
        .failureHandler(userLoginFailHandler)
        .defaultSuccessUrl("/customer/main");
        
        http
        .logout() //로그아웃 관련 설정.
            .logoutUrl("/logout")
            .logoutSuccessUrl("/main");	// logout에 성공하면 해당 url로 redirect
    }   
    
    @Bean //해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    public BCryptPasswordEncoder encodePwd() {
    	return new BCryptPasswordEncoder();
    }
    
    /**
     * 로그인 인증 처리 메소드
     * @param auth
     * @throws Exception
     */

}