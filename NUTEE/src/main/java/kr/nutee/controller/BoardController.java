package kr.nutee.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.dto.Board;
import kr.nutee.exception.BadRequestException;
import kr.nutee.model.BoardInsertRequestDto;
import kr.nutee.service.impl.BoardServiceImpl;

/*
 * Board Controller
 *
 * @author choiyk
 */
@RestController
@RequestMapping("/api/boards")
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	private final BoardServiceImpl boardService;

	@Autowired
	public BoardController(BoardServiceImpl boardService) {
		this.boardService = boardService;
	}

	/*
	 * 전체 게시판 반환
	 *
	 * @return 전체 게시판 List
	 * @see /api/boards
	 */
	@GetMapping("")
	public ResponseEntity<List<Board>> boards(){
		return new ResponseEntity<List<Board>>(boardService.findAll(), HttpStatus.OK);
	}

	/*
	 * 해당 id에 해당하는 게시판 1개 반환
	 *
	 * @return 게시판 1개
	 * @see /api/boards/{id}
	 */
	@GetMapping("{id}")
	public ResponseEntity<Board> board(@PathVariable("id") int id) {
		Board board = boardService.findOne(id);
		if(board == null) throw new BadRequestException("NonExist Board id :"+id);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	/*
	 * 게시판 1개 추가
	 *
	 * @param json형태의 board
	 * @return 결과 상태 코드
	 */
	@PostMapping("")
	public ResponseEntity<String> insert(@RequestBody BoardInsertRequestDto board){
		Set<ConstraintViolation<BoardInsertRequestDto>> validateErrors = validator.validate(board);
		if(validateErrors.isEmpty()) {
			try{
				boardService.insert(board);
				return new ResponseEntity<String>("", HttpStatus.OK);
			}
			catch(DataIntegrityViolationException e) {
				logger.info("DataIntegrityViolationException: 게시판 중복 오류");
				return new ResponseEntity<String>("게시판 이름 중복 오류", HttpStatus.CONFLICT);
			}
		}
		else {
			logger.info("DataIntegrityViolationException: 게시판 이름 조건 오류");
			return new ResponseEntity<String>(validateErrors.toString(), HttpStatus.CONFLICT);
		}
	}

}
