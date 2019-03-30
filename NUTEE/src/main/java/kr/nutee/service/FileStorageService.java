package kr.nutee.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import kr.nutee.playload.UploadFileResponse;

/*
 * File Storage Service Interface
 *
 * @author choiyk
 */
public interface FileStorageService {

	//파일 저장
	UploadFileResponse storeFile(MultipartFile file);

	//파일 가져오기
	Resource loadFileAsResource(String fileName);

	//파일 삭제
	void deleteFile(String fileName);

	//파일 저장 경로 가져오기
	String getFileStorageLocation();

}
