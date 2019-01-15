package kr.nutee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dto.Board;
import kr.nutee.exception.NonExistException;
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
		Board board = boardMapper.findOne(id);
		if(board == null) throw new NonExistException("NonExist Board id "+id);
		return board;
	}

}
