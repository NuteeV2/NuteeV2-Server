package kr.nutee.repository.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.ArticleFile;
import kr.nutee.dao.File;

/*
 * ArticleFile Mapper Interface
 * @author choiyk
 */
@Mapper
public interface ArticleFileMapper {

	//게시글에 연결된 전체 파일 리스트
	List<File> findAllByArticleId(BigInteger articleId);

	//게시글에 파일 추가
	void insert(ArticleFile fileArticle);

	//게시글에 연결된 파일 전체 삭제
	void deleteAll(BigInteger articleId);
}
