package kr.nutee.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.nutee.dao.FileTable;
import kr.nutee.model.Article.ArticleInsertRequestDto;
import kr.nutee.model.Article.ArticleListResponseDto;
import kr.nutee.model.Article.ArticleResponseDto;
import kr.nutee.model.Article.ArticleUpdateRequestDto;
import kr.nutee.playload.CustomResponseBody;
import kr.nutee.playload.UploadFileResponse;
import kr.nutee.service.impl.ArticleServiceImpl;
import kr.nutee.service.impl.FileServiceImpl;

/*
 * Article Controller
 *
 * @author choiyk
 */
@RestController
@RequestMapping("/api")
public class ArticleController {

	private final ArticleServiceImpl articleService;
	private final FileServiceImpl fileService;

	private final int articleTableId = FileTable.Article.getId();

	@Autowired
	public ArticleController(ArticleServiceImpl articleService, FileServiceImpl fileService) {
		this.articleService = articleService;
		this.fileService = fileService;
	}

	/*
	 * 전체 게시글 반환
	 * @param 게시판 id
	 * @return 전체 게시글 List
	 */
	@GetMapping("boards/{boardId}/articles")
	public ResponseEntity<CustomResponseBody> boardArticles(@PathVariable("boardId") int boardId){
		List<ArticleListResponseDto> articles = articleService.findAll(boardId);
		CustomResponseBody body = new CustomResponseBody(articles, null);
		if(articles.isEmpty()) return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<CustomResponseBody>(body, HttpStatus.OK);
	}

	/*
	 * 카테고리별 게시글 반환
	 * @param 카테고리 id
	 * @return 카테고리별 게시글 List
	 */
	@GetMapping("categories/{categoryId}/articles")
	public ResponseEntity<CustomResponseBody> categoryArticles(@PathVariable("categoryId") int categoryId){
		List<ArticleListResponseDto> articles = articleService.findAllByCategoryId(categoryId);
		CustomResponseBody body = new CustomResponseBody(articles, null);
		if(articles.isEmpty()) return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<CustomResponseBody>(body, HttpStatus.OK);
	}

	/*
	 * 사용자별 게시글 반환
	 * @param 카테고리 id
	 * @return 카테고리별 게시글 List
	 */
	@GetMapping("user/{userId}/articles")
	public ResponseEntity<CustomResponseBody> userArticles(@PathVariable("userId") int userId){
		List<ArticleListResponseDto> articles = articleService.findAllByUserId(userId);
		CustomResponseBody body = new CustomResponseBody(articles, null);
		if(articles.isEmpty()) return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<CustomResponseBody>(body, HttpStatus.OK);
	}

	/*
	 * 게시글 하나 반환
	 * @param 게시글 id
	 * @return 게시글 하나
	 */
	@GetMapping("articles/{id}")
	public ResponseEntity<CustomResponseBody> articles(@PathVariable("id") int id){
		ArticleResponseDto article = articleService.findOne(id);
		article.setFileInfs(fileService.findAllByFileTableIdAndColId(articleTableId, id));
		CustomResponseBody body = new CustomResponseBody(article, null);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.OK);
	}

	/*
	 * 게시글 작성
	 * @parma 게시글 제목, 내용, 작성자 id, 익명 여부, 카테고리 id, 게시판 id
	 * @return ResponseEntity<CustomResponseBody>

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

	@PatchMapping("articles/{id}")
	public ResponseEntity<CustomResponseBody> update(@PathVariable("id") int id,
			@Valid @RequestBody ArticleUpdateRequestDto article){
		articleService.update(id, article);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
	}
	*/

	//TODO test 못함
	//TODO 예외처리 생각하고 추가하기
	/*
	 * 게시글 작성
	 * @parma type: FormData() , body: article(게시글 제목, 내용, 작성자 id, 익명 여부, 카테고리 id, 게시판 id), file 리스트
	 * @return ResponseEntity<CustomResponseBody>
	*/
	@PostMapping("articles")
	public ResponseEntity<CustomResponseBody> insert(@Valid @RequestParam("article") ArticleInsertRequestDto article,
			@RequestParam("files") MultipartFile[] files){
		//게시글 저장
		int articleId = articleService.insert(article);

		//파일 저장
		List<UploadFileResponse> result = fileService.insert(articleTableId, articleId, files);

		CustomResponseBody body = new CustomResponseBody(result, null);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.CREATED);
	}

	/*
	 * 게시글 수정
	 * @param 게시글 제목, 내용, 익명 여부, 카테고리 id
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PatchMapping("articles/{id}")
	public ResponseEntity<CustomResponseBody> update(@PathVariable("id") int id,
			@Valid @RequestParam("article") ArticleUpdateRequestDto article,
			@RequestParam("files") MultipartFile[] files){
		//게시글 수정
		articleService.update(id, article);

		//파일 수정
		fileService.update(article.getFileInfs(), articleTableId, id, files);

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
		//게시글 삭제
		articleService.delete(id);

		//파일 삭제
		fileService.delete(articleTableId, id);

		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
	}

}
