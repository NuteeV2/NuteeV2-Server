package kr.nutee.service.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dto.Board;
import kr.nutee.exception.InvalidDataException;
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

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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
		return board;
	}

	/*
	 * @param 게시판 id, 게시판 이름
	 */
	@Override
	public void insert(BoardInsertRequestDto board) throws InvalidDataException{
		Set<ConstraintViolation<BoardInsertRequestDto>> validateErrors = validator.validate(board);
		if(validateErrors.isEmpty()) {
			boardMapper.insert(board);
		}
		else{
			throw new InvalidDataException(validateErrors.toString());
		}
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
