package kr.nutee.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * Category 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class Category {

	private int id;
	private String categoryName;
	private int boardId;

}
