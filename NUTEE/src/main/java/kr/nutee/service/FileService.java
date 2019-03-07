package kr.nutee.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.nutee.dao.File;
import kr.nutee.playload.UploadFileResponse;

/*
 * File Service Interface
 *
 * @author choiyk
 */
public interface FileService {

	//Table의 해당 col의 전체 파일 이름 리스트 출력
	List<File> findAllByFileTableIdAndColId(int fileTableId, int colId);

	/*
	 * 파일 저장
	 * @param Table의 col id, 입력받은 파일 리스트
	 */
	List<UploadFileResponse> insert(int tableId, int colId, MultipartFile[] files);

	//파일 삭제
	void delete(File file);

	//파일들 삭제
	void delete(List<File> files);

	//파일들 삭제
	void delete(int tableId, int colId);

	//파일 수정
	void update(List<File> fileInfs, int tableId, int colId, MultipartFile[] files);

}
