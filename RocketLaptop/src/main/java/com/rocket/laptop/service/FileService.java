package com.rocket.laptop.service;

import java.util.List;
import java.util.Map;

import com.rocket.laptop.model.FileDto;

public interface FileService {
	public void fileAdd(FileDto fileDto);
	
	public FileDto isType(int type, String product_code);
	
	public List<FileDto> getProductFile(String product_code);

	public void fileModify(FileDto fileDto, String product_code);
}
