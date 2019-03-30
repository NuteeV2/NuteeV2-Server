package kr.nutee.controller;

import java.math.BigInteger;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.dao.ArticleFile;
import kr.nutee.dao.File;
import kr.nutee.model.Article.ArticleInsertRequestDto;
import kr.nutee.model.Article.ArticleListResponseDto;
import kr.nutee.model.Article.ArticleResponseDto;
import kr.nutee.model.Article.ArticleUpdateRequestDto;
import kr.nutee.service.impl.ArticleFileServiceImpl;
import kr.nutee.service.impl.ArticleServiceImpl;

/*
 * Article Controller
 *
 * @author choiyk
 */
@RestController
public class ArticleController {

	private final ArticleServiceImpl articleService;
	private final ArticleFileServiceImpl articleFileService;

	@Autowired
	public ArticleController(ArticleServiceImpl articleService, ArticleFileServiceImpl articleFileService) {
		this.articleService = articleService;
		this.articleFileService = articleFileService;
	}

	/*
	 * 전체 게시글 반환
	 * @param 게시판 id
	 * @return 전체 게시글 List
	 */
	@GetMapping("boards/{boardId}/articles")
	public ResponseEntity<List<ArticleListResponseDto>> boardArticles(@PathVariable("boardId") int boardId){
		List<ArticleListResponseDto> articles = articleService.findAll(boardId);
		if(articles.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>(articles, HttpStatus.OK);
	}

	/*
	 * 카테고리별 게시글 반환
	 * @param 카테고리 id
	 * @return 카테고리별 게시글 List
	 */
	@GetMapping("categories/{categoryId}/articles")
	public ResponseEntity<List<ArticleListResponseDto>> categoryArticles(@PathVariable("categoryId") int categoryId){
		List<ArticleListResponseDto> articles = articleService.findAllByCategoryId(categoryId);
		if(articles.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>(articles, HttpStatus.OK);
	}

	/*
	 * 사용자별 게시글 반환
	 * @param 카테고리 id
	 * @return 카테고리별 게시글 List
	 */
	@GetMapping("user/{userId}/articles")
	public ResponseEntity<List<ArticleListResponseDto>> userArticles(@PathVariable("userId") long userId){
		List<ArticleListResponseDto> articles = articleService.findAllByUserId(userId);
		if(articles.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>(articles, HttpStatus.OK);
	}

	/*
	 * 게시글 하나 반환
	 * @param 게시글 id
	 * @return 게시글 하나
	 */
	@GetMapping("articles/{id}")
	public ResponseEntity<ArticleResponseDto> articles(@PathVariable("id") BigInteger id){
		ArticleResponseDto article = articleService.findOne(id);
		return new ResponseEntity<>(article, HttpStatus.OK);
	}

	/*
	 * 게시글 작성
	 * @parma 게시글 제목, 내용, 작성자 id, 익명 여부, 카테고리 id, 게시판 id
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PostMapping("articles")
	public ResponseEntity<BigInteger> insert(@Valid @RequestBody ArticleInsertRequestDto article){
		BigInteger id = articleService.insert(article);
		return new ResponseEntity<>(id, HttpStatus.CREATED);
	}

	/*
	 * 게시글 수정
	 * @param 게시글 제목, 내용, 익명 여부, 카테고리 id
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PatchMapping("articles/{id}")
	public ResponseEntity<String> update(@PathVariable("id") BigInteger id,
			@Valid @RequestBody ArticleUpdateRequestDto article){
		articleService.update(id, article);
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}

	/*
	 * 게시글 삭제
	 * @param 카테고리 id
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@DeleteMapping("articles/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") BigInteger id){
		articleService.delete(id);
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}

	/*
	 * 게시글에 연결된 파일 목록 조회
	 * @param 게시글 id
	 */
	@GetMapping("articles/{id}/files")
	public ResponseEntity<List<File>> files(@PathVariable("id") BigInteger id){
		List<File> files = articleFileService.findAll(id);
		if(files.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>(articleFileService.findAll(id), HttpStatus.OK);
	}

	/*
	 * 게시글에 연결된 파일 목록 저장
	 * @param 게시글 id, 연결할 파일의 id
	 */
	@PostMapping("articles/{id}/files/{fileId}")
	public ResponseEntity<String> filesInsert(@PathVariable("id") BigInteger id,
			@PathVariable("fileId") BigInteger fileId){
		articleFileService.insert(new ArticleFile(null, id, fileId));
		return new ResponseEntity<>("", HttpStatus.CREATED);
	}

}
