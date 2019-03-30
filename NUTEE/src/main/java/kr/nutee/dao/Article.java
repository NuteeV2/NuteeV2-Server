package kr.nutee.dao;

import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * Article 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class Article {

	private BigInteger id;
	private String title;
	private String contents;
	private Timestamp dates;
	private long userId;
	private long hits;
	private String anonymous;
	private int categoryId;
	private int boardId;
	private String deleted;
	private int report;
	private String nickname;
	private long empathy;

}
