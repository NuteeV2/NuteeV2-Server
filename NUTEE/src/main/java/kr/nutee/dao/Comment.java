package kr.nutee.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * Comment 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Comment {

	private int id;
	private String contents;
	private String dates;
	private int userId;
	private String anonymous;
	private String deleted;
	private int report;
	private int reCommentId;
	private int articleId;

}
