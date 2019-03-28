package kr.nutee.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dao.File;
import kr.nutee.repository.mapper.FileMapper;
import kr.nutee.service.FileService;

/*
 * File Service 구현 클래스
 * @author choiyk
 */
@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper fileMapper;

	/*
	 * 파일 찾기
	 */
	@Override
	public File findOne(BigInteger id) {
		return fileMapper.findOne(id);
	}

	/*
	 * 파일 추가
	 */
	@Override
	public BigInteger insertAndGetId(File file) {
		fileMapper.insertAndGetId(file);
		return file.getId();
	}

	/*
	 * 파일 삭제
	 */
	@Override
	public void delete(BigInteger id) {
		fileMapper.delete(id);
	}



}
