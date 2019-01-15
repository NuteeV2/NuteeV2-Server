package kr.nutee.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.nutee.dto.Board;
import kr.nutee.exception.NonExistException;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class BoardServiceTest {

	@Autowired
	BoardService boardService;

	@Test
	public void 전체게시판출력() {
		List<Board> boards = boardService.findAll();
		assertEquals(boards.size(), 7);
		for(Board b : boards) {
			System.out.println(b.toString());
		}
	}

	@Test
	public void 게시판하나출력() {
		Board board = boardService.findOne(1);
		assertEquals(board.getId(), 1);
		System.out.println(board.toString());
	}

	@Test(expected=NonExistException.class)
	public void 존재하지않는게시판() {
		boardService.findOne(8);
	}

}
