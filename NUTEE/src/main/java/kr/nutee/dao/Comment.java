package kr.nutee.dao;

import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class Comment {

	private int id;
	private String dates;
	private int userId;
	private String anonymous;
	private String deleted;
	private int report;
	private int recommentId;
	private int articleId;

	@NotEmpty(message="contents requires non blank value")
	private String contents;
	
}
