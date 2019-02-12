package kr.nutee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import kr.nutee.dao.Board;
import kr.nutee.exception.DuplicationException;
import kr.nutee.model.Board.BoardInsertAndUpdateRequestDto;
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
		return boardMapper.findOne(id);
	}

	/*
	 * @param 게시판 이름
	 */
	@Override
	public void insert(BoardInsertAndUpdateRequestDto board){
		try {
			boardMapper.insert(board);
		}catch(DataIntegrityViolationException e) {
			throw new DuplicationException("boardName", "Board name already exist");
		}
	}

	/*
	 * @param 게시판 id, 게시판 이름
	 */
	@Override
	public void update(int id, BoardInsertAndUpdateRequestDto board) {
		try {
			boardMapper.update(id, board);
		}catch(DataIntegrityViolationException e) {
			throw new DuplicationException("boardName", "Board name already exist");
		}
	}

	@Override
	public void delete(int id) {
		// TODO delete 메소드 작성해야 함..

	}

}
