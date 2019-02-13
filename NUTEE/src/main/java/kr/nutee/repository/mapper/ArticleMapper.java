package kr.nutee.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.nutee.dao.Article;
import kr.nutee.model.Article.ArticleInsertRequestDto;
import kr.nutee.model.Article.ArticleUpdateRequestDto;

/*
 * Article Mapper Interface
 * @author choiyk
 */
@Mapper
public interface ArticleMapper {

	//전체 게시글 조회
	List<Article> findAll(int boardId);

	//Category별 게시글 조회
	List<Article> findAllByCategoryId(int categoryId);

	//게시글 하나 조회
	Article findOne(int id);

	//게시글 조회수 업데이트
	void hit(int id);

	//게시글 작성
	void insert(ArticleInsertRequestDto article);

	//게시글 수정
	void update(@Param("id") int id, @Param("article") ArticleUpdateRequestDto article);

	//게시글 삭제
	void delete(int id);
}
