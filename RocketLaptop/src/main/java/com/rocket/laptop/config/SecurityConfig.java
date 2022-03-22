package com.rocket.laptop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.CorsFilter;

import com.rocket.laptop.config.handler.CustomAccessDeniedHandler;
import com.rocket.laptop.config.handler.LoginFailHandler;
import com.rocket.laptop.config.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성, preAuthorize, postAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CorsFilter corsFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http
		.addFilter(corsFilter)
		.authorizeRequests()
		.antMatchers("/api/user/**").authenticated()
		.antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginProcessingUrl("/api/login")
		.usernameParameter("user_id")
		.passwordParameter("user_password")
		.defaultSuccessUrl("/")
		.successHandler(new LoginSuccessHandler())
		.failureHandler(new LoginFailHandler())
		.and()
		.logout()
		.logoutUrl("/api/logout")
		.logoutSuccessUrl("/api/logoutSuccess")
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.and()
		.exceptionHandling()
		.accessDeniedHandler(new CustomAccessDeniedHandler());
	}
}
