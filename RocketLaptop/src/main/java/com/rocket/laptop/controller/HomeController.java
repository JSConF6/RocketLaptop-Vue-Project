package com.rocket.laptop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rocket.laptop.config.auth.PrincipalDetails;
import com.rocket.laptop.model.ResponseDto;
import com.rocket.laptop.model.UserDto;
import com.rocket.laptop.service.UserService;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register/idCheck/{id}")
	public int idCheck(@PathVariable("id") String id) {
		logger.info("아이디 체크 : " + id);
		return userService.isId(id);
	}
	
	@PostMapping("/register")
	public ResponseDto<String> register(@RequestBody UserDto userDto){
		logger.info("회원가입 시작");
		
		int result = userService.register(userDto);
		
		if(result != 1) {
			return new ResponseDto<String> (HttpStatus.BAD_REQUEST.value(), "회원가입 실패");
		}
		
		return new ResponseDto<String> (HttpStatus.OK.value(), "회원가입 성공");
	}
	
	@GetMapping("/getSession")
	public ResponseDto<Map<String, Object>> getSession(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		logger.info("세션값 가져오기");
		System.out.println(principalDetails);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(principalDetails == null) {
			map.put("message", "사용자 정보 가져오기 실패");
			return new ResponseDto<Map<String, Object>> (HttpStatus.OK.value(), map);
		}
		
		UserDto userDto = principalDetails.getUserDto();
		
		map.put("user_id", userDto.getUser_id());
		map.put("user_role", userDto.getUser_role());
		map.put("user_name", userDto.getUser_name());
		
		return new ResponseDto<Map<String, Object>> (HttpStatus.OK.value(), map);
	}
	
	@GetMapping("/logoutSuccess")
	public ResponseDto<String> logoutSuccess() {
		logger.info("로그아웃 성공");
		return new ResponseDto<String> (HttpStatus.OK.value(), "로그아웃 성공");
	}
	
	@GetMapping("/loginSuccess")
	public ResponseDto<Map<String, Object>> loginSuccess(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		logger.info("로그인 성공");
		
		UserDto userDto = principalDetails.getUserDto();
		
		Map<String, Object> user = new HashMap<String, Object>();
		user.put("user_id", userDto.getUser_id());
		user.put("user_role", userDto.getUser_role());
		user.put("user_name", userDto.getUser_name());
		user.put("message", "로그인 성공");
		
		return new ResponseDto<Map<String, Object>> (HttpStatus.OK.value(), user);
	}
	
	@GetMapping("/loginFail")
	public ResponseDto<String> loginFail() {
		logger.info("로그인 실패");
		return new ResponseDto<String>(HttpStatus.UNAUTHORIZED.value(), "로그인 실패");
	}
	
	@GetMapping("/accessDenied")
	public ResponseDto<String> accessDenied() {
		logger.info("권한 없이 페이지 접속");
		
		return new ResponseDto<String> (HttpStatus.FORBIDDEN.value(), "권한이 없습니다.");
	}
	
	@GetMapping("/admin/authorityCheck")
	public ResponseDto<String> as(){
		return new ResponseDto<String> (HttpStatus.OK.value(), "해당 페이지의 권한 있습니다.");
	}
}
