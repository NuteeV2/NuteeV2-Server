package kr.nutee.repository.mapper;

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
	List<ArticleEmpathy> findAllByArticleId(int articleId);

	//게시글 별 공감 갯수 출력
	int countByArticleId(int articleId);

	//공감
	void insert(int userId, int articleId);

	//공감 취소
	void delete(int userId, int articleId);

}
