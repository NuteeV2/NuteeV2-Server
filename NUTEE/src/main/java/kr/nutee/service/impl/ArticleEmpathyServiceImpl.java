package kr.nutee.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import kr.nutee.dao.ArticleEmpathy;
import kr.nutee.exception.DuplicationException;
import kr.nutee.repository.mapper.ArticleEmpathyMapper;
import kr.nutee.service.ArticleEmpathyService;

/*
 * ArticleEmpathy Service 구현 클래스
 * @author choiyk
 */
@Service
public class ArticleEmpathyServiceImpl implements ArticleEmpathyService{

	@Autowired ArticleEmpathyMapper articleEmpathyMapper;

	/*
	 * 게시판 별 공감 목록 출력
	 * @param articleId
	 * @return 게시판 별 공감 목록
	 */
	@Override
	public List<ArticleEmpathy> findAllByArticleId(BigInteger articleId) {
		return articleEmpathyMapper.findAllByArticleId(articleId);
	}

	/*
	 * 게시판 별 공감 개수 출력
	 * @param articleId
	 * @return 게시판 별 공감 개수
	 */
	@Override
	public BigInteger countByArticleId(BigInteger articleId) {
		return articleEmpathyMapper.countByArticleId(articleId);
	}

	/*
	 * 공감하기
	 * @param articleId, userId
	 */
	@Override
	public void insert(long userId, BigInteger articleId) {
		try{
			articleEmpathyMapper.insert(userId, articleId);
		}catch(DataIntegrityViolationException e) {
			throw new DuplicationException("userId&articleId", "You've already empathized");
		}
	}

	/*
	 * 공감 취소
	 * @param articleId, userId
	 */
	@Override
	public void delete(long userId, BigInteger articleId) {
		articleEmpathyMapper.delete(userId, articleId);
	}

}
