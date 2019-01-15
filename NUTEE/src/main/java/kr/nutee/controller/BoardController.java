package kr.nutee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.dto.Board;
import kr.nutee.service.impl.BoardServiceImpl;

/*
 * Board Controller
 *
 * @author choiyk
 */
@RestController
@RequestMapping("/boards")
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
	 * @see /boards/
	 */
	@GetMapping("/")
	public List<Board> boards(){
		return boardService.findAll();
	}

	/*
	 * 해당 id에 해당하는 게시판 1개 반환
	 *
	 * @return 게시판 1개
	 * @see /boards/{id}
	 */
	@GetMapping("{id}")
	public Board board(@PathVariable("id") int id) {
		return boardService.findOne(id);
	}

}
