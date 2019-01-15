package kr.nutee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.dto.Board;
import kr.nutee.service.impl.BoardServiceImpl;

/*
 * Board Controller
 *
 * @Author choiyk
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
	 * @Return 전체 게시판 List
	 * @See /boards/
	 */
	@GetMapping("/")
	public List<Board> boards(){
		return boardService.findAll();
	}

}
