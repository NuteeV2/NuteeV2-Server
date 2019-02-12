package kr.nutee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.model.CustomResponseBody;
import kr.nutee.service.impl.ArticleServiceImpl;

/*
 * Article Controller
 *
 * @author choiyk
 */
@RestController
@RequestMapping("/api")
public class ArticleController {

	private final ArticleServiceImpl articleService;

	@Autowired
	public ArticleController(ArticleServiceImpl articleService) {
		this.articleService = articleService;
	}

	/*
	 * 전체 게시글 반환
	 * @param 게시판 id
	 * @return 전체 게시글 List
	 */
	@GetMapping("boards/{boardId}/articles")
	public ResponseEntity<CustomResponseBody> boardArticles(@PathVariable("boardId") int boardId){
		CustomResponseBody body = new CustomResponseBody(articleService.findAll(boardId), null);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.OK);
	}

	/*
	 * 게시글 하나 반환
	 * @param 게시글 id
	 * @return 게시글 하나
	 */
	@GetMapping("articles/{id}")
	public ResponseEntity<CustomResponseBody> articles(@PathVariable("id") int id){
		CustomResponseBody body = new CustomResponseBody(articleService.findOne(id), null);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.OK);
	}

}
