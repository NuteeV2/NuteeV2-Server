package kr.nutee.dao;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * Comment 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class Comment {

	private BigInteger id;
	private String contents;
	private String dates;
	private long userId;
	private String anonymous;
	private String deleted;
	private int report;
	private BigInteger reCommentId;
	private BigInteger articleId;

}
