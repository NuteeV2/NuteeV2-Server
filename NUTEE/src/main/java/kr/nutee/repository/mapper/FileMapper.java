package kr.nutee.repository.mapper;

import java.math.BigInteger;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.File;

/*
 * File Mapper Interface
 * @author choiyk
 */
@Mapper
public interface FileMapper {

	//파일 찾기
	File findOne(BigInteger id);

	//파일 저장
	void insertAndGetId(File file);

	//파일 삭제
	void delete(BigInteger id);

}
