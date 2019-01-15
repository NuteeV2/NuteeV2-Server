package kr.nutee.service;

import java.util.List;

import kr.nutee.dto.Board;

/*
 * Board Service Interface
 *
 * @Author choiyk
 */
public interface BoardService {

	//전체 게시판 조회
	public abstract List<Board> findAll();

/*	//게시판 id로 조회
	public abstract Board findOne(int id);

	//게시판 추가
	public abstract void add(Board board);

	//게시판 삭제
	public abstract void delete(int id);

	//게시판 이름 변경
	public abstract void edit(Board board);*/

}
