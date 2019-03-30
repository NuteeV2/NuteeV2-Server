package kr.nutee.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.dao.ArticleEmpathy;
import kr.nutee.service.impl.ArticleEmpathyServiceImpl;

/*
 * ArticleEmpathy Controller
 *
 * @author choiyk
 */
@RestController
@RequestMapping("/api")
public class ArticleEmpathyController {

	private final ArticleEmpathyServiceImpl articleEmpathyService;

	@Autowired
	public ArticleEmpathyController(ArticleEmpathyServiceImpl articleEmpathyService) {
		this.articleEmpathyService = articleEmpathyService;
	}

	/*
	 * 게시판 별 공감 목록 출력
	 * @param articleId
	 * @return 게시판 별 공감 목록 있으면 200, 없으면 204
	 */
	@GetMapping("articles/{articleId}/empathies")
	public ResponseEntity<List<ArticleEmpathy>> findAllByArticleId(@PathVariable("articleId") BigInteger articleId){
		List<ArticleEmpathy> articleEmpathies = articleEmpathyService.findAllByArticleId(articleId);
		if(articleEmpathies.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>(articleEmpathies, HttpStatus.OK);
	}

	/*
	 * 게시판 별 공감 개수 출력
	 * @param articleId
	 * @return 게시판 별 공감 개수
	 */
	@GetMapping("articles/{articleId}/empathies/count")
	public ResponseEntity<BigInteger> countByArticleId(@PathVariable("articleId") BigInteger articleId){
		return new ResponseEntity<>(articleEmpathyService.countByArticleId(articleId), HttpStatus.OK);
	}

	/*
	 * 공감하기
	 * @param articleId, userId
	 */
	@PostMapping("articles/{articleId}/empathies/{userId}")
	public ResponseEntity<String> insert(@PathVariable("articleId") BigInteger articleId, @PathVariable("userId") long userId){
		articleEmpathyService.insert(userId, articleId);
		return new ResponseEntity<>("", HttpStatus.CREATED);
	}

	/*
	 * 공감 취소
	 * @param articleId, userId
	 */
	@DeleteMapping("articles/{articleId}/empathies/{userId}")
	public ResponseEntity<String> delete(@PathVariable("articleId") BigInteger articleId, @PathVariable("userId") long userId){
		articleEmpathyService.delete(userId, articleId);
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}
}
