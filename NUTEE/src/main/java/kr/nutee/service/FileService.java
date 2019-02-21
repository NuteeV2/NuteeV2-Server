package kr.nutee.service;

import java.util.List;

import kr.nutee.dao.File;
import kr.nutee.model.File.FileListResponseDto;

/*
 * File Service Interface
 *
 * @author choiyk
 */
public interface FileService {

	//Table의 해당 col의 전체 파일 이름 리스트 출력
	List<FileListResponseDto> findAllByFileTableIdAndColId(int fileTableId, int colId);

	//파일 저장
	void insert(File file);

	/*
	 * 파일 삭제
	 * @return 삭제한 파일의 이름
	 * 저장된 파일도 지우기 위함
	 */
	String delete(int id);

}
