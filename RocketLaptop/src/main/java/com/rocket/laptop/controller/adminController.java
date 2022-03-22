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
	
	@GetMapping("/product/list")
	public ResponseEntity<Response> adminProductView(
			@RequestParam(value="page", defaultValue = "1", required = false) int page,
			@RequestParam(value="limit", defaultValue = "4", required = false) int limit) {
		logger.info("상품리스트 가져오기");
		
		logger.info("limit : " + limit);
		
		int listCount = productService.getProductListCount();
		logger.info("총 게시물 갯수 : " + listCount);
		
		PageHandler pageHandler = new PageHandler(page, listCount, limit);
		
		if(pageHandler.getEndPage() > pageHandler.getMaxPage()) {
			pageHandler.setEndPage(pageHandler.getMaxPage());
		}
		
		List<ProductListDto> productList = productService.getProductList(pageHandler);
		logger.info("상품 리스트 갯수 : " + productList);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageHandler", pageHandler);
		map.put("productList", productList);
		
		return ResponseEntity.ok(
				Response.builder()
				.status(HttpStatus.OK.value())
				.message("상품 리스트 가져오기 성공")
				.body(map)
				.build());
	}
	
	@PostMapping("/product/add")
	public ResponseEntity<Response> adminProductAdd(ProductDto productDto,
			@RequestParam("image_upload") List<MultipartFile> multipartFiles) throws Exception{
		logger.info("상품등록");
		
		productService.productAdd(productDto);
		
		for(int i = 0; i < multipartFiles.size(); i++) {
			MultipartFile file = (MultipartFile) multipartFiles.get(i);
			
			if(file.isEmpty()) {
				continue;
			}
			
			FileDto fileDto = new FileDto();
			
			if(i == 0) {
				fileDto.setProduct_img_type(1);
			}else if(i == 1 || i == 2 || i == 3) {
				fileDto.setProduct_img_type(2);
			}else if(i == 4) {
				fileDto.setProduct_img_type(3);
			}
			
			String originalFileName = file.getOriginalFilename(); // 오리지날 파일명
			logger.info("오리지날 파일명 : " + originalFileName);
			
			fileDto.setProduct_img_original_name(originalFileName);
			
			fileDto.setProduct_code(productDto.getProduct_code());
			
			String fileDBName = fileDBName(originalFileName, saveFolder);
			
			file.transferTo(new File(saveFolder + fileDBName));
			
			fileDto.setProduct_img_name(fileDBName);
			
			fileService.fileAdd(fileDto);
		}
		
		return ResponseEntity.ok(
				Response.builder()
				.status(HttpStatus.OK.value())
				.message("상품 등록 성공")
				.build());
	}
	
	private String fileDBName(String originalFileName, String saveFolder) {
		// 새로운 폴더 이름 : 오늘 년+월+일
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		
		String homeDir = saveFolder + year + "-" + month + "-" + date;
		logger.info("저장될 파일 경로 : " + homeDir);
		
		File path = new File(homeDir);
		if((!path.exists())) {
			path.mkdir();
		}
		
		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자
		logger.info("파일 확장자 : " + fileExtension);
		
		String reFileName = "product" + year + month + date + UUID.randomUUID() + fileExtension; // 새로운 파일명
		logger.info("새로운 파일명 : " + reFileName);
		
		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + reFileName; // DB에 저장될 파일명
		logger.info("DB에 저장될 파일명 : " + fileDBName);
		
		return fileDBName;
		
	}
}
