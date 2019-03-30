package kr.nutee.service;

import java.util.List;

import kr.nutee.dao.Board;
import kr.nutee.model.Board.BoardInsertAndUpdateRequestDto;

/*
 * Board Service Interface
 *
 * @author choiyk
 */
public interface BoardService {

	//전체 게시판 조회
	List<Board> findAll();

	//게시판 id로 조회
	Board findOne(int id);

	//게시판 추가
	void insert(BoardInsertAndUpdateRequestDto board);

	//게시판 삭제
	void delete(int id);

	//게시판 이름 변경
	void update(int id, BoardInsertAndUpdateRequestDto board);

}
