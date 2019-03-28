package kr.nutee.repository.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.ArticleEmpathy;

/*
 * ArticleEmpathy Mapper Interface
 * @author choiyk
 */
@Mapper
public interface ArticleEmpathyMapper {
	//article id로 검색
	List<ArticleEmpathy> findAllByArticleId(BigInteger articleId);

	//게시글 별 공감 갯수 출력
	BigInteger countByArticleId(BigInteger articleId);

	//공감
	void insert(long userId, BigInteger articleId);

	//공감 취소
	void delete(long userId, BigInteger articleId);

}
