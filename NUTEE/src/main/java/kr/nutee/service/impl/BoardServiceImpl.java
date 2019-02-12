package kr.nutee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dao.Board;
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
		boardMapper.insert(board);
	}

	@Override
	public void delete(int id) {
		// TODO delete 메소드 작성해야 함..

	}

	/*
	 * @param 게시판 id, 게시판 이름
	 */
	@Override
	public void update(int id, BoardInsertAndUpdateRequestDto board) {
		//TODO bad request 처리 해야하나..? 없는 id여도 update query는 성공 뜨고 디비엔 아무 변화도 없네
		boardMapper.update(id, board);
	}

}
