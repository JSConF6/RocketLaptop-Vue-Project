package com.rocket.laptop.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.util.CollectionUtils;
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
import com.rocket.laptop.model.ResponseDto;
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
	public ResponseDto<Map<String, Object>> adminProductView(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "4", required = false) int limit) {
		logger.info("??????????????? ????????????");

		logger.info("limit : " + limit);

		int listCount = productService.getProductListCount();
		logger.info("??? ????????? ?????? : " + listCount);

		PageHandler pageHandler = new PageHandler(page, listCount, limit);

		if (pageHandler.getEndPage() > pageHandler.getMaxPage()) {
			pageHandler.setEndPage(pageHandler.getMaxPage());
		}

		List<ProductListDto> productList = productService.getProductList(pageHandler);
		logger.info("?????? ????????? ?????? : " + productList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "?????? ????????? ???????????? ??????");
		map.put("pageHandler", pageHandler);
		map.put("productList", productList);

		return new ResponseDto<Map<String, Object>> (HttpStatus.OK.value(), map);
	}

	@GetMapping("/detail/{product_code}")
	public ResponseDto<Map<String, Object>> adminProductDetail(@PathVariable("product_code") String product_code) {
		ProductDetailDto productDetailDto = productService.getProductDetail(product_code);

		Map<String, Object> map = new HashMap<>();
		
		if (productDetailDto == null) {
			map.put("message", "?????? ???????????? ???????????? ??????");
			return new ResponseDto<Map<String, Object>> (HttpStatus.BAD_REQUEST.value(), map);
		}

		List<FileDto> fileList = fileService.getProductFile(product_code);

		map.put("productDetail", productDetailDto);
		map.put("fileList", fileList);

		return new ResponseDto<Map<String, Object>> (HttpStatus.OK.value(), map);
	}

	@DeleteMapping("/delete/{product_code}")
	public ResponseDto<String> adminProductDelete(@PathVariable("product_code") String product_code) {
		logger.info("?????? ??????");

		int result = productService.productDelete(product_code);

		if (result == 1) {
			return new ResponseDto<String> (HttpStatus.OK.value(), "?????? ?????? ??????");
		}

		return new ResponseDto<String> (HttpStatus.BAD_REQUEST.value(), "?????? ?????? ??????");
	}
	
	@PostMapping("/modify/{product_code}")
	public ResponseDto<String> adminProductModify(ProductDto productDto,
			@RequestParam(value = "images", required = false) List<MultipartFile> multipartFiles,
			@RequestPart("image_type") String imageTypeList) {
		logger.info("????????????");
		productService.productModify(productDto);
		
		if(CollectionUtils.isEmpty(multipartFiles)) {
			logger.info("?????? ?????? 5??? ????????? ??????");
			return new ResponseDto<String>(HttpStatus.OK.value(), "?????? ?????? ??????");
		}else {
			logger.info("????????? ???????????? ??????");
			
			ObjectMapper om = new ObjectMapper();

			try {
				
				List<ImageTypeDto> imageTypeDtos = om.readValue(imageTypeList, new TypeReference<List<ImageTypeDto>>() {
				});
				System.out.println(imageTypeDtos);
				for (int i = 0; i < multipartFiles.size(); i++) {
					MultipartFile file = (MultipartFile) multipartFiles.get(i);

					FileDto fileDto = new FileDto();
	
					String originalFileName = file.getOriginalFilename(); // ???????????? ?????????
					logger.info("???????????? ????????? : " + originalFileName);
	
					fileDto.setProduct_img_original_name(originalFileName);
	
					for (ImageTypeDto imageTypeDto : imageTypeDtos) {
						if(imageTypeDto == null) {
							continue;
						}
						
						if (imageTypeDto.getImage_name().equals(originalFileName)) {
							fileDto.setProduct_img_type(imageTypeDto.getImage_type());
							fileDto.setProduct_img_num(imageTypeDto.getImage_num());
						}
					}
	
					fileDto.setProduct_code(productDto.getProduct_code());
	
					String fileDBName = fileDBName(originalFileName, saveFolder);
	
					file.transferTo(new File(saveFolder + fileDBName));
	
					fileDto.setProduct_img_name(fileDBName);
	
					fileService.fileModify(fileDto, productDto.getProduct_code());
				}
			} catch (Exception e) {
				ProductDetailDto productDetailDto = productService.getProductDetail(productDto.getProduct_code());
				
				if(productDetailDto != null) {
					productService.productDelete(productDto.getProduct_code());
				}
				
				return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), "?????? ?????? ??????");
			}
		}

		return new ResponseDto<String>(HttpStatus.OK.value(), "?????? ?????? ??????");
	}

	@PostMapping("/add")
	public ResponseDto<String> adminProductAdd(ProductDto productDto,
			@RequestParam("images") List<MultipartFile> multipartFiles,
			@RequestPart("image_type") String imageTypeList) {
		logger.info("????????????");
		productService.productAdd(productDto);

		ObjectMapper om = new ObjectMapper();

		try {
			
			List<ImageTypeDto> imageTypeDtos = om.readValue(imageTypeList, new TypeReference<List<ImageTypeDto>>() {
			});

			for (int i = 0; i < multipartFiles.size(); i++) {
				MultipartFile file = (MultipartFile) multipartFiles.get(i);

				FileDto fileDto = new FileDto();

				String originalFileName = file.getOriginalFilename(); // ???????????? ?????????
				logger.info("???????????? ????????? : " + originalFileName);

				fileDto.setProduct_img_original_name(originalFileName);

				for (ImageTypeDto imageTypeDto : imageTypeDtos) {
					if (imageTypeDto.getImage_name().equals(originalFileName)) {
						fileDto.setProduct_img_type(imageTypeDto.getImage_type());
					}
				}

				fileDto.setProduct_code(productDto.getProduct_code());

				String fileDBName = fileDBName(originalFileName, saveFolder);

				file.transferTo(new File(saveFolder + fileDBName));

				fileDto.setProduct_img_name(fileDBName);

				fileService.fileAdd(fileDto);
			}
		} catch (Exception e) {
			ProductDetailDto productDetailDto = productService.getProductDetail(productDto.getProduct_code());
			
			if(productDetailDto != null) {
				productService.productDelete(productDto.getProduct_code());
			}
			
			return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), "?????? ?????? ??????");
		}

		return new ResponseDto<String>(HttpStatus.OK.value(), "?????? ?????? ??????");
	}

	private String fileDBName(String originalFileName, String saveFolder) {
		// ????????? ?????? ?????? : ?????? ???+???+???
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);

		String homeDir = saveFolder + year + "-" + month + "-" + date;
		logger.info("????????? ?????? ?????? : " + homeDir);

		File path = new File(homeDir);
		if ((!path.exists())) {
			path.mkdir();
		}

		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")); // ?????? ?????????
		logger.info("?????? ????????? : " + fileExtension);

		String reFileName = "product" + year + month + date + UUID.randomUUID() + fileExtension; // ????????? ?????????
		logger.info("????????? ????????? : " + reFileName);

		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + reFileName; // DB??? ????????? ?????????
		logger.info("DB??? ????????? ????????? : " + fileDBName);

		return fileDBName;

	}

}
