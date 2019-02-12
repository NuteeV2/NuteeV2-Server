package kr.nutee.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import kr.nutee.dao.Board;
import kr.nutee.model.Board.BoardInsertAndUpdateRequestDto;
import kr.nutee.service.impl.BoardServiceImpl;
import kr.nutee.util.JsonUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardController.class)
public class BoardControllerTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BoardServiceImpl boardService;

	Board board;
	BoardInsertAndUpdateRequestDto boardDto;
	List<Board> boards;

	@Before
	public void setUp(){
		this.board = new Board(100, "테스트 게시판 1");

		this.boards = new ArrayList<Board>();
		boards.add(board);

		this.boardDto = new BoardInsertAndUpdateRequestDto();
	}

	@Test
	public void 전체게시판() throws Exception{
		Mockito.when(boardService.findAll())
			.thenReturn(this.boards);
		ResultActions result = mockMvc.perform(get("/api/boards").contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(this.boards.size())))
			.andExpect(jsonPath("$[0].id", is(this.board.getId())))
			.andExpect(jsonPath("$[0].boardName", is(board.getBoardName())));

		Mockito.verify(boardService).findAll();
	}

	@Test
	public void 게시판하나() throws Exception{
		Mockito.when(boardService.findOne(ArgumentMatchers.anyInt()))
			.thenReturn(this.board);
		ResultActions result = mockMvc.perform(get("/api/boards/{id}", this.board.getId()).contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", is(this.board.getId())))
			.andExpect(jsonPath("$.boardName", is(board.getBoardName())));

		Mockito.verify(boardService).findOne(this.board.getId());
	}

	@Test
	public void 게시판삽입() throws Exception{
		Mockito.doNothing().when(boardService).insert(ArgumentMatchers.any());
		this.boardDto.setBoardName("테스트 게시판 2");
		ResultActions result = mockMvc.perform(post("/api/boards")
										.contentType(MediaType.APPLICATION_JSON)
										.content(JsonUtils.toJson(this.boardDto)));
		result.andExpect(status().isOk());
		Mockito.verify(boardService).insert(this.boardDto);
	}

	@Test
	public void 게시판삽입_이름조건오류() throws Exception {
		Mockito.doNothing().when(boardService).insert(ArgumentMatchers.any());
		this.boardDto.setBoardName(null);
		ResultActions result = mockMvc.perform(post("/api/boards")
										.contentType(MediaType.APPLICATION_JSON)
										.content(JsonUtils.toJson(this.boardDto)));
		result.andExpect(status().isConflict());
		Mockito.verify(boardService, Mockito.times(0)).insert(this.boardDto);
	}

	//TODO 현재 게시판 이름 중복은 디비 에러로 처리하여 Mock으로 테스트 불가..
	@Test
	public void 게시판삽입_이름중복오류() throws Exception {
		Mockito.doNothing().when(boardService).insert(ArgumentMatchers.any());
		this.boardDto.setBoardName("테스트 게시판 1");
		ResultActions result = mockMvc.perform(post("/api/boards")
										.contentType(MediaType.APPLICATION_JSON)
										.content(JsonUtils.toJson(this.boardDto)));
		result.andExpect(status().isConflict())
			.andExpect(jsonPath("$", is("게시판 이름 중복 오류")));
		Mockito.verify(boardService, Mockito.times(0)).insert(this.boardDto);
	}

}
