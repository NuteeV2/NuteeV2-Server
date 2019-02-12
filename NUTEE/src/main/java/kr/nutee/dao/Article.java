package kr.nutee.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * Article 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Article {

	private int id;
	private String title;
	private String contents;
	private String dates;
	private int userId;
	private int hits;
	private String anonymous;
	private int categoryId;
	private int boardId;
	private String deleted;
	private int report;
	private String nickname;
	private int empathy;

}
