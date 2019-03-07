package kr.nutee.service;

import java.util.List;

import kr.nutee.model.Article.ArticleInsertRequestDto;
import kr.nutee.model.Article.ArticleListResponseDto;
import kr.nutee.model.Article.ArticleResponseDto;
import kr.nutee.model.Article.ArticleUpdateRequestDto;

/*
 * Article Service Interface
 *
 * @author choiyk
 */
public interface ArticleService {

	//전체 게시글 조회
	List<ArticleListResponseDto> findAll(int boardId);

	//Category별 게시글 조회
	List<ArticleListResponseDto> findAllByCategoryId(int categoryId);

	//user별 게시글 조회
	List<ArticleListResponseDto> findAllByUserId(int userId);

	//게시글 하나 조회
	ArticleResponseDto findOne(int id);

	//게시글 조회수 증가
	void hit(int id);

	//게시글 작성
	int insert(ArticleInsertRequestDto article);

	//게시글 수정
	void update(int id, ArticleUpdateRequestDto article);

	//게시글 삭제
	void delete(int id);

}
