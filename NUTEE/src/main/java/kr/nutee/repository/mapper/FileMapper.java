package kr.nutee.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.File;

/*
 * File Mapper Interface
 * @author choiyk
 */
@Mapper
public interface FileMapper {

	//Table의 해당 col의 전체 파일 이름 리스트 출력
	List<File> findAllByFileTableIdAndColId(int fileTableId, int colId);

	//파일 id로 파일 이름 찾기
	String findFileNameById(int id);

	//파일 저장
	void insert(File file);

	//파일 삭제
	void delete(int id);

}
