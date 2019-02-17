package kr.nutee.controller;

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

import kr.nutee.model.CustomResponseBody;
import kr.nutee.model.Article.ArticleInsertRequestDto;
import kr.nutee.model.Article.ArticleUpdateRequestDto;
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
	 * 카테고리별 게시글 반환
	 * @param 카테고리 id
	 * @return 카테고리별 게시글 List
	 */
	@GetMapping("categories/{categoryId}/articles")
	public ResponseEntity<CustomResponseBody> categoryArticles(@PathVariable("categoryId") int categoryId){
		CustomResponseBody body = new CustomResponseBody(articleService.findAllByCategoryId(categoryId), null);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.OK);
	}

	/*
	 * 사용자별 게시글 반환
	 * @param 카테고리 id
	 * @return 카테고리별 게시글 List
	 */
	@GetMapping("user/{userId}/articles")
	public ResponseEntity<CustomResponseBody> userArticles(@PathVariable("userId") int userId){
		CustomResponseBody body = new CustomResponseBody(articleService.findAllByUserId(userId), null);
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

	/*
	 * 게시글 작성
	 * @parma 게시글 제목, 내용, 작성자 id, 익명 여부, 카테고리 id, 게시판 id
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PostMapping("articles")
	public ResponseEntity<CustomResponseBody> insert(@Valid @RequestBody ArticleInsertRequestDto article){
		articleService.insert(article);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.CREATED);
	}

	/*
	 * 게시글 수정
	 * @param 게시글 제목, 내용, 익명 여부, 카테고리 id
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PatchMapping("articles/{id}")
	public ResponseEntity<CustomResponseBody> update(@PathVariable("id") int id,
			@Valid @RequestBody ArticleUpdateRequestDto article){
		articleService.update(id, article);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
	}

	/*
	 * 게시글 삭제
	 * @param 카테고리 id
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@DeleteMapping("articles/{id}")
	public ResponseEntity<CustomResponseBody> delete(@PathVariable("id") int id){
		articleService.delete(id);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
	}

}
