package kr.nutee.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import kr.nutee.dto.Board;
import kr.nutee.model.BoardInsertRequestDto;
import kr.nutee.repository.mapper.BoardMapper;
import kr.nutee.service.impl.BoardServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Mock
	private BoardMapper boardMapper;

	@InjectMocks
	private BoardServiceImpl boardService;

	private Board board;
	private List<Board> boards;

	@Before
	public void init() {
		this.board = new Board(100, "테스트 게시판 1");
		this.boards = new ArrayList<Board>();
		boards.add(board);
	}

	@Test
	public void 전체게시판출력() {
		Mockito.when(boardMapper.findAll())
			.thenReturn(this.boards);
		List<Board> boardsTest = boardService.findAll();
		assertEquals(this.boards, boardsTest);
		Mockito.verify(boardMapper).findAll();
	}

	@Test
	public void 게시판하나출력() {
		Mockito.when(boardMapper.findOne(ArgumentMatchers.anyInt()))
			.thenReturn(this.board);
		Board boardTest = boardService.findOne(this.board.getId());
		assertEquals(boardTest, this.board);
	}

	@Test
	public void 게시판삽입성공() {
		BoardInsertRequestDto boardDto = new BoardInsertRequestDto();
		boardDto.setBoardName("게시판 삽입 테스트");
		boardService.insert(boardDto);
		Mockito.verify(boardMapper).insert(boardDto);
	}

}
