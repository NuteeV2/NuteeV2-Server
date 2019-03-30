package kr.nutee.dao;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Comment 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comment {

	private int id;
	//private BigInteger id;
	private String dates;
	private long userId;
	private String anonymous;
	private String deleted;
	private int report;
	private int recommentId;
	private int articleId;
//	private BigInteger reCommentId;
//	private BigInteger articleId;

	@NotEmpty(message="contents requires non blank value")
	private String contents;

}
