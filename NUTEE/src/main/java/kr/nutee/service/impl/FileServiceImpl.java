package kr.nutee.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import kr.nutee.dao.File;
import kr.nutee.playload.UploadFileResponse;
import kr.nutee.repository.mapper.FileMapper;
import kr.nutee.service.FileService;

public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper fileMapper;

	@Autowired
	FileStorageService fileStorageService;

	/*
	 * Table의 해당 column에 첨부된 전체 파일 이름 리스트 출력
	 * @param fileTableId, colId
	 * @return 전체 파일 이름 리스트
	 */
	@Override
	public List<File> findAllByFileTableIdAndColId(int fileTableId, int colId) {
		return fileMapper.findAllByFileTableIdAndColId(fileTableId, colId);
	}

	/*
	 * file 삽입
	 * @param filePath, fileName, fileTableId, colId, fileType
	 */
	@Override
	public List<UploadFileResponse> insert(int tableId, int colId, MultipartFile[] files) {
		//파일 저장
		List<UploadFileResponse> uploadFileResponses =
				Arrays.asList(files)
				.stream()
				.map(file -> fileStorageService.storeFile(file))
				.collect(Collectors.toList());

		//DB에 File 정보 추가
		for(UploadFileResponse r : uploadFileResponses){
			fileMapper.insert(new File(0, fileStorageService.getFileStorageLocation(), r.getFileName(), r.getFileType(), tableId, colId));
		}

		return uploadFileResponses;
	}

	/*
	 * 파일 삭제
	 * @param File
	 */
	@Override
	public void delete(File file) {
		fileMapper.delete(file.getId());
		fileStorageService.deleteFile(file.getFileName());
	}

	/*
	 * 파일 삭제
	 * @param List<File> files
	 */
	@Override
	public void delete(List<File> files) {
		for(File file : files) {
			delete(file);
		}
	}

	/*
	 * 파일 삭제
	 * @param int tableId, int colId
	 */
	@Override
	public void delete(int tableId, int colId) {
		List<File> files = findAllByFileTableIdAndColId(tableId, colId);
		delete(files);
	}

	/*
	 * 파일 수정
	 * 이름이 같지만 파일의 내용이 다를 경우를 위해 기존에 저장했던 파일들을 삭제하고 다시 저장함
	 * @param 기존 파일 정보 fileInfs, Table id, col id, 새로 저장할 파일 리스트
	 */
	@Override
	public void update(List<File> fileInfs, int tableId, int colId, MultipartFile[] files) {
		delete(fileInfs);
		insert(tableId, colId, files);
	}

}
