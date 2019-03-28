package kr.nutee.dao;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * ArticleEmpathy 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class ArticleEmpathy {

	private long userId;
	private BigInteger articleId;

}
