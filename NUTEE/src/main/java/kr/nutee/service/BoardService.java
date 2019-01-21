package kr.nutee.service;

import java.util.List;

import kr.nutee.dto.Board;
import kr.nutee.exception.InvalidDataException;
import kr.nutee.model.BoardInsertRequestDto;

/*
 * Board Service Interface
 *
 * @author choiyk
 */
public interface BoardService {

	//전체 게시판 조회
	public abstract List<Board> findAll();

	//게시판 id로 조회
	public abstract Board findOne(int id);

	//게시판 추가
	public abstract void insert(BoardInsertRequestDto board) throws InvalidDataException;

	//게시판 삭제
	public abstract void delete(int id);

	//게시판 이름 변경
	public abstract void update(Board board);

}
