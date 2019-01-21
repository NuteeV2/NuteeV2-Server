package kr.nutee.controller;

import java.util.List;

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

	private final BoardServiceImpl boardService;

	@Autowired
	public BoardController(BoardServiceImpl boardService) {
		this.boardService = boardService;
	}

	/*
	 * 전체 게시판 반환
	 *
	 * @return 전체 게시판 List
	 * @see /boards/
	 */
	@GetMapping("")
	public ResponseEntity<List<Board>> boards(){
		return new ResponseEntity<List<Board>>(boardService.findAll(), HttpStatus.OK);
	}

	/*
	 * 해당 id에 해당하는 게시판 1개 반환
	 *
	 * @return 게시판 1개
	 * @see /boards/{id}
	 */
	@GetMapping("{id}")
	public ResponseEntity<Board> board(@PathVariable("id") int id) {
		Board board = boardService.findOne(id);
		if(board == null) throw new BadRequestException("NonExist Board id "+id);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<String> insert(@RequestBody Board board){
		try{
			boardService.insert(board);
			return new ResponseEntity<String>("", HttpStatus.OK);
		}
		catch(DataIntegrityViolationException e) {
			logger.info("DataIntegrityViolationException: 데이터 삽입 오류");
			return new ResponseEntity<String>("데이터 삽입 오류", HttpStatus.CONFLICT);
		}
	}

}
