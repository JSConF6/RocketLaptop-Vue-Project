package com.rocket.laptop.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rocket.laptop.model.UserDto;
import com.rocket.laptop.repository.UserMapper;

// 시큐리티 설정에서 loginProcessingUrl("login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	
	// 시큐리티 session(내부 Authentication(내부 UserDetails))
	// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailsService의 loadUserByUsername()");
		System.out.println(id);
		UserDto userDto = userMapper.findById(id);
		if(userDto != null) {
			return new PrincipalDetails(userDto);
		}
		throw new UsernameNotFoundException("유저 정보를 찾을 수 없습니다.");
	}
	
}
