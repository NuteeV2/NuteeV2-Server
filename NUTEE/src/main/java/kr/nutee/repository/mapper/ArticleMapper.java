package kr.nutee.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.Article;

/*
 * Article Mapper Interface
 * @author choiyk
 */
@Mapper
public interface ArticleMapper {

	//전체 게시글 조회
	List<Article> findAll(int boardId);

	//게시글 하나 조회
	Article findOne(int id);

	//게시글 조회수 업데이트
	void hit(int id);

	//게시글 작성

	//게시글 수정

	//게시글 삭제

}
