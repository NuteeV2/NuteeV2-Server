package kr.nutee.service;

import java.math.BigInteger;
import java.util.List;

import kr.nutee.dao.File;
import kr.nutee.dao.FileLink;

/*
 * File Link Service Interface
 *
 * @author choiyk
 */
public interface FileLinkService {

	/*
	 * 테이블의 id에 연결된 file 리스트 출력
	 * ex)id가 1인 게시글에 연결된 파일의 리스트를 출력할 경우 colId는 1이 된다.
	 */
	List<File> findAll(BigInteger colId);

	//@param FileLink를 구현한 클래스
	void insert(FileLink fileLink);

	void deleteAll(BigInteger colId);

}
