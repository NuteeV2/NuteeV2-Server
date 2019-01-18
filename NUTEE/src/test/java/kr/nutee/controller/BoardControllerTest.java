package kr.nutee.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.nutee.dto.Board;
import kr.nutee.exception.BadRequestException;
import kr.nutee.service.impl.BoardServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class BoardControllerTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private MockMvc mockMvc;

	@Autowired
	private BoardController boardController;

	@Autowired
	private BoardServiceImpl boardService;

	@Before
	public void setUp() throws Exception{
		mockMvc = standaloneSetup(boardController).build();
	}

	private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

	@Test
	public void 전체게시판() throws Exception{
		List<Board> boards = boardService.findAll();
		String jsonString = this.jsonStringFromObject(boards);
		MvcResult result = mockMvc.perform(get("/api/boards"))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(content().string(equalTo(jsonString)))
			.andReturn();

		logger.info(result.getResponse().getContentAsString());
	}

	@Test
	public void 게시판하나() throws Exception{
		int id = 1;
		Board board = boardService.findOne(id);
		String jsonString = this.jsonStringFromObject(board);
		MvcResult result = mockMvc.perform(get("/api/boards/{id}", id))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(content().string(equalTo(jsonString)))
			.andReturn();

		logger.info(result.getResponse().getContentAsString());
	}

	@Test//(expected=BadRequestException.class)
	public void 게시판하나_잘못된Url() throws Exception{
		int id = 10;
		mockMvc.perform(get("/api/boards/{id}", id))
			.andExpect((r)->assertTrue(r.getResolvedException().getClass().isAssignableFrom(BadRequestException.class)));
	}

}
