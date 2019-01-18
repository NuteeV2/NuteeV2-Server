package kr.nutee.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.nutee.dto.Board;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class BoardServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BoardService boardService;

	@Test
	public void 전체게시판출력() {
		List<Board> boards = boardService.findAll();
		assertEquals(boards.size(), 7);
		logger.info(boards.toString());
	}

	@Test
	public void 게시판하나출력() {
		Board board = boardService.findOne(1);
		assertEquals(board.getId(), 1);
		logger.info(board.toString());
	}

}
