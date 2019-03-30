package kr.nutee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.dao.Board;
import kr.nutee.model.Board.BoardInsertAndUpdateRequestDto;
import kr.nutee.service.impl.BoardServiceImpl;

/*
 * Board Controller
 *
 * @author choiyk
 */
@RestController
@RequestMapping("/guest/boards")
public class BoardController {

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
		return new ResponseEntity<>(boardService.findAll(), HttpStatus.OK);
	}

	/*
	 * 해당 id에 해당하는 게시판 1개 반환
	 *
	 * @return 게시판 1개
	 * @see /api/boards/{id}
	 */
	@GetMapping("{id}")
	public ResponseEntity<Board> board(@PathVariable("id") int id) {
		return new ResponseEntity<>(boardService.findOne(id), HttpStatus.OK);
	}

	/*
	 * 게시판 1개 추가
	 *
	 * @param json형태의 board
	 * @return 결과 상태 코드
	 */
	@PostMapping("")
	public ResponseEntity<String> insert(@Valid @RequestBody BoardInsertAndUpdateRequestDto board){
		boardService.insert(board);
		return new ResponseEntity<>("", HttpStatus.CREATED);
	}

	/*
	 * 게시판 이름 수정
	 *
	 * @param json형태의 board
	 * @return 결과 상태 코드
	 */
	@PatchMapping("{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @Valid @RequestBody BoardInsertAndUpdateRequestDto board){
		boardService.update(id, board);
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}

	/*
	 * 게시판 삭제
	 * @param 게시판 id
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id){
		boardService.delete(id);
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}

}
