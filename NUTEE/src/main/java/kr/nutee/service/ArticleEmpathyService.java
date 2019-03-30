package kr.nutee.service;

import java.math.BigInteger;
import java.util.List;

import kr.nutee.dao.ArticleEmpathy;

/*
 * ArticleEmapthy Service Interface
 *
 * @author choiyk
 */
public interface ArticleEmpathyService {

	//article id로 검색
	List<ArticleEmpathy> findAllByArticleId(BigInteger articleId);

	//게시글 별 공감 갯수 출력
	BigInteger countByArticleId(BigInteger articleId);

	//공감
	void insert(long userId, BigInteger articleId);

	//공감 취소
	void delete(long userId, BigInteger articleId);

}
