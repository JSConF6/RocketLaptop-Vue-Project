package com.rocket.laptop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rocket.laptop.model.CategoryDto;
import com.rocket.laptop.model.FileDto;
import com.rocket.laptop.model.PageHandler;
import com.rocket.laptop.model.ProductDto;
import com.rocket.laptop.model.ProductListDto;
import com.rocket.laptop.model.response.Response;
import com.rocket.laptop.service.CategoryService;
import com.rocket.laptop.service.FileService;
import com.rocket.laptop.service.ProductService;

@RestController
@RequestMapping("/api/admin")
public class adminController {
	
	private final Logger logger = LoggerFactory.getLogger(adminController.class);
	
	@Value("${savefoldername}")
	private String saveFolder;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/category/list")
	public ResponseEntity<Response> adminProductAddView() {
		logger.info("카테고리 리스트 가져오기");
		
		List<CategoryDto> categoryList = categoryService.getAllCategory();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryList", categoryList);
		
		return ResponseEntity.ok(
				Response.builder()
				.status(HttpStatus.OK.value())
				.message("카테고리 리스트 가져오기 성공")
				.body(map)
				.build());
	}
	
}
