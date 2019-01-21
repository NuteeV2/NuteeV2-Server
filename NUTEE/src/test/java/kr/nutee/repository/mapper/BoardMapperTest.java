package kr.nutee.repository.mapper;

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
import kr.nutee.model.BoardInsertRequestDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardMapperTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BoardMapper boardMapper;

	@Test
	public void 게시판1개검색() {
		Board board = boardMapper.findOne(1);
		assertEquals(board.getBoardName(), "자유게시판");
		logger.info(board.toString());
	}

	@Test
	public void 게시판추가() {
		BoardInsertRequestDto board = new BoardInsertRequestDto();
		board.setBoardName("테스트 게시판 2");
		boardMapper.insert(board);

		logger.info("데이터 삽입 완료");

		List<Board> boards = boardMapper.findAll();

		logger.info(boards.toString());
	}

}
