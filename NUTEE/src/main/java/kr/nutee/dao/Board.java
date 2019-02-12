package kr.nutee.dao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/*
 * Board 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@AllArgsConstructor	//TODO @NoArgsconstructor 로 바꿀 것
@ToString
@EqualsAndHashCode
public class Board {

	private int id;
	private String boardName;

}
