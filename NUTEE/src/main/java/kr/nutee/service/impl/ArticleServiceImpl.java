package kr.nutee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.nutee.dao.Article;
import kr.nutee.model.Article.ArticleInsertRequestDto;
import kr.nutee.model.Article.ArticleListResponseDto;
import kr.nutee.model.Article.ArticleResponseDto;
import kr.nutee.model.Article.ArticleUpdateRequestDto;
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
		return transferDTOList(articles);
	}

	/*
	 * 카테고리별 게시글 조회
	 * @param 카테고리 id
	 * @return 카테고리 별 게시글
	 */
	@Override
	public List<ArticleListResponseDto> findAllByCategoryId(int categoryId) {
		//TODO 리팩토링 필요
		List<Article> articles = articleMapper.findAllByCategoryId(categoryId);
		if(articles == null) return null;
		return transferDTOList(articles);
	}

	/*
	 * 게시판의 게시글 하나 조회
	 * @param 게시판 id, 게시글 id
	 * @return 게시글 하나
	 */
	@Override
	@Transactional
	public ArticleResponseDto findOne(int id) {
		//TODO 리팩토링 필요, 없는 id error 처리
		hit(id);
		Article a = articleMapper.findOne(id);
		return transferDTO(a);
	}

	/*
	 * 조회수 증가
	 * @param 게시글 id
	 */
	@Override
	public void hit(int id) {
		articleMapper.hit(id);
	}

	/*
	 * 게시글 삽입
	 * @param 게시글 제목, 내용, 작성자 id, 익명 여부, 카테고리 id, 게시판 id
	 */
	@Override
	public void insert(ArticleInsertRequestDto article) {
		articleMapper.insert(article);
	}

	/*
	 * 게시글 수정
	 * @param 게시글 제목, 내용, 익명 여부, 카테고리 id
	 */
	@Override
	public void update(int id, ArticleUpdateRequestDto article) {
		articleMapper.update(id, article);
	}

	/*
	 * 게시글 삭제
	 * @param 게시글 id
	 */
	@Override
	public void delete(int id) {
		articleMapper.delete(id);
	}

	/*
	 * Article을 ArticleResponseDto로 변환하는 메소드
	 * @param Article
	 * @return ARticleResponseDto
	 */
	public ArticleResponseDto transferDTO(Article article) {
		//익명으로 작성된 게시글인 경우 '스누피'
		String nickname = article.getNickname();
		if(article.getAnonymous().equals("Y")) nickname = "스누피";
		ArticleResponseDto aDTO = new ArticleResponseDto(article.getId(), article.getTitle(), article.getContents(), article.getDates(), article.getUserId(), article.getCategoryId(), article.getHits(), article.getReport(), nickname, article.getEmpathy());
		return aDTO;
	}

	/*
	 * List<Article>을 List<ArticleResponseDto>로 변환하는 메소드
	 * @param List<Article>
	 * @return List<ArticleResponseDto>
	 */
	public List<ArticleListResponseDto> transferDTOList(List<Article> articles){
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

}
