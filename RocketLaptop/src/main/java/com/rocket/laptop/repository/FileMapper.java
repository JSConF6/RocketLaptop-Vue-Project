package com.rocket.laptop.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.rocket.laptop.model.FileDto;

@Mapper
@Repository
public interface FileMapper {
	public void fileInsert(FileDto fileDto);
	
	public FileDto findByType(Map<String, Object> map);
	
	public List<FileDto> getProductFile(String product_code);

	public void fileModify(Map<String, Object> map);
	
}
