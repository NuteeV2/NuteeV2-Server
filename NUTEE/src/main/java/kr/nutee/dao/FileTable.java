package kr.nutee.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * FileTable 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FileTable {

	private int id;
	private String fileTableName;

}
