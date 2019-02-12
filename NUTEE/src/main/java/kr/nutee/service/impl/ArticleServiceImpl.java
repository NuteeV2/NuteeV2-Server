package kr.nutee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dao.Article;
import kr.nutee.model.Article.ArticleListResponseDto;
import kr.nutee.model.Article.ArticleResponseDto;
import kr.nutee.repository.mapper.ArticleMapper;
import kr.nutee.service.ArticleService;

/*
 * Article Service 구현 클래스
 * @author choiyk
 */
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired ArticleMapper articleMapper;

	/*
	 * 게시판의 전체 게시글 조회
	 * @param 게시판 id
	 * @return 게시판의 전체 게시글
	 */
	@Override
	public List<ArticleListResponseDto> findAll(int boardId) {
		//TODO 리팩토링 필요
		List<Article> articles = articleMapper.findAll(boardId);
		if(articles == null) return null;
		List<ArticleListResponseDto> list = new ArrayList<>();
		for(Article a : articles) {
			//익명으로 작성된 게시글인 경우 '스누피'
			String nickname = a.getNickname();
			if(a.getAnonymous().equals("Y")) nickname = "스누피";
			ArticleListResponseDto dto = new ArticleListResponseDto(a.getId(), a.getTitle(), a.getDates(), a.getUserId(), a.getCategoryId(), a.getHits(), a.getReport(), nickname, a.getEmpathy());
			list.add(dto);
		}
		return list;
	}

	/*
	 * 게시판의 게시글 하나 조회
	 * @param 게시판 id, 게시글 id
	 * @return 게시글 하나
	 */
	@Override
	public ArticleResponseDto findOne(int id) {
		//TODO 리팩토링 필요, error 처리
		Article a = articleMapper.findOne(id);
		String nickname = a.getNickname();
		if(a.getAnonymous().equals("Y")) nickname = "스누피";
		ArticleResponseDto article = new ArticleResponseDto(a.getId(), a.getTitle(), a.getContents(), a.getDates(), a.getUserId(), a.getCategoryId(), a.getHits(), a.getReport(), nickname, a.getEmpathy());
		return article;
	}

}
