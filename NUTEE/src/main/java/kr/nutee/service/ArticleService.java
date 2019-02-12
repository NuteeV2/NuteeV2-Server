package kr.nutee.service;

import java.util.List;

import kr.nutee.model.Article.ArticleListResponseDto;
import kr.nutee.model.Article.ArticleResponseDto;

/*
 * Article Service Interface
 *
 * @author choiyk
 */
public interface ArticleService {

	//전체 게시글 조회
	List<ArticleListResponseDto> findAll(int boardId);

	//게시글 하나 조회
	ArticleResponseDto findOne(int id);

}
