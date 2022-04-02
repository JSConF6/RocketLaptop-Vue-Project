package com.rocket.laptop.controller;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.rocket.laptop.model.FileDto;
import com.rocket.laptop.model.ImageTypeDto;
import com.rocket.laptop.model.PageHandler;
import com.rocket.laptop.model.ProductDetailDto;
import com.rocket.laptop.model.ProductDto;
import com.rocket.laptop.model.ProductListDto;
import com.rocket.laptop.model.response.Response;
import com.rocket.laptop.service.FileService;
import com.rocket.laptop.service.ProductService;

@RestController
@RequestMapping("/api/admin/product")
public class adminProductController {
	
	private final Logger logger = LoggerFactory.getLogger(adminProductController.class);
	
	@Value("${savefoldername}")
	private String saveFolder;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/list")
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
	
	@GetMapping("/detail/{product_code}")
	public ResponseEntity<Response> adminProductDetail(@PathVariable("product_code") String product_code){
		ProductDetailDto productDetailDto = productService.getProductDetail(product_code);
		
		if(productDetailDto == null) {
			return ResponseEntity.ok(
					Response.builder()
					.status(HttpStatus.BAD_REQUEST.value())
					.message("상품 상세정보 가져오기 실패")
					.build());
		}
		
		List<FileDto> fileList = fileService.getProductFile(product_code);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productDetail", productDetailDto);
		map.put("fileList", fileList);
		
		return ResponseEntity.ok(
				Response.builder()
				.status(HttpStatus.OK.value())
				.message("상품 상세정보 가져오기 성공")
				.body(map)
				.build());
	}
	
	@DeleteMapping("/delete/{product_code}")
	public ResponseEntity<Response> adminProductDelete(@PathVariable("product_code") String product_code){
		logger.info("상품 삭제");
		
		int result = productService.productDelete(product_code);
		
		if(result == 1) {
			return ResponseEntity.ok(
					Response.builder()
					.status(HttpStatus.OK.value())
					.message("상품 삭제 완료")
					.build());
		}
		
		return ResponseEntity.ok(
				Response.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.message("상품 삭제 실패")
				.build());
	}
	
	@PostMapping("/add")
	public ResponseEntity<Response> adminProductAdd(ProductDto productDto,
			@RequestParam("images") List<MultipartFile> multipartFiles, @RequestPart("image_type") List<ImageTypeDto> imageTypeList) throws Exception{
		logger.info("상품등록");
//		productService.productAdd(productDto);
		System.out.println(imageTypeList);
		for(int i = 0; i < multipartFiles.size(); i++) {
			MultipartFile file = (MultipartFile) multipartFiles.get(i);
			
			System.out.println(file.getOriginalFilename());
			System.out.println(imageTypeList);
			
//			if(file.isEmpty()) {
//				continue;
//			}
//			
//			FileDto fileDto = new FileDto();
//			
//			String originalFileName = file.getOriginalFilename(); // 오리지날 파일명
//			logger.info("오리지날 파일명 : " + originalFileName);
//			
//			fileDto.setProduct_img_original_name(originalFileName);
//			
//			fileDto.setProduct_code(productDto.getProduct_code());
//			
//			String fileDBName = fileDBName(originalFileName, saveFolder);
//			
//			file.transferTo(new File(saveFolder + fileDBName));
//			
//			fileDto.setProduct_img_name(fileDBName);
//			
//			fileService.fileAdd(fileDto);
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
