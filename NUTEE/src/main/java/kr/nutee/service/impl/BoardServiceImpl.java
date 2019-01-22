package kr.nutee.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dto.Board;
import kr.nutee.exception.BadRequestException;
import kr.nutee.model.BoardInsertRequestDto;
import kr.nutee.repository.mapper.BoardMapper;
import kr.nutee.service.BoardService;

/*
 * Board Service 구현 클래스
 * @author choiyk
 */
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;

	/*
	 * @return 전체 게시판 목록
	 */
	@Override
	public List<Board> findAll() {
		return boardMapper.findAll();
	}

	/*
	 * @param 게시판 id
	 * @return 해당 id의 게시판 데이터
	 */
	@Override
	public Board findOne(int id) {
		Board board = Optional.ofNullable(boardMapper.findOne(id)).orElseThrow(()->new BadRequestException("NonExist Board id "+id));
		return board;
	}

	/*
	 * @param 게시판 id, 게시판 이름
	 */
	@Override
	public void insert(BoardInsertRequestDto board){
		boardMapper.insert(board);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Board board) {
		boardMapper.update(board);
	}

}
