package kr.nutee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.nutee.dao.File;
import kr.nutee.model.File.FileListResponseDto;
import kr.nutee.repository.mapper.FileMapper;
import kr.nutee.service.FileService;

public class FileServiceImpl implements FileService {

	@Autowired
	FileMapper fileMapper;

	/*
	 * Table의 해당 column에 첨부된 전체 파일 이름 리스트 출력
	 * @param fileTableId, colId
	 * @return 전체 파일 이름 리스트
	 */
	@Override
	public List<FileListResponseDto> findAllByFileTableIdAndColId(int fileTableId, int colId) {
		List<File> files = fileMapper.findAllByFileTableIdAndColId(fileTableId, colId);
		List<FileListResponseDto> frd = new ArrayList<>();
		for(File file : files) {
			frd.add(new FileListResponseDto(file.getId(), file.getFileName(), file.getFileType()));
		}
		return frd;
	}

	/*
	 * file 삽입
	 * @param filePath, fileName, fileTableId, colId, fileType
	 */
	@Override
	public void insert(File file) {
		fileMapper.insert(file);
	}

	/*
	 * 파일 삭제
	 * @param fileId
	 */
	@Override
	public String delete(int id) {
		String fileName = fileMapper.findFileNameById(id);
		fileMapper.delete(id);
		return fileName;
	}

}
